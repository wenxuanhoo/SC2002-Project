package boundary;

import java.util.Scanner;

public class GameResultUI {
    private final Scanner sc = new Scanner(System.in);

    public void showBattleResult(boolean isVictory, int hp, int maxHp, int rounds, int potions, int smokeBombs, int powerStones, int enemiesRemaining) {
        
        System.out.println("--------------------------------");

        if (isVictory) {
            System.out.println("            VICTORY             ");
            System.out.println("--------------------------------");
            System.out.println("Congratulations, you have defeated all your enemies.");
            System.out.println("Statistics: Remaining HP: " + hp + " / " + maxHp + " | Total Rounds: " + rounds);
        } else {
            System.out.println("            DEFEAT              ");
            System.out.println("--------------------------------");
            System.out.println("Defeated. Don't give up, try again!");
            System.out.println("Statistics: Enemies remaining: " + enemiesRemaining + " | Total Rounds Survived: " + rounds);
        }
        System.out.println();
        System.out.println("Remaining items:");
        System.out.println(" - Potions: " + potions);
        System.out.println(" - Smoke Bombs: " + smokeBombs);
        System.out.println(" - Power Stones: " + powerStones);

    }

    public int chooseEndGameOption() {
        System.out.println("1. Replay with the same settings");
        System.out.println("2. Start a new game (return to the home screen)");
        System.out.println("3. Exit");
        return readIntInRange("Choose option (1-3): ", 1, 3);
    }

    private int readIntInRange(String prompt, int min, int max) {
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
    //copied from GameUISetup class, used locally under BattleUIProcess and GameResultUI

}