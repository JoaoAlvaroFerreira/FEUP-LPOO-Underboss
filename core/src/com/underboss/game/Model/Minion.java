package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Minion extends Character {

    float auxX,auxY;
    Texture minionImage;

    public Minion(Boss chefe){

        this.x = chefe.getX();
        this.y = chefe.getY();
        this.HP = 2;
        this.maxHP = 2;
        minionImage = new Texture(Gdx.files.internal("minion1.png"));


    }

    public void move(Player jogador){
        auxX = jogador.getX() - this.getX() ;
        auxY = jogador.getY() - this.getY() ;

        setX((float)(getX() + auxX*0.1));
        setY((float)(getY() + auxY*0.1));
    }


}
