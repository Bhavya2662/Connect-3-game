package com.example.connectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.util.Arrays;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    boolean gameIsActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};//2 means unplayed.
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

//    public void dropIn(View view) {
//        ImageView counter = (ImageView) view;
//
//        int tappedCounter = Integer.parseInt(counter.getTag().toString());
//
//        if (gameState[tappedCounter] == 2) {
//            gameState[tappedCounter] = activePlayer;
//
//            counter.setTranslationY(-1000f);
//            if (activePlayer == 0) {
//                counter.setImageResource(R.drawable.blue);
//                activePlayer = 1;
//            } else {
//
//                counter.setImageResource(R.drawable.red);
//                activePlayer = 0;
//            }
//            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
//
//
//        }
//        for (int[] winningPosition : winningPositions) {
//            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
//                gameIsActive = false;
//                String winner = "Red";
//
//                if (gameState[winningPosition[0]] == 0) {
//                    winner = "Blue";
//                }
//
//                TextView textView = findViewById(R.id.textView);
//                textView.setText(winner + " has won!");
//                LinearLayout linearLayout = findViewById(R.id.play_again);
//                linearLayout.setVisibility(VISIBLE);
//
//            }
//        }
//    }
    public void dropIn(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(gameState[tappedImage]==2 && gameIsActive){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.red);
                activePlayer = 1;
            }
            else {
                img.setImageResource(R.drawable.blue);
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){



                LinearLayout ll = (LinearLayout)findViewById(R.id.play_again);

                Button btn = new Button(this);
                btn.setText("Play again!");
                btn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                ll.addView(btn);



                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //make dialog invisible
                        LinearLayout linearLayout = findViewById(R.id.play_again);
                        linearLayout.animate().alpha(0).setDuration(100);

                        gameIsActive = true;
                        activePlayer = 0;
                        //setting gameState as unplayed
                        for(int i = 0; i<gameState.length; i++){
                            gameState[i] = 2;
                        }
                        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
                        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
                    }
                });
                // somebody has won! Who?
                String winnerStr;
                gameIsActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "P1";
                }
                else {
                    winnerStr = "P2";
                }
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(winnerStr + " has won!");
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.play_again);


                linearLayout.animate().alpha(1).setDuration(100);

            }else{

                boolean gameIsOver = true;
                for (int counterState : gameState){
                    if(counterState ==2)
                        gameIsOver=false;
                }
                if(gameIsOver){
                    TextView textView = (TextView)findViewById(R.id.textView);
                    textView.setText("It's a draw!");
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.play_again);
                    linearLayout.animate().alpha(1).setDuration(100);

                }
            }

        }

    }


//    public void playAgain(View view) {
//        //make dialog invisible
//        LinearLayout linearLayout = findViewById(R.id.play_again);
//        linearLayout.animate().alpha(0).setDuration(100);
//
//        gameIsActive = true;
//        activePlayer = 0;
//        //setting gameState as unplayed
//        for(int i = 0; i<gameState.length; i++){
//            gameState[i] = 2;
//        }
//        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
//        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}