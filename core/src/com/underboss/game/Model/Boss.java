package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.max;


public class Boss extends Character {

    Texture bossImage = new Texture(Gdx.files.internal("Boss1.png"));
    double changeDirectionTimer = 0;
    int nextX, nextY, speed;


    public Boss(){
        super();
        angle = 270;
    }

    public Boss(int x, int y){
        super(x, y);
        angle = 270;
    }

    public Texture getBossImage()
    {
        return bossImage;
    }


    void setX(int x){
        this.x = x;
    }

    void setY(int y){
        this.y = y;
    }

    public void move(double delta){


        if(changeDirectionTimer < 0 || (getX() == nextX && getY() == nextY))
        {
            Random rand = new Random();
            changeDirectionTimer = (double)rand.nextInt(4)/delta;
            nextX = rand.nextInt(700);
            nextY = rand.nextInt(400);
            speed = rand.nextInt(10);

        }


        setX((getX() + (nextX-getX())/65*speed));
        setY( (getY() + (nextY-getY())/65*speed));
        changeDirectionTimer = changeDirectionTimer - 1;


    }

    void angleChange(Player jogador){

    }
}
