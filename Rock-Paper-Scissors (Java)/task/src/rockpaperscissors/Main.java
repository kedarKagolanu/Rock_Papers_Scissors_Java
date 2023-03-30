package rockpaperscissors;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        // write your code here
        Scanner input = new Scanner(System.in);
        Player currentPlayer;
        boolean isDefaultGamePlay = false;

        // taking input name form the current player
        System.out.print("Enter your name: ");
        String currentPlayerName = input.nextLine();
        System.out.println("Hello, "+currentPlayerName);

        // Loading players data from the rating.txt file to plauerDataMap.

        Map<String,Player> playersDataMap = new HashMap<>();
        File playerDataFile = new File("rating.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(playerDataFile)) ) {
            Stream<String> temp = br.lines();
            playersDataMap = temp.map(s -> s.split(" "))
                    .map(s -> new Player(s[0],Integer.parseInt(s[1])))
                    .collect(Collectors.toMap(Player::getName,p -> p));

        }catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try (Scanner sc = new Scanner(playerDataFile)) {
            while(sc.hasNextLine()) {
                StringTokenizer temp = new StringTokenizer(sc.nextLine());
                String playerName = temp.nextToken();
                int playerScore = Integer.parseInt(temp.nextToken());
                Player player = new Player(playerName, playerScore);
                playersDataMap.put(playerName,player);
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
        */

        String options = input.nextLine();
        StringTokenizer tokens;
        if(!options.isBlank()) {
            tokens = new StringTokenizer(options,",");
        } else {
            isDefaultGamePlay = true;
            tokens = new StringTokenizer("rock paper scissors"," ");
        }
        Choices bucket = new Choices(tokens);

        // checking if the player exists in the plyersDataMap.
        if(playersDataMap.containsKey(currentPlayerName)) {
            currentPlayer = playersDataMap.get(currentPlayerName);
        } else {
            currentPlayer = new Player(currentPlayerName,0);
            playersDataMap.put(currentPlayerName,currentPlayer);
        }

        // running the game.
        System.out.println("Okay, let's start");
        while(true) {
            Scanner in = new Scanner(System.in);
            String userChoiceString = in.next().toLowerCase();
            if(userChoiceString.equals("!exit")) {
                //exiting out of the game.
                break;
            } else if(userChoiceString.equals("!rating")) {
                //creating a new game.
                System.out.println("Your rating: " + currentPlayer.getScore());
                continue;
            }
            if(!bucket.optionsList.contains(userChoiceString)) {
                System.out.println("Invalid input");
                continue;
            }

            Random random = new Random();
            int randomNumber = Math.abs(random.nextInt() % bucket.optionsList.size());
            String computerChoiceString = bucket.optionsList.get(randomNumber);
            Choices.printResult(currentPlayer,bucket.optionsList,userChoiceString,computerChoiceString,isDefaultGamePlay);
        }

        System.out.println("Bye!");
    }
}
