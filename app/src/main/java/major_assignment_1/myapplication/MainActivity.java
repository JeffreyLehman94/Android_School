package major_assignment_1.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    // Vars given in project
    private char mBoard[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final int BOARD_SIZE = 9;
    public static final char HUMAN_PLAYER = 'X';
    public static final char COMPUTER_PLAYER = 'O';
    private Random mRand;


    private Boolean gameOver=true;
    private int turn;
    private String message;
    private String gameStatus;
    Boolean HUMAN_TURN=true;


     Button startButton;
     Button square1;
     Button square2;
     Button square3;
     Button square4;
     Button square5;
     Button square6;
     Button square7;
     Button square8;
     Button square9;
     TextView GameStatusText;

    private Button bBoard[] = {square1, square2, square3, square4, square5, square6, square7, square8, square9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = (Button) findViewById(R.id.startButton);
        GameStatusText = (TextView) findViewById(R.id.gameText);
        square1 = (Button) findViewById(R.id.square1);
        square2 = (Button) findViewById(R.id.square2);
        square3 = (Button) findViewById(R.id.square3);
        square4 = (Button) findViewById(R.id.square4);
        square5 = (Button) findViewById(R.id.square5);
        square6 = (Button) findViewById(R.id.square6);
        square7 = (Button) findViewById(R.id.square7);
        square8 = (Button) findViewById(R.id.square8);
        square9 = (Button) findViewById(R.id.square9);
        square1.setOnClickListener(this);
        square2.setOnClickListener(this);
        square3.setOnClickListener(this);
        square4.setOnClickListener(this);
        square5.setOnClickListener(this);
        square6.setOnClickListener(this);
        square7.setOnClickListener(this);
        square8.setOnClickListener(this);
        square9.setOnClickListener(this);
        createArray();
        GameStatusText.setText("PRESS NEW GAME TO BEGIN");

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startNewGame();
            }
        });

    }

    private void createArray(){
        bBoard[0] = square1;
        bBoard[1] = square2;
        bBoard[2] = square3;
        bBoard[3] = square4;
        bBoard[4] = square5;
        bBoard[5] = square6;
        bBoard[6] = square7;
        bBoard[7] = square8;
        bBoard[8] = square9;
    }

    /**
     * Start new game by clearing the board and set the starting values
     */
    private void startNewGame() {
        clearGrid();
        setStartingValues();
        for(int i=0;i!=9;i++){
            mBoard[i]='N';
        }
    }

    /**
     * set starting values, gameOver = false, turn = 1, message = Player X’s turn, game status = “ “
     */
    private void setStartingValues() {
        gameOver = false;
        turn = 1;
        GameStatusText.setText("PLAYER TURN (X)");
        gameStatus = "";
        HUMAN_TURN=true;
    }

    /**
     * Clear the board of all X's and O's.
     */
    public void clearGrid() {
        square1.setText(" ");
        square2.setText(" ");
        square3.setText(" ");
        square4.setText(" ");
        square5.setText(" ");
        square6.setText(" ");
        square7.setText(" ");
        square8.setText(" ");
        square9.setText(" ");

    }

    /**
     * Check for a winner and set gameOver to true if there is a winner or tie
     */
    private void checkForGameOver() {
        if (checkForWinner() == 1) {
            GameStatusText.setText("GAME OVER: TIE");
            gameOver=true;
        } else if (checkForWinner() == 2) {
            GameStatusText.setText("GAME OVER: X WON");
            gameOver=true;
        } else if (checkForWinner() == 3) {
            GameStatusText.setText("GAME OVER: O WON");
            gameOver=true;
        }
    }




    // Check for a winner.  Return
    //  0 if no winner or tie yet
    //  1 if it's a tie
    //  2 if X won
    //  3 if O won
    private int checkForWinner() {

        // Check horizontal wins
        for (int i = 0; i <= 6; i += 3) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i + 1] == HUMAN_PLAYER &&
                    mBoard[i + 2] == HUMAN_PLAYER)

                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i + 1] == COMPUTER_PLAYER &&
                    mBoard[i + 2] == COMPUTER_PLAYER)

                return 3;
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i + 3] == HUMAN_PLAYER &&
                    mBoard[i + 6] == HUMAN_PLAYER)

                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i + 3] == COMPUTER_PLAYER &&
                    mBoard[i + 6] == COMPUTER_PLAYER)

                return 3;
        }

        // Check for diagonal wins
        if ((mBoard[0] == HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[8] == HUMAN_PLAYER) ||
                (mBoard[2] == HUMAN_PLAYER &&
                        mBoard[4] == HUMAN_PLAYER &&
                        mBoard[6] == HUMAN_PLAYER))
            return 2;
        if ((mBoard[0] == COMPUTER_PLAYER &&
                mBoard[4] == COMPUTER_PLAYER &&
                mBoard[8] == COMPUTER_PLAYER) ||
                (mBoard[2] == COMPUTER_PLAYER &&
                        mBoard[4] == COMPUTER_PLAYER &&
                        mBoard[6] == COMPUTER_PLAYER))
            return 3;

        // Check for tie
        for (int i = 0; i < BOARD_SIZE; i++) {
            // If we find a number, then no one has won yet
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
                return 0;
        }

        // If we make it through the previous loop, all places are taken, so it's a tie
        return 1;
    }

    public void onClick(View v) {
        if (gameOver == false) {

            if (HUMAN_TURN) {
                switch (v.getId()) {

                    case R.id.square1:
                        if (square1.getText().equals(" "))
                            square1.setText("X");
                        HUMAN_TURN = false;
                        mBoard[0] = HUMAN_PLAYER;
                        //CHECK IF WIN

                        break;
                    case R.id.square2:
                        if (square2.getText().equals(" "))
                            square2.setText("X");
                        HUMAN_TURN = false;
                        mBoard[1] = HUMAN_PLAYER;
                        break;
                    case R.id.square3:
                        if (square3.getText() == " ")
                            square3.setText("X");
                        HUMAN_TURN = false;
                        mBoard[2] = HUMAN_PLAYER;
                        break;
                    case R.id.square4:
                        if (square4.getText().equals(" "))
                            square4.setText("X");
                        HUMAN_TURN = false;
                        mBoard[3] = HUMAN_PLAYER;
                        break;
                    case R.id.square5:
                        if (square5.getText().equals(" "))
                            square5.setText("X");
                        HUMAN_TURN = false;
                        mBoard[4] = HUMAN_PLAYER;
                        break;
                    case R.id.square6:
                        if (square6.getText().equals(" "))
                            square6.setText("X");
                        HUMAN_TURN = false;
                        mBoard[5] = HUMAN_PLAYER;
                        break;
                    case R.id.square7:
                        if (square7.getText().equals(" "))
                            square7.setText("X");
                        HUMAN_TURN = false;
                        mBoard[6] = HUMAN_PLAYER;
                        break;
                    case R.id.square8:
                        if (square8.getText().equals(" "))
                            square8.setText("X");
                        HUMAN_TURN = false;
                        mBoard[7] = HUMAN_PLAYER;
                        break;
                    case R.id.square9:
                        if (square9.getText().equals(" "))
                            square9.setText("X");
                        HUMAN_TURN = false;
                        mBoard[8] = HUMAN_PLAYER;
                        break;
                }

            }
            GameStatusText.setText("COMPUTER TURN (O)");
            checkForGameOver();
            if (HUMAN_TURN == false && gameOver==false) {

                getComputerMove();
                GameStatusText.setText("PLAYER TURN (X)");
                checkForGameOver();

            }
        }
    }

    private void getComputerMove() {
        int move;


        // First see if there's a move O can make to win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];
                mBoard[i] = COMPUTER_PLAYER;


                if (checkForWinner() == 3) {
                    bBoard[i].setText("O");
                    mBoard[i]=COMPUTER_PLAYER;
                    HUMAN_TURN=true;
                    return;
                } else
                    mBoard[i] = curr;
            }
        }

        // See if there's a move O can make to block X from winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
                char curr = mBoard[i];   // Save the current number
                mBoard[i] = HUMAN_PLAYER;
                if (checkForWinner() == 2) {
                    bBoard[i].setText("O");
                    mBoard[i]=COMPUTER_PLAYER;
                    HUMAN_TURN=true;
                    return;
                } else
                    mBoard[i] = curr;
            }
        }

        // Generate random move
        do {
            mRand = new Random();
            move = mRand.nextInt(9);
        } while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER);

        bBoard[move].setText("O");
        mBoard[move]=COMPUTER_PLAYER;
        HUMAN_TURN=true;
    }


}

