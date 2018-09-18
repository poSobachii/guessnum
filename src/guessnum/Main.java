package guessnum;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static List<GameResult> results = new ArrayList<>();

    public static void main(String[] args) {
        loadResults();

        String answer;

        do {
            System.out.println("Enter your name ?");
            String name = scan.next();


            int myNum = rand.nextInt(100) + 1;

            long t1 = System.currentTimeMillis();
            boolean userLost = true;
            for (int i = 1; i <= 10; i++) {
                System.out.println("Try #" + i + " Choose a number between 0 and 100");
                int userNum = askNum();

                if (userNum > myNum) {
                    System.out.println("lower");
                } else if (userNum < myNum) {
                    System.out.println("higher");
                } else {
                    long t2 = System.currentTimeMillis();
                    long time = (t2 - t1);
                    System.out.println("correct - YOU WON");
                    System.out.println("Your time is " + time / 1000.0 + " seconds");
                    userLost = false;
                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount = i;
                    r.time = time;
                    results.add(r);
                    results.sort(Comparator.<GameResult>comparingInt(x -> x.triesCount).thenComparingLong(x -> x.time));

                    break;
                }
            }
            if (userLost == true) {     // mozno prosto (userlost) - avtomaticheski schitaet estj li tam true ili !userLost - not true ?
                System.out.println("YOU LOST !");
            }

            System.out.println("Wanna play again ? (Y/N)");
            answer = askYN();
        } while (answer.equals("Y"));


        showResults();
        saveResults();
        System.out.println("Goodbye !");
    }

    private static void loadResults() {
        File plum = new File("top_scores.txt");
        try (Scanner shmeks = new Scanner(plum)) {

            while (shmeks.hasNext()) {
                GameResult apple = new GameResult();
                apple.name = shmeks.next();
                apple.triesCount = shmeks.nextInt();
                apple.time = shmeks.nextLong();
                results.add(apple);
            }
        } catch (IOException e) {
            System.out.println("Cannot load from a file");
        }
    }

    private static void saveResults() {
        File plum = new File("top_scores.txt");
        try (PrintWriter xeks = new PrintWriter(plum)) {
            for (GameResult r : results) {
                xeks.printf("%s %d %d\n", r.name, r.triesCount, r.time);
            }
        } catch (IOException e) {
            System.out.println("Cannot save to file");
        }
    }

//    private static void showResults() {
//        int count = 0;
//        for (GameResult r : results) {
//            System.out.println(r.name + ". Your tries --> " + r.triesCount + ". Your time --> " + r.time / 1000 + " seconds !");
//            count++;
//        if (count == 3){
//                break;
//            }
//        }

//    private static void showResults() {
//        for (GameResult r : results) {
//            System.out.println(r.name + ". Your tries --> " + r.triesCount + ". Your time --> " + r.time / 1000 + " seconds !");
//        }
//    }

//    private static void showResults() {
//       int index = Math.min(5, results.size());
//        for (int i = 0; i < index; i++) {
//            GameResult r = results.get(i);
//                System.out.println(r.name + ". Your tries --> " + r.triesCount + ". Your time --> " + r.time / 1000 + " seconds !");
//        }

    private static void showResults() {
       results.stream()
               .limit(5)
               .forEach( r -> {
                   System.out.println(r.name + ". --> " + r.triesCount + ". --> " + r.time / 1000 + " secs !");
               });
    }


        static String askYN () {
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

        static int askNum () {
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