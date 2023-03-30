package rockpaperscissors;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Choices {

    ArrayList<String> optionsList = new ArrayList<>();

    public Choices(StringTokenizer st) {
        while(st.hasMoreTokens()) {
            optionsList.add(st.nextToken());
        }
    }

    public static  void printResult(Player player, ArrayList<String> optionsList,String userInputString,String computerInputString,boolean isDefaultGamePlay) {

        if(userInputString.equals(computerInputString)) {
            System.out.println("There is a draw (" + computerInputString + ")");
            player.changeScore(50);
            return;
        }

        boolean userWon = false;
        if(isDefaultGamePlay) {
            var temp = getDefeatingPair(computerInputString);
            if(userInputString.equals(temp)) {
                userWon = true;
            }
        } else {
            int userChoiceNumber = optionsList.indexOf(userInputString);
            int computerChoiceNumber = optionsList.indexOf(computerInputString);

            int lowerLimit = userChoiceNumber + 1;
            int upperLimit = (userChoiceNumber + ((optionsList.size() - 1) / 2)) % optionsList.size();

            if(lowerLimit < upperLimit) {
                // options between lowerlimit and upperlimit are Stronger than user options in this case.
                if(!(computerChoiceNumber >= lowerLimit && computerChoiceNumber <= upperLimit)) {
                    userWon = true;
                }
            } else {
                //options after lowerlimit or before upperlimit are Stronger than user options in this case.
                if(!(computerChoiceNumber >= lowerLimit || computerChoiceNumber <= upperLimit)) {
                    userWon = true;
                }
            }
        }

        if(userWon) {
            System.out.println("Well done. The computer chose " + computerInputString + " and failed");
            player.changeScore(100);
        } else {
            System.out.println("Sorry, but the computer chose " + computerInputString);
        }
    }

    private static String getDefeatingPair(String s) {
        switch (s) {
            case "rock" : return "paper";
            case "paper" : return "scissors";
            case "scissors" : return "rock";
        }
        return null;
    }

}
