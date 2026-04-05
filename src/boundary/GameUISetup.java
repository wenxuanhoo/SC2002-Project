package boundary;

import java.util.Scanner;

public class GameUISetup {
    private final Scanner scanner_forGameUISetup;

    public GameUISetup(Scanner scanner) {
        scanner_forGameUISetup = scanner;
    }
    // assigns scanner object from main to GameUISetup obj

    private int ReadIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner_forGameUISetup.nextLine().trim();

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
    // method that reads and checks whether an int matches the range given or not. If not it throws an exception

    public int choosePlayer() {
        System.out.println("-----------------------------------");
        System.out.println("           CHOOSE PLAYER           ");
        System.out.println("-----------------------------------");
        System.out.println("Choose between two playable characters");
        System.out.println("                                   ");
        System.out.println("1. Warrior");
        System.out.println("HP: 260 | Attack: 40 | Defense: 20 | Speed: 30");
        System.out.println("Special Skill: Shield Bash");
        System.out.println("BasicAttack damage to selected enemy. Selected enemy is unable to take actions for the current turn and the next turn.");
        System.out.println("                                   ");
        System.out.println("2. Wizard");
        System.out.println("HP: 200 | Attack: 50 | Defense: 10 | Speed: 20");
        System.out.println("Special Skill: Arcane Blast");
        System.out.println("BasicAttack damage to all enemies currently in combat. Each enemy defeated by Arcane Blast adds to to the Wizard's Attack, lasting until end of the level.");
        System.out.println("                                   ");
        
        return ReadIntInRange("Choose player (1 or 2): ", 1, 2);
    }
    // displays player character choices and make choice

    public int[] chooseItems() {
        System.out.println("-----------------------------------");
        System.out.println("            CHOOSE ITEMS           ");
        System.out.println("-----------------------------------");
        System.out.println("Take two items, duplicates allowed.");
        System.out.println("                                   ");
        System.out.println("1. Potion");
        System.out.println("Heals 100HP");
        System.out.println("2. Power Stone");
        System.out.println("Use special skill once without affecting cooldown timer");
        System.out.println("3. Smoke Bomb");
        System.out.println("Enemy attacks nullified in this turn and the next turn");
        System.out.println("                                   ");

        int[] itemChoices = new int[2];
        itemChoices[0] = ReadIntInRange("Choose item 1 (1 to 3): ", 1, 3);
        itemChoices[1] = ReadIntInRange("Choose item 2 (1 to 3): ", 1, 3);

        return itemChoices;
    }
    // displays item choices and make 2 choices

    public int chooseDifficulty() {
        System.out.println("-----------------------------------");
        System.out.println("         CHOOSE DIFFICULTY         ");
        System.out.println("-----------------------------------");
        System.out.println("Choose between Easy, Medium and Hard");
        System.out.println("                                   ");
        System.out.println("1. Easy");
        System.out.println("Initial Spawn: 3 Goblins");
        System.out.println("                                   ");
        System.out.println("2. Medium");
        System.out.println("Initial Spawn: 1 Goblin, 1 Wolf");
        System.out.println("Backup Spawn: 2 Wolves");
        System.out.println("                                   ");
        System.out.println("3. Hard");
        System.out.println("Initial Spawn: 2 Goblins");
        System.out.println("Backup Spawn: 1 Goblin, 2 Wolves");
        System.out.println("                                   ");

        return ReadIntInRange("Enter difficulty (1-3): ", 1, 3);
    }
    // displays difficulty choices and choose difficulty

    private void showEnemyStats() {
        System.out.println("------------ENEMY STATS------------");
        System.out.println("Goblin: ");
        System.out.println("HP: 55 | Attack: 35 | Defense: 15 | Speed: 25");
        System.out.println("Wolf: ");
        System.out.println("HP: 40 | Attack: 45 | Defense: 5 | Speed: 35");
        System.out.println("                                   ");
    }
    // displays enemy information

    

}
    

