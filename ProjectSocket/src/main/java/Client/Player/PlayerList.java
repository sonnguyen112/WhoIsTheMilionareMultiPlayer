package Client.Player;

public class PlayerList {
    /*
     * singleton class, each game there are only 1 class like this
     */
    private Player[] players = { null, null, null, null };
    public int player_num;

    private PlayerList() {
        this.player_num = 0;
    }

    public void set(Player[] out_players){
        int count = 0;
        for (int i = 0; i < 4; i++){
            players[i] = out_players[i];
            if (players[i] != null) count++;
        }
        player_num = count;
    }

    public Player getPlayer(int i){
        return players[i];
    }

    private static final PlayerList playerlist = new PlayerList();

    public static PlayerList getInstance() {
        return playerlist;
    }

    public Player get(int index) {
        return players[index];
    }

    public boolean add(Player player) {
        if (player_num == 4)
            return false;
        players[player_num] = player;
        player_num++;
        return true;
    }
}
