package rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        while(true) {
            Scanner in = new Scanner(System.in);
            String userChoiceString = in.next().toLowerCase();
            if(userChoiceString.equals("!exit")) {
                break;
            }

            Choice userChoice = null;
            try {
                userChoice = Choice.valueOf(userChoiceString);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
                continue;
            }

            Random random = new Random();
            int randomNumber = Math.abs(random.nextInt() % 3);
            String computerChoice = getChoiceAsString(randomNumber);

            GameResult resultVal = GameResult.getResult(userChoice ,computerChoice);
            printResultFrom(resultVal,userChoice,computerChoice);
        }

        System.out.println("Bye!");
    }

    private static void printResultFrom(GameResult resultVal,Choice userChoice,String computerChoice) {
        switch(resultVal) {
            case DRAW :
                System.out.println("There is a draw (" + userChoice  + ")");
                break;
            case LOST :
                System.out.println("Sorry, but the computer chose "+computerChoice);
                break;
            case WON :
                System.out.println("Well done. The computer chose "+computerChoice+" and failed");
        }
    }

    private static String getChoiceAsString(int choiceNum) {
        switch(choiceNum) {
            case 0 :
                return "rock";
            case 1 :
                return "scissors";
            case 2 :
                return "paper";
        }
        return null;
    }
}
