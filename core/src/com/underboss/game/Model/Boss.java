package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


import java.util.Random;



public class Boss extends Character {

    Texture bossImage = new Texture(Gdx.files.internal("Boss1.png"));
    double changeDirectionTimer = 0;
    int nextX, nextY, speed;
    float tempX, tempY;
    double fireFrequency;
    int fireSpeed;
    boolean jamudou;

    public Boss() {
        super();
        initVariables();
    }

    public Boss(int x, int y) {
        super(x, y);
        initVariables();
    }
    public Boss(int x, int y, int HP) {
        super(x, y, HP);
       initVariables();
    }

    private void initVariables(){
        angle = 0;
        fireFrequency = 2000000000;
        fireSpeed = 100;
        this.setWidth(100);
        this.setHeight(100);
        jamudou = false;
    }


    public Texture getBossImage() {
        return bossImage;
    }


    public double getFireFrequency(){ return fireFrequency;   }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

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

    public void doubleFire(){ fireFrequency = fireFrequency/2;
    fireSpeed = 2*fireSpeed;}

    public int getFireSpeed() {
        return fireSpeed;
    }

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


    private float auxCalc(Player jogador)
    {
        float auxX, auxY;
        auxX = jogador.getX() - getX();
        auxY = jogador.getY() - getY();
        return (float)Math.toDegrees(Math.tanh(auxY/auxX));

    }

    public Boolean tooClose(Player jogador){

        return Math.sqrt(Math.pow(this.getX() - jogador.getX(),2) + Math.pow(this.getY() - jogador.getY(),2)) < 100;

    }




}
