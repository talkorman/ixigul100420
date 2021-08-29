package com.example.ixigul;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.ls.LSOutput;

public class MainActivity extends AppCompatActivity {
    String[] images = {"cell", "x", "0"};
    ImageButton[][] btns;
    TextView tvMessage;
    UserPlayer user;
    MobilePlayer mobile;
    EditText userScore, mobileScore;
    Board board;
    final int SCORE = 10;
    Player cPlayer = user;
    int idRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int[][] btnIds = {{R.id.btn00, R.id.btn01, R.id.btn02},
                {R.id.btn10, R.id.btn11, R.id.btn12},
                {R.id.btn20, R.id.btn21, R.id.btn22}};
        board = new Board();
        btns = new ImageButton[3][3];
        user = new UserPlayer("Your");
        mobile = new MobilePlayer("Mobile");
        userScore = findViewById(R.id.etnUserScore);
        mobileScore = findViewById(R.id.etnMobileScore);
        tvMessage = findViewById(R.id.tvMessage);
        userScore.setText("0");
        mobileScore.setText("0");
        cPlayer = user;
        final ImageButton choseX = findViewById(R.id.btnChoseX);
        final ImageButton chose0 = findViewById(R.id.btnChose0);
        choseX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseX.setEnabled(false);
                chose0.setEnabled(false);
                user.setSimbol(1);
                mobile.setSimbol(2);
                choseX.setImageResource(R.drawable.x);
                chose0.setImageResource(R.drawable.cell);
                game();

            }
        });
        chose0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseX.setEnabled(false);
                chose0.setEnabled(false);
                user.setSimbol(2);
                mobile.setSimbol(1);
                chose0.setImageResource(R.drawable.o);
                choseX.setImageResource(R.drawable.cell);
                game();
            }
        });
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btns[i][j]  = (ImageButton)findViewById(btnIds[i][j]);
                btns[i][j].setBackgroundResource(R.drawable.cell);
                btns[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(v.isEnabled() && user.chosenSimbol > 0 && cPlayer == user) {
                            v.setEnabled(false);
                            if (user.chosenSimbol == 1) {
                                v.setBackgroundResource(R.drawable.x);
                            } else {
                                v.setBackgroundResource(R.drawable.o);
                            }
                            for (int k = 0; k < 3; k++) {
                                for (int l = 0; l < 3; l++) {
                                    if(v.getId() == btnIds[k][l]){
                                        user.chose(board, k, l);
                                        cPlayer = mobile;
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

    }
    public void game(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cPlayer = user;
                while (user.score < SCORE & mobile.score < SCORE) {
                   reset();
                    message(cPlayer);
                   printStatus();
                    do {
                      if(cPlayer == user){
                          delay(500);
                          message(cPlayer);
                          while (cPlayer == user) {
                          }
                          delay(500);
                          if (board.winnerIs() > 0){cPlayer = user; break;}
                          message(cPlayer);
                          delay(500);
                          mobileTurn();
                          while (cPlayer == mobile && havePlace()){}
                          delay(500);
                          message(cPlayer);
                          while (cPlayer == user && havePlace()){}
                          delay(500);
                          if (board.winnerIs() > 0){cPlayer = mobile; break;}
                          message(cPlayer);
                          delay(500);
                      }else if(cPlayer == mobile) {
                          delay(500);
                          message(cPlayer);
                          delay(500);
                          mobileTurn();
                          while (cPlayer == mobile && havePlace()) {
                          }
                          delay(500);
                          if (board.winnerIs() > 0) {
                              cPlayer = mobile;
                              break;
                          }
                          message(cPlayer);
                          delay(500);

                          message(cPlayer);
                          while (cPlayer == user  && havePlace()) {
                          }
                          delay(500);
                          if (board.winnerIs() > 0) {
                              cPlayer = user;
                              break;
                          }
                          message(cPlayer);
                          delay(500);

                      }
                    }while (havePlace());
                    message(null);
                    delay(750);
                       int win = board.winnerIs();
                       if(win > 0){
                           cPlayer.score++;
                           userScore.postDelayed(() -> {userScore.setText(String.valueOf(user.score));}, 10);
                           mobileScore.postDelayed(() -> {mobileScore.setText(String.valueOf(mobile.score));}, 10);
                       }
                    }
                }
                public void reset(){
                    System.out.println("reset");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            board.boardArray[i][j] = 0;
                            final ImageButton btn = btns[i][j];
                            btn.post(()->btn.setEnabled(true));
                            btn.post(()->btn.setBackgroundResource(R.drawable.cell));
                        }
                    }
                    System.out.println("after reset");
                    delay(2000);
                }
                public void userTurn() {

                }
                public void mobileTurn(){
                    System.out.println("mobile turn");
                    int[] loc = mobile.chose(board, user.chosenSimbol);
                    board.boardArray[loc[0]][loc[1]] = mobile.chosenSimbol;
                    printStatus();
                    btns[loc[0]][loc[1]].setEnabled(false);
                    if (mobile.chosenSimbol == 1) {
                        btns[loc[0]][loc[1]].post(()->btns[loc[0]][loc[1]].setBackgroundResource(R.drawable.x));
                    } else if (mobile.chosenSimbol == 2) {
                        btns[loc[0]][loc[1]].post(()->btns[loc[0]][loc[1]].setBackgroundResource(R.drawable.o));
                    }
                    cPlayer = user;
                }
                public void printStatus(){
                    System.out.println("status:");
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            System.out.println("at " + k + ", " + l + " have: " + board.boardArray[k][l]);
                        }
                    }
                }
                public boolean havePlace(){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (board.boardArray[i][j] == 0){
                                return true;
                            }
                        }
                    }
                    return false;
                }
                public void delay(int ms){
try{
    Thread.sleep(ms);
}catch (InterruptedException ex){
    Thread.currentThread().interrupt();
}
                }
                public void message(Player player){
                    String message = player == null ? "Are you ready?" : ((player == user ? user.getName() : mobile.getName()) + " turn");
                    tvMessage.post(()->tvMessage.setText(message));
                }
        };
        Thread round = new Thread(runnable);
        round.start();
    }
}