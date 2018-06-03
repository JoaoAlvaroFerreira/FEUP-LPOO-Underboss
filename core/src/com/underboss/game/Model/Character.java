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

    /**
     * constructor for the abstract class character
     */
    Character(){
        x = 240;
        y = 400;
        setWidth(50);
        setHeight(80);
        angle = 0;
        initEstados();

    }

    /**
     * constructor for the abstract class character
     * @param xpos
     * @param ypos
     */

    Character(int xpos, int ypos){
        x = xpos;
        y = ypos;
        angle = 0;
        initEstados();


    }

    /**
     * constructor for the abstract class character
     * @param xpos
     * @param ypos
     * @param HP
     */

    Character(int xpos, int ypos, int HP){
        x = xpos;
        y = ypos;
        this.HP = HP;
        this.maxHP = HP;
        angle = 0;
        initEstados();


    }

    /**
     * makes sure characters stay inside the arena
     */

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

    /**
     * sets a new angle
     * @param novoAngulo
     */

    public void setAngle(double novoAngulo){
        this.angle = novoAngulo;
    }

    /**
     * defines the possible states for a character and initializes them at Healthy
     */

    private void initEstados(){
        estados.add("Healthy");
        estados.add("Poisoned");
        estados.add("Dying");
        estados.add("Dead");
        estados.add("Invincible");
        estadoAtual = 0;
    }

    /**
     * returns the character's HP
     * @return HP
     */
    public float getHP() {
        return HP;
    }

    /**
     * makes the character lose one HP without going under 0, and proceeding to check their current state
     */

    public void loseHP(){
        if(HP > 0)
        setHP(getHP() - 1);

        checkState();
    }

    /**
     * checks a character's current state and updates it if needed
     */

    public void checkState() {

        if(HP <= maxHP/3)
            estadoAtual = 2;
        if(HP < 1)
            estadoAtual = 3;


        poisonTick();
    }

    /**
     * sets a character's HP
     * @param HP
     */
    public void setHP(float HP) {
        this.HP = HP;
    }

    /**
     * returns the character's angle
     * @return angle
     */

    public double getAngle() {return angle;}

    /**
     * causes bullet damage to the character
     * @param tiro
     * @return if the character got damaged or not
     */
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

    /**
     * gets the character's state in String form
     * @return the character's state
     */
    public String getState(){
        return estados.get(estadoAtual);
    }

    /**
     * sets the character's state
     * @param i
     */

    public void setState(int i){
        this.estadoAtual = i;
    }

    /**
     * deals one tick of poison damage to the character, repeating while the character is poisoned and dealing damage over time
     */

    public void poisonTick(){
        if(getState() == "Poisoned")
            setHP(getHP() - Gdx.graphics.getDeltaTime() / 3);


    }

}
