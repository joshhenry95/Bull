package bull.bull;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;


public class GameActivity extends AppCompatActivity {

    Dice dice = new Dice();
    boolean rollIsHidden = false;
    boolean haveRolled = false;
    boolean specialValueRolled = false; // Keeps track if the last roll was a 21 or 31.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Button rollButton = (Button) findViewById(R.id.roll_button);
        final Button bullButton = (Button) findViewById(R.id.bull_button);
        final Button scoresButton = (Button) findViewById(R.id.scores_button);
        final Button subtractYourselfButton = (Button) findViewById(R.id.subtract_yourself_button);
        final Button subtractPreviousButton = (Button) findViewById(R.id.subtract_previous_button);
        final TextView rollLabel = (TextView) findViewById(R.id.roll_label);

        bullButton.setVisibility(View.INVISIBLE);
        subtractYourselfButton.setVisibility(View.INVISIBLE);
        subtractPreviousButton.setVisibility(View.INVISIBLE);

        Database.setActivePlayer(Database.playerList.get(0));

        scoresButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, ScoresActivity.class));
            }
        });

        subtractYourselfButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO
                String textStr = "";

                subtractYourselfButton.setVisibility(View.INVISIBLE);
                subtractPreviousButton.setVisibility(View.INVISIBLE);

                Database.activePlayer.decreaseScore();



                if (Database.playerList.size() == 1) {
                    rollButton.setVisibility(View.INVISIBLE);
                    bullButton.setVisibility(View.INVISIBLE);
                    scoresButton.setVisibility(View.INVISIBLE);
                }

                if (Database.activePlayer.getScore() == 0) {
                    Database.setNextActivePlayer();
                    textStr = Database.activePlayer.getName() + "'s score is zero and is now eliminated! " + Database.activePlayer.getName() + ", roll the dice!";

                } else {
                    textStr = Database.activePlayer.getName() + ", roll the dice!";
                }

                haveRolled = false;

                rollLabel.setText(textStr);

                //checkIfElimination();
                checkIfWinner(rollLabel);

            }
        });

        subtractPreviousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO
                subtractYourselfButton.setVisibility(View.INVISIBLE);
                subtractPreviousButton.setVisibility(View.INVISIBLE);

                Database.previousPlayer.decreaseScore();

                rollLabel.setText(Database.activePlayer.getName() + ", roll the dice!");

                if (Database.playerList.size() == 1) {
                    rollButton.setVisibility(View.INVISIBLE);
                    bullButton.setVisibility(View.INVISIBLE);
                    scoresButton.setVisibility(View.INVISIBLE);
                }

                //checkIfElimination();
                checkIfWinner(rollLabel);

            }
        });

        rollButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Roll the dice.
                int rollVal = dice.getDiceRoll();


                if (!haveRolled) {
                    // When the active player hasn't rolled and presses Roll Dice

                    // Make the HIDE button (aka Bull button) visible.
                    bullButton.setVisibility(View.VISIBLE);

                        // Make the Roll Button unclickable.
                    rollButton.setEnabled(false);

                    // Give the player their dice roll value.
                    Database.activePlayer.setRollValue(rollVal);

                    // Check if the user rolled a 31 or a 21:
                    // If 21 --> remove a point from every player, and tell the user this.
                    // If 31 --> it's a clear, so go to the next player without removing any points.
                    if (rollVal == 21 || rollVal == 31) {
                        specialRoll(rollVal, bullButton, rollLabel);
                    } else {

                        specialValueRolled = false;

                        // Display the roll.
                        rollLabel.setText(Database.activePlayer.getName() + ", you've rolled a " + rollVal + "!");

                        // Change the bull / hide button back to saying "Hide"
                        bullButton.setBackgroundColor(Color.GRAY);
                        bullButton.setText("Hide Roll");
                        haveRolled = true;
                        rollIsHidden = false;
                    }
                }

                //checkIfElimination();
                checkIfWinner(rollLabel);

            }
        });

        bullButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String str = ""; // General purpose string.
                haveRolled = false; // Set haveRolled to false so the active player can roll.

                if (!rollIsHidden) {
                    // What happens when you press Hide Roll.
                    Database.setNextActivePlayer();

                    // Make the Roll Dice button clickable.
                    rollButton.setEnabled(true);

                    str = Database.previousPlayer.getName() + "'s roll is hidden!\nYou may call Bull or roll!";
                    rollLabel.setText(str);

                    bullButton.setBackgroundColor(Color.RED);
                    bullButton.setText("BULL!");

                    rollIsHidden = true;
                } else if (!specialValueRolled){
                    // What happens when you call Bull on someone.
                    // Display what the last player rolled.
                    str = Database.previousPlayer.getName() + " rolled a " + Database.previousPlayer.getRollValue() + ". Who lost a point?";

                    rollLabel.setText(str);

                    bullButton.setVisibility(View.INVISIBLE);

                    rollIsHidden = false;

                    // Show the scoring buttons and set their text.
                    subtractYourselfButton.setVisibility(View.VISIBLE);
                    subtractPreviousButton.setVisibility(View.VISIBLE);

                    str = "-1 Point For " + Database.activePlayer.getName();
                    subtractYourselfButton.setText("-1 Point For " + Database.activePlayer.getName());

                    str = "-1 Point For " + Database.previousPlayer.getName();
                    subtractPreviousButton.setText("-1 Point For " + Database.previousPlayer.getName());

                } else if (specialValueRolled) {
                    // If a 21 or 31 is rolled and the user presses the bull button (which at this point reads "NEXT PLAYER":
                    // Tell the next person in line to roll (aka set them as next active user).
                    // Disable bull button and set specialValueRolled = false.

                    Database.setNextActivePlayer();

                    rollLabel.setText(Database.activePlayer.getName() + ", roll the dice!");

                    bullButton.setVisibility(View.INVISIBLE);

                    specialValueRolled = false;

                    rollButton.setEnabled(true);
                }

                //checkIfElimination();
                checkIfWinner(rollLabel);

            }


        });
    }

    private static void removePointsFromEveryoneExcept(Player p) {
        Iterator<Player> itr = Database.playerList.iterator();

        while (itr.hasNext()) {
            Player currPlayer = itr.next();

            if (!currPlayer.getName().equals(p.getName())) {
                currPlayer.decreaseScore();
            }
        }
    }

    private void specialRoll(int val, Button b, TextView t) {
        specialValueRolled = true;
        b.setText("NEXT PLAYER");

        if (val == 21) {
            t.setText(Database.activePlayer.getName() + ", you've rolled a 21! Everyone except " + Database.activePlayer.getName() + " loses a point!");

            removePointsFromEveryoneExcept(Database.activePlayer);

        } else {
            t.setText(Database.activePlayer.getName() + ", you've rolled a 31! The play is cleared!");


        }
    }

    private void resetGame() {
        Database.playerList.clear();
        Database.numPlayers = 0;
        Database.playerIndex = 0;
        Database.activePlayer = null;
        Database.previousPlayer = null;
        Database.playersEntered = false;

        finish();

        startActivity(new Intent(GameActivity.this, HomeActivity.class));
    }

    public void checkIfWinner(TextView t) {
        if (Database.playerList.size() == 1) {
            // TODO Winner logic
            Player winner = Database.playerList.get(0);

            t.setText(winner.getName() + " has won!");



            // This is so the user has time to see who won.
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    resetGame();
                }
            }, 5000);

        }
    }

    /*private void checkIfElimination() {
        Iterator<Player> itr = Database.playerList.iterator();

        while (itr.hasNext()) {
            Player currPlayer = itr.next();

            if (currPlayer.getScore() <= 0) {

                try {
                    Database.playerList.remove(currPlayer);
                    //showAlert(currPlayer, false);
                } catch (Exception e) {}

            }
        }
    }*/

    /*private static void showEndGame(Player p, TextView t) {
        /*AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();

        if (isWinner) {
            alertDialog.setTitle("Game over!");
            alertDialog.setMessage(p.getName() + " has won the game!");
        } else {

            alertDialog.setTitle("Player has been eliminated!");
            alertDialog.setMessage(p.getName() + " has been eliminated!");

        }

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();*/

        //t.setText(p.getName() + " has won!");

    //}
}
