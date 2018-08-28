package bull.bull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NumberOfPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_players);

        // Buttons
        final Button threePlayersButton = (Button) findViewById(R.id.three_players_button);
        final Button fourPlayersButton = (Button) findViewById(R.id.four_players_button);
        final Button fivePlayersButton = (Button) findViewById(R.id.five_players_button);
        final Button sixPlayersButton = (Button) findViewById(R.id.six_players_button);
        // ON CLICK LISTENERS ---------------------------

        // Three Players
        threePlayersButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database.numPlayers = 3;
                goToEnterPlayers();
            }
        });

        // Four Players
        fourPlayersButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database.numPlayers = 4;
                goToEnterPlayers();
            }
        });

        // Five Players
        fivePlayersButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database.numPlayers = 5;
                goToEnterPlayers();
            }
        });

        // Six Players
        sixPlayersButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Database.numPlayers = 6;
                goToEnterPlayers();
            }
        });
    }

    protected void goToEnterPlayers() {
        EnterPlayersActivity.enteredPlayerCnt = 0;
        startActivity(new Intent(NumberOfPlayersActivity.this, EnterPlayersActivity.class));

    }
}
