package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

public abstract class Character extends Rectangle {


    int HP;
    int maxHP;
    int angle;
    State estado;

    public enum State {
        HEALTHY,  DYING, DEAD, POISONED
    }

    Character(){
        x = 240;
        y = 400;
        angle = 90;
        State.valueOf("HEALTHY");
    }

    Character(int xpos, int ypos){
        x = xpos;
        y = ypos;
        angle = 90;
        State.valueOf("HEALTHY");

    }

    Character(int xpos, int ypos, String estado){
        x = xpos;
        y = ypos;
        State.valueOf(estado);
    }

    public int getHP() {
        return HP;
    }

    public void checkState() {

        if(HP < maxHP/3)
            State.valueOf("DYING");
        if(HP < 1)
            State.valueOf("DEAD");


    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAngle() {return angle;}
}
