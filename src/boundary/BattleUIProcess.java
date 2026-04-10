package boundary;


public class BattleUIProcess {

    public void showRounds(int roundNumber) {
        System.out.println("------ ROUND " + roundNumber + " ------");
    }
    //displays round number

    public void showTurnOrder(String[] combatantNames) {
        System.out.print("Turn Order: ");

        for (int i = 0; i < combatantNames.length; i += 1) {
            if (i > 0) {
                System.out.print("-->");
            }
            System.out.print(combatantNames[i]);
        }
        System.out.println();

    }
    // displays turn order depending on the List of combatants

    public void showPlayerStats(String playerName, int hp, int maxHp, int attack, int defense, int speed, int potions, int smokeBombs, int cooldown, String enemyStats) {
        System.out.println("Player: " + playerName);
        System.out.println("HP: " + hp + "/" + maxHp + " | Attack: " + attack + " | Defense: " + defense + " | Speed: " + speed + " | Potion: " + potions + " | Smoke Bomb: " + smokeBombs + " | Special Skills Cooldown: " + cooldown + " Round(s)");
        System.out.println(enemyStats);

    }
    //displays player stats

    public void showEnemies(String[] enemyInfo) {
        System.out.println("Enemies: ");
        for (int i = 0; i < enemyInfo.length; i += 1) {
            System.out.println((i + 1) + ". " + enemyInfo[i]);
        }
        System.out.println();

    }
    //displays Enemies information line by line

    //display status effects if any

    public int chooseAction(boolean hasItems, boolean specialReady, int cooldown) {
        System.out.println("Choose action: ");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");

        if (hasItems) {
            System.out.println("3. Use Item");
        } else {
            System.out.println("3. Use Item (NO items remaining)");
        }

        if (specialReady) {
            System.out.println("4. Special Skill (READY)");
        } else {
            System.out.println("4. Special Skill (Cooldown: " + cooldown + " turns remaining.)");
        }

        System.out.println();
        return InputHelper.readIntInRange("Enter Choice (1-4): ", 1, 4);

    }
    //displays option choices and proompts user to choose action
    
    public int chooseTarget(int enemyCount) {
        return InputHelper.readIntInRange("Choose the target enemy (" + 1 + " to " + enemyCount + "): ", 1, enemyCount);
    }
    //choose the enemy target by number

    public int chooseItem(int itemCount) {
        return InputHelper.readIntInRange("Choose item (1 to " + itemCount + "): ", 1, itemCount);
    }
    //choose the item by number

    public void actionDisplayResult(String actionMessage) {
        System.out.println(actionMessage);
        System.out.println();
    }
    //displays whatever action that you want to specify

    public void displayStunned(String name) {
        System.out.println(name + " is STUNNED, turn skipped!!");
        System.out.println();
    }
    //displays that a combatant is stunned

    public void showElim(String name) {
        System.out.println(name + " is ELIMINATED!");
        System.out.println();
    }
    //displays that a combatant is eliminated

    public void noItems() {
        System.out.println("You have no items remaining");
        System.out.println();
    }
    //displays you no items and broke

    public void showPlayerAttack(String playerName, String actionName, String enemyName, int damage) {
        System.out.println(playerName + " has used " + actionName + " on " + enemyName + ", dealing " + damage + " damage!");
    }
    //displays player attack 

    public void showPlayerSpecialSkill(String playerName, String specialSkill) {
        System.out.println(playerName + " has used " + specialSkill + "!!!");
    }
    //displays player using special skill

    public void showEnemyAttack(String enemyName, String actionName, String playerName, int damage) {
        System.out.println(enemyName + " has used " + actionName + " on " + playerName + ", dealing " + damage + " damage!");
    }
    //displays enemy using normal attack

    public void showEnemyBackup() {
        System.out.println("--------------------------------");
        System.out.println("      BACKUP SPAWN ARRIVED      ");
        System.out.println("    new enemies have arrived    ");
        System.out.println("--------------------------------");
    }
    //displays UI for backup spawn

    public void showItemInventory(String[] itemNames, int[] itemCounts) {
        System.out.println("Items: ");
        for (int i = 0; i < itemNames.length; i += 1) {
            System.out.print(itemNames[i] + ": " + itemCounts[i]);
            if (itemCounts[i] == 0) {
                System.out.print(" (consumed) ");
            }
            System.out.println();
        }
        System.out.println();
    }
    //displays available items from item list and item count list

    public void showBattleStart() {
        System.out.println("\n==================================");
        System.out.println("          BATTLE START!           ");
        System.out.println("==================================\n");
    }

}