package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


import java.util.Random;



public class Boss extends Character {

    Texture bossImage;
    double changeDirectionTimer = 0;
    int nextX, nextY, speed;
    float tempX, tempY;
    double fireFrequency;
    int fireSpeed;
    boolean jamudou;

    /**
     * constructor for boss class
     */
    public Boss() {
        super();
        initVariables();
    }

    /**
     * constructor for boss class
     * @param x
     * @param y
     */
    public Boss(int x, int y) {
        super(x, y);
        initVariables();
    }

    /**
     * constructor for boss class
     * @param x
     * @param y
     * @param HP
     */
    public Boss(int x, int y, int HP) {
        super(x, y, HP);
       initVariables();
    }

    /**
     * auxiliary method for the constructors that initiates variables
     */
    private void initVariables(){
        angle = 0;
        fireFrequency = 2000000000;
        fireSpeed = 100;
        this.setWidth(100);
        this.setHeight(100);
        jamudou = false;
    }

    /**
     * returns the boss's image
     * @return bossImage
     */

    public Texture getBossImage() {
        return bossImage;
    }

    /**
     * sets the boss's image
     * @param textura
     */
    public void setTexture(Texture textura){
        bossImage = textura;
    }

    /**
     * returns the frequency at which the boss is firing
     * @return fireFrequency
     */
    public double getFireFrequency(){ return fireFrequency;   }

    /**
     * moves the boss in unpredictable directions
     * @param delta
     */
    public void move(double delta) {


        if (changeDirectionTimer < 0 || (getX() == nextX && getY() == nextY)) {
            Random rand = new Random();
            changeDirectionTimer = (double) rand.nextInt(4) / delta;
            nextX = rand.nextInt(700);
            nextY = rand.nextInt(400);
            speed = rand.nextInt(10);

        }

        tempX = getX() + (nextX - getX()) / 65 * speed;
        if(tempX > 700)
            tempX = 700;

        tempY = getY() + (nextY - getY()) / 65 * speed;
        if(tempY > 400)
            tempY = 400;

        setX(tempX);
        setY(tempY);
        changeDirectionTimer = changeDirectionTimer - 1;



    }

    /**
     * doubles the boss's shot speed, but halves his rate of fire
     */
    public void doubleFire(){ fireFrequency = fireFrequency/2;
    fireSpeed = 2*fireSpeed;}

    /**
     * returns the boss's shot speed
     * @return fireSpeed
     */

    public int getFireSpeed() {
        return fireSpeed;
    }

    /**
     * changes the boss's angle so that he always faces the player
     * @param jogador
     */

    public void angleChange(Player jogador) {
        if (this.getX() <= jogador.getX() && this.getY() <= jogador.getY())
            angle = auxCalc(jogador); //Q1

        else if (this.getX() <= jogador.getX() && this.getY() > jogador.getY())
            angle = auxCalc(jogador);

        else if (this.getX() > jogador.getX() && this.getY() > jogador.getY())
            angle = auxCalc(jogador) + 180;

        else if (this.getX() > jogador.getX() && this.getY() <= jogador.getY())
            angle = auxCalc(jogador) + 180;

        if(getState() == "Dying" && !jamudou)
        {
            doubleFire();
            jamudou = true;
        }
    }


    /**
     * auxiliary method to calculate the boss's angle
     * @param jogador
     * @return pre-processed boss angle
     */
    private float auxCalc(Player jogador)
    {
        float auxX, auxY;
        auxX = jogador.getX() - getX();
        auxY = jogador.getY() - getY();
        return (float)Math.toDegrees(Math.tanh(auxY/auxX));

    }

    /**
     * checks if the player is too close to the boss
     * @param jogador
     * @return if the player is too close
     */

    public Boolean tooClose(Player jogador){

        return Math.sqrt(Math.pow(this.getX() - jogador.getX(),2) + Math.pow(this.getY() - jogador.getY(),2)) < 100;

    }




}
