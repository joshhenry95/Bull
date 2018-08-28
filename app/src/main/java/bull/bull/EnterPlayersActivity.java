package bull.bull;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterPlayersActivity extends AppCompatActivity {
    static int enteredPlayerCnt = 0; // How many names have been entered.
    static int cnt = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_players);

        final TextView label = (TextView) findViewById(R.id.label);
        final EditText inputText = (EditText) findViewById(R.id.text_input);
        final Button acceptButton = (Button) findViewById(R.id.accept_button);

        label.setText("Enter player #1's name:");

        acceptButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Make it so each name has to be unique (?)

                if (!inputText.getText().equals("")) {

                    enteredPlayerCnt++;
                    cnt++;
                    if (enteredPlayerCnt <= Database.numPlayers) {


                        // Edit top label
                        if (enteredPlayerCnt != Database.numPlayers) {
                            label.setText("Enter player #" + (cnt) + "'s name:");
                        }

                        // ... then create a Player object and add it to the player list.
                        Player currPlayer = new Player(inputText.getText().toString());
                        Database.playerList.add(currPlayer);

                        // Set the text box's text to nothing.
                        inputText.setText("");
                    }

                    // Check if we've reached the num of players specified by the user.
                    if (enteredPlayerCnt == Database.numPlayers) {
                        // If so, go to the main screen.
                        cnt = 1;

                        Database.playersEntered = true;

                        startActivity(new Intent(EnterPlayersActivity.this, HomeActivity.class));
                    }
                }
            }
        });
    }
}
