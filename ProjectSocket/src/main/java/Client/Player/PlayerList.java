package Client.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    /*
     * singleton class, each game there are only 1 class like this
     */
    private List<Player> players = new ArrayList<>();
    public int currentPlayer = 0;
    public String playername;
    public boolean answer = false;

    private PlayerList() {}

    public void set(ArrayList<String> out_players){
        players = out_players.stream().map(e->new Player(e)).toList();
    }

    public int size(){
        return players.size();
    }

    public Player getPlayer(int i){
        return players.get(i);
    }

    private static final PlayerList playerlist = new PlayerList();

    public static PlayerList getInstance() {
        return playerlist;
    }

    public Player get(int index) {
        return players.get(index);
    }

    public void Clear(){
        players.clear();
    }
}
