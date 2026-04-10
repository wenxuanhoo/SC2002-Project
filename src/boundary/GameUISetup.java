package boundary;


import domain.Warrior;
import domain.Wizard;
import domain.Goblin;
import domain.Wolf;

public class GameUISetup {

    public int choosePlayer() {
        System.out.println("-----------------------------------");
        System.out.println("           CHOOSE PLAYER           ");
        System.out.println("-----------------------------------");
        System.out.println("Choose between two playable characters");
        System.out.println();
        Warrior rw = new Warrior("Hero");
        Wizard rwi = new Wizard("Hero");
        System.out.println("1. Warrior");
        System.out.println("HP: " + rw.getMaxHp() + " | Attack: " + rw.getAttack() + " | Defense: " + rw.getDefense() + " | Speed: " + rw.getSpeed());
        System.out.println("Special Skill: Shield Bash");
        System.out.println("BasicAttack damage to selected enemy. Selected enemy is unable to take actions for the current turn and the next turn.");
        System.out.println();
        System.out.println("2. Wizard");
        System.out.println("HP: " + rwi.getMaxHp() + " | Attack: " + rwi.getAttack() + " | Defense: " + rwi.getDefense() + " | Speed: " + rwi.getSpeed());
        System.out.println("Special Skill: Arcane Blast");
        System.out.println("BasicAttack damage to all enemies currently in combat. Each enemy defeated by Arcane Blast adds to to the Wizard's Attack, lasting until end of the level.");
        System.out.println();
        
        return InputHelper.readIntInRange("Choose player (1 or 2): ", 1, 2);
    }
    // displays player character choices and make choice

    public int[] chooseItems() {
        System.out.println("-----------------------------------");
        System.out.println("            CHOOSE ITEMS           ");
        System.out.println("-----------------------------------");
        System.out.println("Take two items, duplicates allowed.");
        System.out.println();
        System.out.println("1. Potion");
        System.out.println("Heals 100HP");
        System.out.println("2. Power Stone");
        System.out.println("Use special skill once without affecting cooldown timer");
        System.out.println("3. Smoke Bomb");
        System.out.println("Enemy attacks nullified in this turn and the next turn");
        System.out.println();

        int[] itemChoices = new int[2];
        itemChoices[0] = InputHelper.readIntInRange("Choose item 1 (1 to 3): ", 1, 3);
        itemChoices[1] = InputHelper.readIntInRange("Choose item 2 (1 to 3): ", 1, 3);

        return itemChoices;
    }
    // displays item choices and make 2 choices

    public int chooseDifficulty() {
        System.out.println("-----------------------------------");
        System.out.println("         ENEMY INFORMATION         ");
        System.out.println("-----------------------------------");
        System.out.println();
        showEnemyStats();

        System.out.println("-----------------------------------");
        System.out.println("         CHOOSE DIFFICULTY         ");
        System.out.println("-----------------------------------");
        System.out.println("Choose between Easy, Medium and Hard");
        System.out.println();
        System.out.println("1. Easy");
        System.out.println("Initial Spawn: 3 Goblins");
        System.out.println();
        System.out.println("2. Medium");
        System.out.println("Initial Spawn: 1 Goblin, 1 Wolf");
        System.out.println("Backup Spawn: 2 Wolves");
        System.out.println();
        System.out.println("3. Hard");
        System.out.println("Initial Spawn: 2 Goblins");
        System.out.println("Backup Spawn: 1 Goblin, 2 Wolves");
        System.out.println();

        return InputHelper.readIntInRange("Enter difficulty (1-3): ", 1, 3);
    }
    // displays difficulty choices and choose difficulty

    private void showEnemyStats() {
        Goblin g = new Goblin();
        Wolf w = new Wolf();
        System.out.println("------------ENEMY STATS------------");
        System.out.println("Goblin: ");
        System.out.println("HP: " + g.getMaxHp() + " | Attack: " + g.getAttack() + " | Defense: " + g.getDefense() + " | Speed: " + g.getSpeed());
        System.out.println("Wolf: ");
        System.out.println("HP: " + w.getMaxHp() + " | Attack: " + w.getAttack() + " | Defense: " + w.getDefense() + " | Speed: " + w.getSpeed());
        System.out.println("                                   ");
    }
    // displays enemy information

    

}
    

