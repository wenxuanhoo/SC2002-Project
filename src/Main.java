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
                System.out.println("Defeat! You have fallen in battle!");
                break;
            }
            
            if (engine.checkWinCondition()) {
                if (!backupSpawned && (diffString.equals("Medium") || diffString.equals("Hard"))) {
                    uiProcess.showEnemyBackup();
                    List<Enemy> backups = levelManager.spawnBackupEnemies();
                    engine.addCombatants(backups);
                    backupSpawned = true;
                } else {
                    System.out.println("Victory! All enemies defeated!");
                    break;
                }
            }
        }
    }
}
