package com.example.tictactoe;


import androidx.appcompat.app.AppCompatActivity;
import java.lang.*;



import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0:Red,1:Yellow,2:Game state.

    public int activePlayer=0;
    public Integer[] gameState={2,2,2,2,2,2,2,2,2};
    public int[] [] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; //These are the index of winning positions on the grid
    public String msg;
    public boolean gameActive=true;
    public boolean replay=true;
    public int check=2;





    public void fade(View view)
    {
            ImageView imageView = (ImageView) view;
            int tapCounter=Integer.parseInt(imageView.getTag().toString()); //Get the tag of the image view which has been tapped

            if(gameState[tapCounter]==2 && gameActive) { //prevents the same box from getting filled again

                gameState[tapCounter] = activePlayer;
                imageView.setTranslationY(-1500);

                if (activePlayer == 0) {
                    imageView.setImageResource(R.drawable.red);
                    activePlayer = 1;
                } else {
                    imageView.setImageResource(R.drawable.yellow);
                    activePlayer = 0;

                }
                imageView.animate().translationYBy(1500).rotationXBy(1800).setDuration(300);


                for (int[] winningPosition : winningPositions) { // Winning condition check.
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                        if (activePlayer == 1) {
                            msg = "Red has won";
                            gameActive=false;

                        } else if (activePlayer == 0) {
                            msg = "Yellow has won";
                            gameActive=false;
                        }
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(msg);
                        Button button3=(Button) findViewById(R.id.button3);
                        button3.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);


                    }
                }


            }


            replay=Arrays.asList(gameState).contains(check);// to check whether the game is a draw i.e The value "2" doesn't exist in the gameState Array.

             if(replay==false)
            {
            TextView textView = findViewById(R.id.textView);
            textView.setText("It's a draw!");
            Button button3=(Button) findViewById(R.id.button3);
            button3.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            }

    }


    public void playAgain(View view) {            //Play again button

        Button playAgainButton = (Button) findViewById(R.id.button3);

        TextView winnerTextView = (TextView) findViewById(R.id.textView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) { // Clear red and yellow circles from the grid

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {  // Set gameState array to initial values.

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}