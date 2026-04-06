package control;

import domain.Combatant;
import domain.Enemy;
import domain.Player;
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
    private TurnOrderStrategy turnStrategy; // strategy to decide turn order. Can be SpeedTurnOrder or other implementation.
    private int currentRound; // tracks the current round
    private BattleUIProcess uiProcess = new BattleUIProcess(); // Add UI process

    public BattleEngine(List<Combatant> initialCombatants, TurnOrderStrategy strategy) {
        this.activeCombatants = new ArrayList<>(initialCombatants);
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

            if (checkWinCondition() || checkLossCondition()) { // check if the battle is over, i.e. if all enemies are dead or all allies dead
                break;
            }
        }
        cleanupDefeated(); //remove all dead combatants from the master list. 
        currentRound++;
    }

    private void handlePlayerTurn(Player player) {
        uiProcess.showPlayerStats(player.getName(), player.getHp(), player.getMaxHp(), player.getAttack(), player.getDefense(), player.getSpeed());
        
        List<Combatant> aliveEnemies = new ArrayList<>();
        for (Combatant c : activeCombatants) {
            if (c instanceof Enemy && !c.isDefeated()) {
                aliveEnemies.add(c);
            }
        }
        
        int choice = uiProcess.chooseAction(player.hasItems(), player.isCoolDownReady(), player.getCooldownTimer());
        
        if (choice == 1) { // Basic Attack
            if (aliveEnemies.isEmpty()) return;
            
            String[] enemyNames = new String[aliveEnemies.size()];
            for (int i = 0; i < aliveEnemies.size(); i++) {
                enemyNames[i] = aliveEnemies.get(i).getName() + " (HP: " + aliveEnemies.get(i).getHp() + ")";
            }
            uiProcess.showEnemies(enemyNames);
            
            int targetIdx = uiProcess.chooseTarget(aliveEnemies.size()) - 1;
            Combatant target = aliveEnemies.get(targetIdx);
            
            new BasicAttackAction().execute(player, Collections.singletonList(target));
            uiProcess.actionDisplayResult(player.getName() + " used Basic Attack on " + target.getName() + "!");
        } else if (choice == 2) { // Defend
             uiProcess.actionDisplayResult("Defend action not fully implemented yet.");
        } else if (choice == 3) { // Use Item
             if (player.hasItems()) {
                 uiProcess.actionDisplayResult("Item usage not fully implemented in UI yet.");
             } else {
                 uiProcess.noItems();
             }
        } else if (choice == 4) { // Special Skill
             if (player.isCoolDownReady()) {
                 player.useSpecialSkill(activeCombatants);
                 uiProcess.showPlayerSpecialSkill(player.getName(), "Special Skill");
             } else {
                 uiProcess.actionDisplayResult("Special skill is on cooldown!");
             }
        }
    }
    private void cleanupDefeated() {
        Iterator<Combatant> iterator = activeCombatants.iterator(); // gets an iterator to walk through the list one element at a time
        while (iterator.hasNext()) { //return true if no more elements
            if (iterator.next().isDefeated()) {
                iterator.remove();
            }
        }
    }
    public boolean checkWinCondition() {
        for (Combatant c : activeCombatants) {
            if (c instanceof Enemy) {
                return false; // returns false if even one instance of enemy. otherwise c will continue to loop through activeCombatants
            }
        }
        System.out.println("Victory! All enemies defeated!");
        return true;
    }
    public boolean checkLossCondition() {
        for (Combatant c : activeCombatants) {
            if (c instanceof Player) {
                return false; //returns false if even one instance of player
            }
        }
        System.out.println("Defeat! You have fallen in battle!");
        return true;
    }


}
