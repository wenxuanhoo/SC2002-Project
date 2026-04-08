package boundary;

import java.util.Scanner;

public class BattleUIProcess {
    private final Scanner sc = new Scanner(System.in);
    //initialise new Scanner object

    public void showRounds(int round_number) {
        System.out.println("-------ROUND " + round_number + "-------");
    }
    //displays round number

    public void showTurnOrder(String[] combatant_names) {
        System.out.print("Turn Order: ");

        for (int i = 0; i < combatant_names.length; i += 1) {
            if (i > 0) {
                System.out.print("-->");
            }
            System.out.print(combatant_names[i]);
        }
        System.out.println("                                 ");

    }
    // displays turn order depending on the List of combatants

    public void showPlayerStats(String player_name, int hp, int maxHp, int attack, int defense, int speed, int potions, int smokeBombs, int cooldown, String enemyStats) {
        System.out.println("Player: " + player_name);
        System.out.println("HP: " + hp + "/" + maxHp + " | Attack: " + attack + " | Defense: " + defense + " | Speed: " + speed + " | Potion: " + potions + " | Smoke Bomb: " + smokeBombs + " | Special Skills Cooldown: " + cooldown + " Round(s)");
        System.out.println(enemyStats);
        System.out.println("                                 ");
    }
    //displays player stats

    public void showEnemies(String[] enemy_info) {
        System.out.println("Enemies: ");
        for (int i = 0; i < enemy_info.length; i += 1) {
            System.out.println((i + 1) + ". " + enemy_info[i]);
        }
        System.out.println("                                 ");

    }
    //displays Enemies information line by line

    public void showStatusEffects(String name, boolean is_stunned, boolean is_defended, boolean is_invulnerable) {
        boolean affected = is_stunned || is_defended || is_invulnerable;
        
        if (affected) {
            if (is_stunned) {
                System.out.println("Stunned - cannot act in this turn");
            }
            if (is_defended) {
                System.out.println("Defending - +10 defense");
            }
            if (is_invulnerable) {
                System.out.println("Smoke Bomb active - immune to damage");
            }
            System.out.println("                                   ");
        }
    }
    //display status effects if any

    private int ReadIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();

            try {
                int input = Integer.parseInt(line);
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Invalid number. Enter a number between " + min + " and " + max + '.');
            } catch (NumberFormatException exception) {
                System.out.println("Invalid number. Please enter an ACTUAL number.");
            }
        } 
    } 
    //copied from GameUISetup class, used locally under BattleUIProcess class

    public int chooseAction(boolean has_items, boolean special_ready, int cooldown) {
        System.out.println("Choose actions: ");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");

        if (has_items) {
            System.out.println("3. Use Item");
        } else {
            System.out.println("3. Use Item (NO items remaining)");
        }

        if (special_ready) {
            System.out.println("4. Special Skill (READY)");
        } else {
            System.out.println("4. Special Skill (Cooldown: " + cooldown + " turns remaining.)");
        }

        System.out.println("                                  ");
        return ReadIntInRange("Enter Choice (1-4): ", 1, 4);

    }
    //displays option choices and proompts user to choose action
    
    public int chooseTarget(int enemy_count) {
        return ReadIntInRange("Choose the target enemy (" + 1 + " to " + enemy_count + "): ", 1, enemy_count);
    }
    //choose the enemy target by number

    public int chooseItem(int item_count) {
        return ReadIntInRange("Choose item (1 to " + item_count + "): ", 1, item_count);
    }
    //choose the item by number

    public void actionDisplayResult(String action_message) {
        System.out.println(action_message);
        System.out.println("                                   ");
    }
    //displays whatever action that you want to specify

    public void displayStunned(String name) {
        System.out.println(name + " is STUNNED, turn skipped!!");
        System.out.println("                                   ");
    }
    //displays that a combatant is stunned

    public void showElim(String name) {
        System.out.println(name + " is ELIMINATED!");
        System.out.println("                                   ");
    }
    //displays that a combatant is eliminated

    public void noItems() {
        System.out.println("You have no items remaining");
        System.out.println("                                   ");
    }
    //displays you no items and broke

    public void showPlayerAttack(String player_name, String action_name, String enemy_name, int damage) {
        System.out.println(player_name + " has used " + action_name + " on " + enemy_name + ", dealing " + damage + " damage!");
    }
    //displays player attack 

    public void showPlayerSpecialSkill(String player_name, String special_skill) {
        System.out.println(player_name + " has used " + special_skill + "!!!");
    }
    //displays player using special skill

    public void showEnemyAttack(String enemy_name, String action_name, String player_name, int damage) {
        System.out.println(enemy_name + " has used " + action_name + " on " + player_name + ", dealing " + damage + " damage!");
    }
    //displays enemy using normal attack

    public void showEnemyBackup() {
        System.out.println("--------------------------------");
        System.out.println("      BACKUP SPAWN ARRIVED      ");
        System.out.println("    new enemies have arrived    ");
        System.out.println("--------------------------------");
    }
    //displays UI for backup spawn

    public void showItemInventory(String[] item_names, int[] item_counts) {
        System.out.println("Items: ");
        for (int i = 0; i < item_names.length; i += 1) {
            System.out.print(item_names[i] + ": " + item_counts[i]);
            if (item_counts[i] == 0) {
                System.out.print(" (consumed) ");
            }
            System.out.println();
        }
        System.out.println("                                    ");
    }
    //displays available items from item list and item count list

    public void showBattleResult(boolean isVictory, int hp, int maxHp, int rounds, int potions, int smokeBombs, int powerStones, int enemiesRemaining) {
        if (isVictory) {
            System.out.println("Congratulations, you have defeated all your enemies.");
            System.out.println("Statistics: Remaining HP: " + hp + " / " + maxHp + " | Total Rounds: " + rounds);
        } else {
            System.out.println("Defeated. Don't give up, try again!");
            System.out.println("Statistics: Enemies remaining: " + enemiesRemaining + " | Total Rounds Survived: " + rounds);
        }
        System.out.println("Remaining Potion: " + potions + " | Remaining Smoke Bomb: " + smokeBombs + " | Remaining Power Stone: " + powerStones);
        System.out.println("                                   ");
    }

    public int chooseEndGameOption() {
        System.out.println("1. Replay with the same settings");
        System.out.println("2. Start a new game (return to the home screen)");
        System.out.println("3. Exit");
        return ReadIntInRange("Choose option (1-3): ", 1, 3);
    }
}