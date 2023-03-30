package Client.Player;

public class PlayerList {
    /*
     * singleton class, each game there are only 1 class like this
     */
    private Player[] players = { null, null, null, null };
    private int player_num;

    private PlayerList() {
        this.player_num = 0;
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
