package com.example.ixigul;

public abstract class Player {
    private String name;
    int chosenSimbol;
    int score;
    Player(String name){
        this.name = name;
    }
        public void setSimbol(int simbol){
                chosenSimbol = simbol;
        }

        public void chose(Board board, int x, int y){}
        public int[] chose(Board board, int oCh){
            int[] a = {0, 0};
            return a;
        }
public String getName(){
            return name;
}
}
