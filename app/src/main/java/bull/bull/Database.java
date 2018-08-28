package bull.bull;

import java.util.ArrayList;

/**
 * Created by joshh_000 on 8/22/2017.
 */

public class Database {
    public static ArrayList<Player> playerList = new ArrayList<Player>();
    public static int numPlayers = 3;
    public static int playerIndex = 0;
    public static Player activePlayer = null; // The Player whose turn it is.
    public static Player previousPlayer = null; // Player to the right of active player.
    public static boolean playersEntered = false;


    public static void setNextActivePlayer() {
        playerIndex++;

        if (playerIndex == playerList.size()) {
            playerIndex = 0;
        }

        previousPlayer = activePlayer;

        activePlayer = playerList.get(playerIndex);
    }

    public static void setActivePlayer(Player p) {
        activePlayer = p;
    }

    public static void removePlayerByName(String n) {
        for (int i = 0; i < playerList.size(); i++) {
            Player currPlayer = playerList.get(i);

            if (currPlayer.getName().equals(n)) {
                playerList.remove(i);
            }
        }
    }

}
