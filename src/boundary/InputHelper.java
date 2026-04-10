package boundary;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner sc = new Scanner(System.in);

    public static int readIntInRange(String prompt, int min, int max) {
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
}
