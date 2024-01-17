import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the length of the secret code:");
            int userLength = scanner.nextInt();

            System.out.println("Enter the quantity of symbols you want to play with:");
            int range = scanner.nextInt();

            String randomNumber = generateRandomNumber(userLength , range);
            System.out.println("Okay, let's start the game!");
            game(randomNumber);

        }catch (Exception e){
            System.out.println("Error");
        }
    }

    public static void game(String secretCode) {

        Scanner scanner = new Scanner(System.in);

        int bulls = 0;
        int cows = 0;
        int totalBulls = 0;

        String userCode = "";
        String[] secretCodeArray = secretCode.split("");
        int turn = 1;

        while (!secretCode.equals(userCode)){

            System.out.println("Turn "+turn+":");

            userCode = scanner.nextLine();
            String[] userCodeArray = userCode.split("");

            for (int i = 0; i < secretCodeArray.length; i++) {
                if (secretCodeArray[i].equals(userCodeArray[i])) {
                    bulls++;
                }
            }

            for (int i = 0; i < secretCodeArray.length; i++) {
                for (int j = 0; j < secretCodeArray.length; j++) {
                    if (Objects.equals(secretCodeArray[i], userCodeArray[j]) && !Objects.equals(secretCodeArray[i], userCodeArray[i])){
                        cows++;
                    }
                }
            }

            if (cows == 0 && bulls == 0) {
                System.out.println("Grade: None.");
            } else if (bulls == secretCode.length()) {
                System.out.println("Grade: "+bulls+" bulls.");
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            } else {
                System.out.println("Grade: "+bulls+" bull(s) and "+cows+" cow(s).");
            }

            turn++;
            totalBulls =  totalBulls + bulls;
            bulls = 0;
            cows = 0;

            System.out.println(totalBulls + " Total bulls");
        }
    }

    public static String generateRandomNumber(int userLength, int range){

        String[] alphabetArray = "0123456789abcdefghijklmnopqrstuvwxyz".split("");
        StringBuilder alphanumeric = new StringBuilder(range);
        StringBuilder secretCode = new StringBuilder(userLength);

        for (int i = 0; i < range; i++) {
            alphanumeric.append(alphabetArray[i]);
        }

        Random random = new Random();

        int aleatoryChar = random.nextInt(range - 1);

        for (int i = 0; i < userLength; i++) {
            secretCode.append(alphanumeric.charAt(aleatoryChar));
            aleatoryChar = random.nextInt(range - 1);
        }

        for (int i = 0; i < secretCode.length(); i++) {
            for (int j = 0; j < secretCode.length(); j++) {
                if (secretCode.charAt(i) == secretCode.charAt(j) && i != j) {
                    secretCode.setCharAt(j,alphanumeric.charAt(random.nextInt(range - 1)));
                    j=0;
                    i=0;
                }
            }
        }

        StringBuilder stars = new StringBuilder();
        stars.append("*".repeat(Math.max(0, userLength)));

        if (range <= 10){
            System.out.println("The secret is prepared: "+stars+" (0-"+ (range-1) +").");
        } else {
            System.out.println("The secret is prepared: "+stars+" (0-9, a-"+alphanumeric.charAt(range-1)+").");
        }
        return secretCode.toString();

    }
}

