import boundary.GameUISetup;
import boundary.BattleUIProcess;
import control.BattleEngine;
import control.LevelManager;
import control.SpeedTurnOrder;
import domain.Player;
import domain.Warrior;
import domain.Wizard;
import domain.Combatant;
import domain.Enemy;
import system.Item;
import system.Potion;
import system.PowerStone;
import system.SmokeBomb;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameUISetup setup = new GameUISetup();
        BattleUIProcess uiProcess = new BattleUIProcess();
        
        int playerChoice = setup.choosePlayer();
        Player player;
        if (playerChoice == 1) {
            player = new Warrior("Hero");
        } else {
            player = new Wizard("Hero");
        }
        
        int[] itemChoices = setup.chooseItems();
        for (int choice : itemChoices) {
            if (choice == 1) player.addItem(new Potion());
            else if (choice == 2) player.addItem(new PowerStone());
            else if (choice == 3) player.addItem(new SmokeBomb());
        }
        
        int difficultyChoice = setup.chooseDifficulty();
        String diffString = "Easy";
        if (difficultyChoice == 2) diffString = "Medium";
        else if (difficultyChoice == 3) diffString = "Hard";
        
        LevelManager levelManager = new LevelManager(diffString);
        List<Enemy> initialEnemies = levelManager.spawnInitialEnemies();
        
        List<Combatant> allCombatants = new ArrayList<>();
        allCombatants.add(player);
        allCombatants.addAll(initialEnemies);
        
        BattleEngine engine = new BattleEngine(allCombatants, new SpeedTurnOrder());
        
        boolean backupSpawned = false;
        
        System.out.println("\n==================================");
        System.out.println("          BATTLE START!           ");
        System.out.println("==================================\n");
        
        while (true) {
            engine.executeRound();
            
            if (engine.checkLossCondition()) {
                int[] items = countItems(player);
                uiProcess.showBattleResult(false, player.getHp(), player.getMaxHp(), engine.getCurrentRound(), items[0], items[1], items[2]);
                break;
            }
            
            if (engine.checkWinCondition()) {
                if (!backupSpawned && (diffString.equals("Medium") || diffString.equals("Hard"))) {
                    uiProcess.showEnemyBackup();
                    List<Enemy> backups = levelManager.spawnBackupEnemies();
                    engine.addCombatants(backups);
                    backupSpawned = true;
                } else {
                    int[] items = countItems(player);
                    uiProcess.showBattleResult(true, player.getHp(), player.getMaxHp(), engine.getCurrentRound(), items[0], items[1], items[2]);
                    break;
                }
            }
        }
    }

    private static int[] countItems(Player player) {
        int p = 0, s = 0, pw = 0;
        for (Item item : player.getInventory()) {
            if (item.getName().equals("Potion")) p++;
            else if (item.getName().equals("Smoke Bomb")) s++;
            else if (item.getName().equals("Power Stone")) pw++;
        }
        return new int[]{p, s, pw};
    }
}
