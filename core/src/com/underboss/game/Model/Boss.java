package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


import java.util.Random;



public class Boss extends Character {

    Texture bossImage = new Texture(Gdx.files.internal("Boss1.png"));
    double changeDirectionTimer = 0;
    int nextX, nextY, speed;
    float tempX, tempY;


    public Boss() {
        super();
        angle = 0;
    }

    public Boss(int x, int y) {
        super(x, y);
        angle = 0;
    }

    public Texture getBossImage() {
        return bossImage;
    }


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

    public void angleChange(Player jogador) {


        if (this.getX() - jogador.getX() > Math.abs(20) && this.getY() - jogador.getY() > Math.abs(20)) {
            if (this.getX() <= jogador.getX() && this.getY() <= jogador.getY()) //Q1
            {
                // this.angle = auxCalc(jogador);
                angle = 135;

            } else if (this.getX() <= jogador.getX() && this.getY() > jogador.getY())//Q4
            {
                // this.angle = auxCalc(jogador) + 90;
                angle = 45;

            } else if (this.getX() > jogador.getX() && this.getY() > jogador.getY()) //Q3
            {
                //this.angle = auxCalc(jogador) + 180;
                angle = 315;

            } else if (this.getX() > jogador.getX() && this.getY() <= jogador.getY()) //Q2
            {
                // this.angle = auxCalc(jogador) - 90;
                angle = 225;
            }
        } else if (this.getY() - jogador.getY() > 0)
            angle = 0;

            else if(this.getY() - jogador.getY() < 0)
                angle = 180;


}
    private float auxCalc(Player jogador)
    {
        float auxX, auxY;
        auxX = jogador.getX() - getX();
        auxY = jogador.getY() - getY();
        return (float)Math.tanh(auxY/auxX);

    }

    public Boolean tooClose(Player jogador){

        return Math.sqrt(Math.pow(this.getX() - jogador.getX(),2) + Math.pow(this.getY() - jogador.getY(),2)) < 180;

    }
}
