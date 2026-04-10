package boundary;


public class GameResultUI {

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
        return InputHelper.readIntInRange("Choose option (1-3): ", 1, 3);
    }

}