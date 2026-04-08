package boundary;

import java.util.List;
import java.util.Scanner;

import domain.Enemy;
import domain.Player;

public class GameResultUI {
    private final Scanner sc = new Scanner(System.in);

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
    //copied from GameUISetup class, used locally under BattleUIProcess and GameResultUI

}