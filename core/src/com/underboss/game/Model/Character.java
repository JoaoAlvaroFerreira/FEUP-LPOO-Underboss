package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Array;
import java.util.Iterator;

public abstract class Character extends Rectangle {


    int HP;
    int maxHP;
    float angle;
    int estadoAtual;
    String[] estados;


    Character(){
        x = 240;
        y = 400;
        setWidth(50);
        setHeight(80);
        angle = 90;
        initEstados();

    }

    Character(int xpos, int ypos){
        x = xpos;
        y = ypos;
        angle = 90;
        initEstados();


    }

    Character(int xpos, int ypos, int estadoAtual){
        x = xpos;
        y = ypos;
        initEstados();
        this.estadoAtual = estadoAtual;
    }



    private void initEstados(){
        String[] estados = {"Healthy", "Poisoned", "Dying", "Dead", "Invincible"};
        estadoAtual = 0;
    }

    public int getHP() {
        return HP;
    }

    public void loseHP(){
        if(HP > 0)
        setHP(getHP() - 1);

        checkState();
    }

    public void checkState() {

        if(HP < maxHP/3)
            estadoAtual = 2;
        if(HP < 1)
            estadoAtual = 3;

    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getAngle() {return angle;}

}
