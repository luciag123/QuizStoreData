package edu.tjhsst.quizstoredata;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "Toast!!!", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences mSettings = this.getSharedPreferences("Settings", 0);
        final SharedPreferences.Editor editor = mSettings.edit();
        final TextView highScoreLabel = (TextView) findViewById(R.id.score);
        highScoreLabel.setText("    " +mSettings.getInt("highScore",0)+"/5");
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText q1 = (EditText) findViewById(R.id.q1);
                String val1 = q1.getText().toString();
                EditText q2 = (EditText) findViewById(R.id.q2);
                String val2 = q2.getText().toString();
                EditText q3 = (EditText) findViewById(R.id.q3);
                String val3 = q3.getText().toString();
                EditText q4 = (EditText) findViewById(R.id.q4);
                String val4 = q4.getText().toString();
                EditText q5 = (EditText) findViewById(R.id.q5);
                String val5 = q5.getText().toString();
                TextView yourScore = (TextView) findViewById(R.id.yourScore);
                int currentScore = scoreQuiz(val1, val2, val3, val4, val5);
                yourScore.setText("Your score:     " + currentScore + "/5");
                int currentHighScore = mSettings.getInt("highScore", 0);
                Log.w("current saved", currentHighScore+"");
                if(currentScore > currentHighScore)
                {
                    editor.putInt("highScore", currentScore);
                    editor.commit();
                    Log.w("new high score", mSettings.getInt("highScore",0)+"");
                    highScoreLabel.setText(currentScore + "/5");
                }
            }
        });
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("highScore", 0);
                editor.commit();
                highScoreLabel.setText("0/5");
            }
        });
    }
    public int scoreQuiz(String a, String b, String c, String d, String e){
        int score = 0;
        if(a.equalsIgnoreCase("Cassie"))
            score++;
        if(b.equalsIgnoreCase("Buzz"))
            score++;
        if(c.equalsIgnoreCase("Puce"))
            score++;
        if(d.equalsIgnoreCase("Dorothy"))
            score++;
        if(e.equalsIgnoreCase("Brain"))
            score++;
        return score;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

