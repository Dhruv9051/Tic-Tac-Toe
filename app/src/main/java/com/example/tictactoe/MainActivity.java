package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] isTapped = {2,2,2,2,2,2,2,2,2};
    int[][] winning = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    int tieCounter = 0;
    int crossCounter = 0;
    int zeroCounter = 0;
    boolean good = true;

    @SuppressLint("SetTextI18n")
    public void dropIt(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(isTapped[tappedCounter] == 2 && gameActive) {
            tieCounter++;
            counter.setTranslationY(-1500);
            isTapped[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winn : winning) {
                if (isTapped[winn[0]] == isTapped[winn[1]] && isTapped[winn[1]] == isTapped[winn[2]] && isTapped[winn[0]] != 2) {
                    gameActive = false;
                    String winner;
                    if (activePlayer == 1){
                        winner = "Cross";
                        crossCounter++;
                    }
                    else {
                        winner = "Zero";
                        zeroCounter++;
                    }

                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    TextView winnerTextView = (TextView) findViewById(R.id.textView);
                    //TextView counterZero = (TextView) findViewById(R.id.textView2);
                    //TextView counterCross = (TextView) findViewById(R.id.textView3);
                    winnerTextView.setText(winner + " won!");
                    //counterZero.setText("Zero: " + zeroCounter);
                    //counterCross.setText("Cross: " + crossCounter);
                    playAgain.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    good = false;
                }
            }
           if(tieCounter == 9 && good){
                Button playAgain = (Button) findViewById(R.id.playAgain);
                TextView winnerTextView = (TextView) findViewById(R.id.textView);
                winnerTextView.setText("It's a DRAW!");
                playAgain.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }
    }


    public void playAgain(View view){
        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView winnerTextView = (TextView) findViewById(R.id.textView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0;i <gridLayout.getChildCount();i++){
            ImageView counter  = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        tieCounter = 0;
        good = true;
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(isTapped, 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
