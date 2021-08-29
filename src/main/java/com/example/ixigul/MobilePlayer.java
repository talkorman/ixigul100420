package com.example.ixigul;

import java.util.Random;

public class MobilePlayer extends Player {

    MobilePlayer(String name) {
        super(name);
    }

    @Override
    public int[] chose(Board board, int oCh) {
        int[] a = {2, 2};
        int mobile1Cell = 0, user1Cell = 0;
        System.out.println("mobile chose");
        int colNum1 =0, rowNum1 = 0,  slantNumR1 = 0, slantNumL1 = 0;
        int[] simbol = {chosenSimbol, oCh};
        //case mobile has 2 in line
        for (int m = 0; m < 2; m++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0, l = 2; j < 3; j++, l--) {
                    System.out.println("simbol: " + simbol[m]);
                    if (board.boardArray[i][j] == simbol[m]) {
                        rowNum1++;
                    }
                    if (rowNum1 == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (board.boardArray[i][k] == 0) {
                                a[0] = i;
                                a[1] = k;
                                return a;
                            }
                        }
                    }
                    if (board.boardArray[j][i] == simbol[m]) {
                        colNum1++;
                    }
                    if (colNum1 == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (board.boardArray[k][i] == 0) {
                                a[0] = k;
                                a[1] = i;
                                return a;
                            }
                        }
                    }

                    if (i == 0) {
                        if (board.boardArray[j][j] == simbol[m]) {
                            slantNumR1++;
                        }
                    if (slantNumR1 == 2) {
                        for (int k = 0; k < 3; k++) {
                            if (board.boardArray[k][k] == 0) {
                                a[0] = k;
                                a[1] = k;
                                return a;
                            }
                        }
                    }


                   if (board.boardArray[j][l] == simbol[m]) {
                        slantNumL1++;
                    }
                    if (slantNumL1 == 2) {
                        for (int k = 0, n = 2; k < 3; k++, n--) {
                            if (board.boardArray[n][k] == 0) {
                                a[0] = n;
                                a[1] = k;
                                return a;
                            }
                        }
                    }
                    }

                }
                rowNum1 = 0;
                colNum1 = 0;
                slantNumR1 = 0;
                slantNumL1 = 0;
            }

        }
        System.out.println("didnt find inline");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.boardArray[i][j] == this.chosenSimbol) {
                    if (j > 0 && board.boardArray[i][j - 1] == 0) {
                        a[0] = i;
                        a[1] = j - 1;
                        return a;
                    } else if (i > 0 && board.boardArray[i - 1][j] == 0) {
                        a[0] = i - 1;
                        a[1] = j;
                        return a;
                    } else if (i > 0 && j > 0 && board.boardArray[i - 1][j - 1] == 0) {
                        a[0] = i - 1;
                        a[1] = j - 1;
                        return a;
                    } else if (i < 2 && board.boardArray[i + 1][j] == 0) {
                        a[0] = i + 1;
                        a[1] = j;
                        return a;
                    } else if (j < 2 && board.boardArray[i][j + 1] == 0) {
                        a[0] = i;
                        a[1] = j + 1;
                        return a;
                    } else if (i < 2 && board.boardArray[i + 1][j] == 0) {
                        a[0] = i + 1;
                        a[1] = j;
                        return a;
                    } else if (i < 2 && j < 2 && board.boardArray[i + 1][j + 1] == 0) {
                        a[0] = i + 1;
                        a[1] = j + 1;
                        return a;
                    }
                    else if(i > 0 && j < 2 && board.boardArray[i - 1][j + 1] == 0){
                        a[0] = i - 1;
                        a[1] = j + 1;
                        return a;
                    }
                }
            }
        }


        System.out.println("returning randomal");
        while(true) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int rand = (int)Math.round(Math.random() * 100);
                    if (rand > 90 && rand < 95 && board.boardArray[i][j] == 0) {
                        a[0] = i;
                        a[1] = j;
                        return a;
                    }
                }
            }
        }
    }
      }

