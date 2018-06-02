package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Array;
import java.util.Iterator;

public abstract class Character extends Rectangle {


    int HP;
    int maxHP;
    double angle;
    int estadoAtual;
    String[] estados;


    Character(){
        x = 240;
        y = 400;
        setWidth(50);
        setHeight(80);
        angle = 0;
        initEstados();

    }

    Character(int xpos, int ypos){
        x = xpos;
        y = ypos;
        angle = 0;
        initEstados();


    }

    Character(int xpos, int ypos, int HP){
        x = xpos;
        y = ypos;
        this.HP = HP;
        this.maxHP = HP;
        angle = 0;
        initEstados();


    }



    public void setAngle(double novoAngulo){
        this.angle = novoAngulo;
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

    public double getAngle() {return angle;}

    public Boolean bulletDamage(Projectile tiro){
        if (tiro.overlaps(this)) {
            this.loseHP();
            return true;
        }
        return false;
    }

    public String getState(){
        return estados[estadoAtual];
    }

}
