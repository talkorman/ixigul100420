package com.example.ixigul;

import android.widget.ImageButton;

public class UserPlayer extends Player{

    UserPlayer(String name) {
        super(name);
    }

    @Override
        public void chose(Board board, int x, int y) {
          board.boardArray[x][y] = this.chosenSimbol;
        }
    }



