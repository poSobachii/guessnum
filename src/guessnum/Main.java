package guessnum;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String answer;
        do {
            int myNum = rand.nextInt(100) + 1;

            boolean userLost = true;
            for (int i = 1; i <= 10; i++) {
                System.out.println("Try #" + i);
                int userNum = askNum();

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
            System.out.println("Wanna play again ? (Y/N)");
            answer = askYN();
        } while (answer.equals("Y"));
        System.out.println("Goodbye !");
    }

    static String askYN() {
        String answer;
        do {
            answer = scan.next();
            if (!answer.equals("Y") && !answer.equals("N")) {
                System.out.println("Type Y or N only");
                continue;   // continue - delaj sledujuwu interaciju cikla - fori , do/while lubogo cikla snachala
            } else {
                break;
            }
        } while (true);
        return answer;

    }

//    static int askNum() {
//        int userNum;
//        do {
//            userNum = scan.nextInt();
//            if ( userNum < 0) {
//                System.out.println("Type only in 0 - 100 range");
//                continue;
//            } else if ( userNum > 100) {
//                System.out.println("Type only in 0 - 100 range");
//                continue;
//            } else {
//                break;
//            }
//        } while (true);
//        return userNum;
//    }


    static int askNum() {
        int answer;
        do {
            try {
                answer = scan.nextInt();
            } catch (InputMismatchException k) {
                System.out.println("This isn't a number");
                scan.next();
                continue;
            }
            if (answer < 0 || answer > 100) {
                System.out.println("Please enter in 0 - 100 range");
            } else {
                return answer;
            }
        } while (true);
    }
}