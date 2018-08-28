package bull.bull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Buttons
        final Button newGameButton = (Button) findViewById(R.id.new_game_button);
        final Button numPlayersButton = (Button) findViewById(R.id.num_players_button);
        final Button rulesButton = (Button) findViewById(R.id.rules_button);

        if (Database.playersEntered) {
            newGameButton.setEnabled(true);
        } else {
            newGameButton.setEnabled(false);
        }


        // OnClickListeners------------------------------------------

        // NEW GAME BUTTON
        newGameButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, GameActivity.class));
            }
        });

        // NUM PLAYERS BUTTON
        numPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NumberOfPlayersActivity.class));
            }
        });

        // RULES BUTTON
        rulesButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, RulesActivity.class));
           }
        });
    }
}
