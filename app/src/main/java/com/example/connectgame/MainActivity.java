package com.example.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};//2 means unplayed.
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "Red";
                    gameIsActive = false;
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Blue";
                    }

                    TextView textView = findViewById(R.id.textView);
                    textView.setText(winner + " has won!");
                    LinearLayout linearLayout = findViewById(R.id.play_again);
                    linearLayout.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view) {
        //make dialog invisible
        LinearLayout linearLayout = findViewById(R.id.play_again);
        linearLayout.setVisibility(View.INVISIBLE);

        gameIsActive = true;
        activePlayer = 0;
        //setting gameState as unplayed
        Arrays.fill(gameState, 2);//replacement of for loop setting all elements = 2.
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}