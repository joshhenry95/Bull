package bull.bull;

import android.widget.TextView;

/**
 * Created by joshh_000 on 8/22/2017.
 */

public class Player {
    private int score, rollValue;
    private String name;

    public Player(String n) {
        this.score = 1; // DELETE CHANGE THIS BACK TO 6
        this.name = n;

    }


    public int getScore() {
        return this.score;
    }

    public void setRollValue(int n) {
        this.rollValue = n;
    }

    public int getRollValue() {
        return this.rollValue;
    }

    public String getName() {
        return this.name;
    }

    public void decreaseScore() {
        this.score--;

        if (this.score == 0) {
            Database.removePlayerByName(this.name);
        }
    }
}
