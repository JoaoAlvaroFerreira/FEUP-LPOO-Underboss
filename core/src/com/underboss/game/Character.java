package com.underboss.game;

public abstract class Character {

    int x;
    int y;
    String status;

    Character(){
        x = 500;
        y = 500;
        status = "Normal";
    }

    Character(int xpos, int ypos){
        x = xpos;
        y = ypos;
        status = "Normal";
    }

    Character(int xpos, int ypos, String actstatus){
        x = xpos;
        y = ypos;
        status = actstatus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getStatus() {
        return status;
    }
}
