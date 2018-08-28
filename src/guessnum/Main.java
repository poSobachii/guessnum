package guessnum;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int myNum = rand.nextInt(100) + 1;

        boolean userLost = true;
        for (int i = 1; i <= 10; i++) {
            System.out.println("Try #" + i);
            int userNum = scan.nextInt();

            if (userNum > myNum) {
                System.out.println("lower");
            } else if (userNum < myNum) {
                System.out.println("higher");
            } else {
                System.out.println("correct - YOU WON");
                userLost = false;
                break;
            }
        }
        if (userLost == true) {  // mozno prosto (userlost) - avtomaticheski schitaet estj li tam true ili !userLost - not true ?
            System.out.println("YOU LOST !");
        }
    }
}
