package Player;

public class Player {
    private String name;
    private int score;
    private boolean host;
    public Player(String name, boolean host){
        this.name = name;
        this.score = 0;
        this.host = host;
    }
}
