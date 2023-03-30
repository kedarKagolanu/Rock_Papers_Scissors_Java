package rockpaperscissors;

public class Player {

    private String name;
    private int score;

    Player(String name) {
        this.name = name;
    }

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    void changeScore(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
