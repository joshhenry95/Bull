package bull.bull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by joshh_000 on 8/22/2017.
 */

public class Dice {
    private int firstDie;
    private int secondDie;

    public Dice() {}

    public  int getDiceRoll() {
        // The value we'll get here is one integer, rather than two actual die
        // values.
        // Ex: If the user rolls a 5 and a 6, their roll value will be 65.
        //     If they roll a 2 and a 2, they'll have 22 etc.

        this.firstDie = roll();
        this.secondDie = roll();

        // Check for doubles, return 11 * any of the die values.
        if (this.firstDie == this.secondDie) {
            return 11 * this.firstDie;
        }

        // If they're not doubles, then return 10 * MaxDie + MinDie:
        return (10 * Math.max(this.firstDie, this.secondDie)) + Math.min(this.firstDie, this.secondDie);
    }

    private static int roll() {
        // Returns a random number within the range 1 - 6 inclusive.
        return (int)(Math.random() * 6) + 1;
    }

}
