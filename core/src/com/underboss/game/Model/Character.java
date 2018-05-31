package com.underboss.game.Model;

import com.badlogic.gdx.math.Rectangle;

public abstract class Character extends Rectangle {


    int HP;
    String status;

    Character(){
        x = 240;
        y = 400;
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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getStatus() {
        return status;
    }
}
