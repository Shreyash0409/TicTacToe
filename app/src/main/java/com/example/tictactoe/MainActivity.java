package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // yellow = 0 , red = 1 , empty = 2

    int activePlayer = 0;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [] [] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},{1,4,7},{2,5,8},{0,4,8}, {2,4,6}};
    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameActive && gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
                for (int[] winningPosition : winningPositions) {

                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                        //Someone has Won
                        String winner;
                        gameActive = false;
                        if (activePlayer == 1) {
                            winner = "Yellow";
                        }
                        else {
                            winner = "Red";
                        }

                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        Button playAgain = (Button) findViewById(R.id.playAgainButton);

                        winnerTextView.setText(winner + " has won");
                        playAgain.setVisibility(View.VISIBLE);
                        winnerTextView.setVisibility(View.VISIBLE);
                    }

            }
            boolean emptySquare = false;
            for (int squareState : gameState) {
                if (squareState == 2) {
                    emptySquare = true;
                    break;
                }
            }
            if (!emptySquare && gameActive) {
                // Game is a draw
                gameActive = false;

                TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                Button playAgain = (Button) findViewById(R.id.playAgainButton);

                winnerTextView.setText("No one has won the game, Try Again!");
                playAgain.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }

        }
    }

    public void playAgain(View view){

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        Button playAgain = (Button) findViewById(R.id.playAgainButton);

        playAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

        activePlayer = 0;
        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        gameActive = true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}