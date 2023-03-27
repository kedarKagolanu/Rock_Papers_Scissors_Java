package rockpaperscissors;

public enum Choice {
    rock("paper"),scissors("rock"),paper("scissors");
    
    final String defeatingPair;

    Choice(String pair) {
        defeatingPair = pair;
    }
    
    String getDefeatingPair() {return defeatingPair;}
}
