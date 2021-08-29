package com.example.ixigul;

import android.widget.ImageButton;

public class Board {
    int[][] boardArray = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        public int winnerIs(){
            int row = 0, col = 0, slantR = 0, slantL = 0;
            for (int k = 1; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0, l = 2; j < 3; j++, l--) {
                        if(boardArray[i][j] == k){row++;}
                        if(boardArray[j][i] == k){col++;}
                        if(boardArray[j][j] == k){slantR++;}
                        if(boardArray[l][j] == k){slantL++;}
                        if(row > 2 || col > 2 || slantR > 2 || slantL > 2){
                            return k;
                        }
                    }
                    row = 0;
                    col = 0;
                    slantR = 0;
                    slantL = 0;
                }
            }

            return 0;
        }
    }

