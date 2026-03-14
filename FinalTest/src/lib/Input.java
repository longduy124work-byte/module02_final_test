package lib;

import java.util.Scanner;

public class Input {
    public static String inputString() {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        return str;
    }

    public static int inputNumber() {
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                String str = input.nextLine();
                int number = Integer.parseInt(str);
                return number;
            } catch(NumberFormatException e) {
                System.out.println("Enter a valid number on the menu: ");
            }
        }
    }
}
