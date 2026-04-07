package control;

import domain.Combatant;
import domain.Enemy;
import domain.Player;
import system.Item;
import boundary.BattleUIProcess;
import system.BasicAttackAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BattleEngine {
    /**
     * Controller that manages a battle. Takes in a list of combatants and a strategy to determine turn order.
     */
    private List<Combatant> activeCombatants; // master list containing everyone still alive
    private List<Enemy> trackedEnemies = new ArrayList<>(); // tracks all enemies encountered (even defeated)
    private TurnOrderStrategy turnStrategy; // strategy to decide turn order. Can be SpeedTurnOrder or other implementation.
    private int currentRound; // tracks the current round
    private BattleUIProcess uiProcess = new BattleUIProcess(); // Add UI process

    public BattleEngine(List<Combatant> initialCombatants, TurnOrderStrategy strategy) {
        this.activeCombatants = new ArrayList<>(initialCombatants);
        for (Combatant c : initialCombatants) {
            if (c instanceof Enemy) {
                trackedEnemies.add((Enemy) c);
            }
        }
        this.turnStrategy = strategy;
        this.currentRound = 1;
    }
    public void executeRound() {
        System.out.println("--- ROUND " + currentRound + " START ---");
        List<Combatant> turnQueue = turnStrategy.determineTurnOrder(activeCombatants); //calls the strategy's method, passing the master list of alive combatants.
        
        for (Combatant currentActor : turnQueue) {
            if (currentActor.isDefeated()) {
                continue;
            }
            if (currentActor.isStunned()) {
                System.out.println(currentActor.getName() + " is stunned and skips their turn!");
                currentActor.updateEffects(); // tick down stun timer
                continue;
            }
            System.out.println("It is " + currentActor.getName() + "'s turn.");
            
            if (currentActor instanceof Player) {
                handlePlayerTurn((Player) currentActor);
            } else {
                currentActor.performTurn(activeCombatants);
            }

            currentActor.updateEffects(); // tick down effects after their turn
            cleanupDefeated(); // check for any immediate eliminations after end of turn

            if (checkWinCondition() || checkLossCondition()) { // check if the battle is over, i.e. if all enemies are dead or all allies dead
                break;
            }
        }
        currentRound++;
    }

    private List<Combatant> promptForTarget(List<Combatant> aliveEnemies) {
        if (aliveEnemies.isEmpty()) return Collections.emptyList();
        
        String[] enemyNames = new String[aliveEnemies.size()];
        for (int i = 0; i < aliveEnemies.size(); i++) {
            enemyNames[i] = aliveEnemies.get(i).getName() + " (HP: " + aliveEnemies.get(i).getHp() + ")";
        }
        uiProcess.showEnemies(enemyNames);
        
        int targetIdx = uiProcess.chooseTarget(aliveEnemies.size()) - 1;
        return Collections.singletonList(aliveEnemies.get(targetIdx));
    }

    private void handlePlayerTurn(Player player) {
        int potions = 0, smokeBombs = 0;
        for (Item item : player.getInventory()) {
            if (item.getName().equals("Potion")) potions++;
            else if (item.getName().equals("Smoke Bomb")) smokeBombs++;
        }
        
        List<Combatant> aliveEnemies = new ArrayList<>();
        for (Combatant c : activeCombatants) {
            if (c instanceof Enemy && !c.isDefeated()) {
                aliveEnemies.add(c);
            }
        }
        
        uiProcess.showPlayerStats(player.getName(), player.getHp(), player.getMaxHp(), player.getAttack(), player.getDefense(), player.getSpeed(), potions, smokeBombs, player.getCooldownTimer(), getEnemyStatusString());
        
        boolean actionCompleted = false;
        
        while (!actionCompleted) {
            int choice = uiProcess.chooseAction(player.hasItems(), player.isCoolDownReady(), player.getCooldownTimer());
            
            if (choice == 1) { // Basic Attack
                List<Combatant> targets = promptForTarget(aliveEnemies);
                if (targets.isEmpty()) continue;
                
                new BasicAttackAction().execute(player, targets);
                uiProcess.actionDisplayResult(player.getName() + " used Basic Attack on " + targets.get(0).getName() + "!");
                actionCompleted = true;
            } else if (choice == 2) { // Defend
                 new system.DefendAction().execute(player, null);
                 uiProcess.actionDisplayResult(player.getName() + " is defending! (+10 Defense)");
                 actionCompleted = true;
            } else if (choice == 3) { // Use Item
                 if (player.hasItems()) {
                     List<Item> inv = player.getInventory();
                     String[] itemNames = new String[inv.size()];
                     int[] itemCounts = new int[inv.size()];
                     for (int i = 0; i < inv.size(); i++) {
                         itemNames[i] = (i + 1) + ") " + inv.get(i).getName();
                         itemCounts[i] = 1;
                     }
                     uiProcess.showItemInventory(itemNames, itemCounts);
                     
                     int itemIdx = uiProcess.chooseItem(inv.size()) - 1;
                     Item chosen = inv.get(itemIdx);
                     
                     List<Combatant> targetsToPass = activeCombatants;
                     if (chosen.requiresEnemyTarget(player)) {
                         targetsToPass = promptForTarget(aliveEnemies);
                         if (targetsToPass.isEmpty()) continue;
                     }
                     
                     player.useItem(chosen, targetsToPass);
                     uiProcess.actionDisplayResult(player.getName() + " used " + chosen.getName() + "!");
                     actionCompleted = true;
                 } else {
                     uiProcess.noItems();
                 }
            } else if (choice == 4) { // Special Skill
                 if (player.isCoolDownReady()) {
                     List<Combatant> targetsToPass = activeCombatants;
                     if (!player.isSpecialSkillAoE()) {
                         targetsToPass = promptForTarget(aliveEnemies);
                         if (targetsToPass.isEmpty()) continue;
                     }
                     
                     player.useSpecialSkill(targetsToPass);
                     uiProcess.showPlayerSpecialSkill(player.getName(), "Special Skill");
                     actionCompleted = true;
                 } else {
                     uiProcess.actionDisplayResult("Special skill is on cooldown!");
                 }
            }
        }
    }
    private void cleanupDefeated() {
        Iterator<Combatant> iterator = activeCombatants.iterator(); // gets an iterator to walk through the list one element at a time
        while (iterator.hasNext()) { //return true if no more elements
            Combatant c = iterator.next();
            if (c.isDefeated()) {
                uiProcess.showElim(c.getName());
                iterator.remove();
            }
        }
    }
    public int getAliveEnemyCount() {
        int count = 0;
        for (Combatant c : activeCombatants) {
            if (c instanceof Enemy && !c.isDefeated()) {
                count++;
            }
        }
        return count;
    }
    public boolean checkWinCondition() {
        for (Combatant c : activeCombatants) {
            if (c instanceof Enemy) {
                return false; // returns false if even one instance of enemy. otherwise c will continue to loop through activeCombatants
            }
        }
        return true;
    }
    public boolean checkLossCondition() {
        for (Combatant c : activeCombatants) {
            if (c instanceof Player) {
                return false; //returns false if even one instance of player
            }
        }
        return true;
    }

    public void addCombatants(List<? extends Combatant> newCombatants) {
        this.activeCombatants.addAll(newCombatants);
        for (Combatant c : newCombatants) {
            if (c instanceof Enemy) {
                trackedEnemies.add((Enemy) c);
            }
        }
    }
    public int getCurrentRound() {
        return currentRound;
    }

    public String getEnemyStatusString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trackedEnemies.size(); i++) {
            sb.append(trackedEnemies.get(i).getName()).append(" HP: ").append(trackedEnemies.get(i).getHp());
            if (i < trackedEnemies.size() - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }
}
