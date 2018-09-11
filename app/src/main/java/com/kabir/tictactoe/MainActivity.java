package com.kabir.tictactoe;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameIsActive = true;
    int activePlayer=0;

    int [] gamestate = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6}, {1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedcounter] == 2 && gameIsActive){
            gamestate[tappedcounter]=activePlayer;
            counter.setTranslationY(-1000f);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }

        counter.animate().translationYBy(1000f).setDuration(300);

        for (int[]winningPositions : winningPositions)
        {
            if( gamestate[winningPositions[0]] == gamestate[winningPositions[1]]  &&
                    gamestate[winningPositions[1]] == gamestate[winningPositions[2]] &&
                    gamestate[winningPositions[0]] != 2 )  {

                gameIsActive = false;
                String winner = " Red";

                if(gamestate[winningPositions[0]]==0)
                {
                    winner = "Yellow";
                }

                TextView winnermessage =(TextView) findViewById(R.id.winnermessage);

                winnermessage.setText(winner + " has won");

                LinearLayout layout =(LinearLayout) findViewById(R.id.playagainlayout);

                layout.setVisibility(View.VISIBLE);

            }
            else
            {
                boolean gameIsOver = true;
                for(int counterstate: gamestate){
                    if(counterstate==2) gameIsOver=false;
                }
                if(gameIsOver) {
                    TextView winnermessage =(TextView) findViewById(R.id.winnermessage);

                    winnermessage.setText("It's draw ");

                    LinearLayout layout =(LinearLayout) findViewById(R.id.playagainlayout);

                    layout.setVisibility(View.VISIBLE);

                }
            }
        }
    }
    }

    public void playagain (View view)
    {
        gameIsActive = true;
        LinearLayout layout =(LinearLayout) findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer =0;
        for(int i =0; i<gamestate.length; i++)
        {
            gamestate[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        for(int i =0; i<gridLayout.getChildCount(); i++)
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}