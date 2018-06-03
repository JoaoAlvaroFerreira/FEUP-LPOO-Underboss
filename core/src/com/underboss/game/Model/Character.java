package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;


public abstract class Character extends Rectangle {


    float HP;
    float maxHP;
    double angle;
    int estadoAtual;
    private ArrayList<String> estados = new ArrayList<String>();


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


    public void recalibrate(){

        if (this.getX() < 0)
            this.setX(0);
        if (this.getX() > 800 - 48)
            this.setX(800- 48);
        if (this.getY() < 0)
            this.setY(0);
        if (this.getY() > 480 - 78)
            this.setY(480-78);

    }

    public void setAngle(double novoAngulo){
        this.angle = novoAngulo;
    }

    private void initEstados(){
        estados.add("Healthy");
        estados.add("Poisoned");
        estados.add("Dying");
        estados.add("Dead");
        estados.add("Invincible");
        estadoAtual = 0;
    }

    public float getHP() {
        return HP;
    }

    public void loseHP(){
        if(HP > 0)
        setHP(getHP() - 1);

        checkState();
    }

    public void checkState() {

        if(HP <= maxHP/3)
            estadoAtual = 2;
        if(HP < 1)
            estadoAtual = 3;


        poisonTick();
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public double getAngle() {return angle;}

    public Boolean bulletDamage(Projectile tiro){
        if (tiro.overlaps(this)) {
            this.loseHP();
            if(tiro.getPoison()) {
                estadoAtual = 1;
                }
            return true;
        }
        return false;
    }

    public String getState(){
        return estados.get(estadoAtual);
    }

    public void setState(int i){
        this.estadoAtual = i;
    }

    public void poisonTick(){
        if(getState() == "Poisoned")
            setHP(getHP() - Gdx.graphics.getDeltaTime() / 3);


    }

}
