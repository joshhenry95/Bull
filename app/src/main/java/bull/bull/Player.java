package bull.bull;

import android.widget.TextView;

/**
 * Created by joshh_000 on 8/22/2017.
 */

public class Player {
    private int score, rollValue;
    private String name;

    public Player(String n) {
        score = 6;
        name = n;
    }


    public int getScore() {
        return score;
    }

    public void setRollValue(int n) {
        rollValue = n;
    }

    public int getRollValue() {
        return rollValue;
    }

    public String getName() {
        return name;
    }

    public void decreaseScore() {
        score--;

        if (score == 0) {
            Database.removePlayerByName(name);
        }
    }
}
