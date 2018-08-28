package bull.bull;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {

    public static List<TextView> labelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        labelList = new ArrayList<TextView>();

        final TextView s1 = (TextView) findViewById(R.id.score_1);
        final TextView s2 = (TextView) findViewById(R.id.score_2);
        final TextView s3 = (TextView) findViewById(R.id.score_3);
        final TextView s4 = (TextView) findViewById(R.id.score_4);
        final TextView s5 = (TextView) findViewById(R.id.score_5);
        final TextView s6 = (TextView) findViewById(R.id.score_6);

        labelList.add(s1);
        labelList.add(s2);
        labelList.add(s3);
        labelList.add(s4);
        labelList.add(s5);
        labelList.add(s6);

        // Set labels invisible:
        Iterator<TextView> itr = labelList.iterator();

        while (itr.hasNext()) {
            TextView currTV = itr.next();
            currTV.setVisibility(View.INVISIBLE);
        }

        displayScores();

    }

    private static void displayScores() {
        for (int i = 0; i < Database.playerList.size(); i++) {
            TextView currTV = labelList.get(i);
            Player currPlayer = Database.playerList.get(i);

            currTV.setText(currPlayer.getName() + "'s score: " + currPlayer.getScore());
            currTV.setVisibility(View.VISIBLE);
        }
    }
}
