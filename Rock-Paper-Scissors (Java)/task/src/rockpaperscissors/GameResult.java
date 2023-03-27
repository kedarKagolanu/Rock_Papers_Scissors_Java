package rockpaperscissors;

public enum GameResult {
    WON,LOST,DRAW;


    public static GameResult getResult(Choice userInput,String computerInput) {
        if(userInput.name().equals(computerInput))
            return DRAW;
        else if(userInput.getDefeatingPair().equals(computerInput))
            return LOST;
        else
            return WON;
    }
}
