package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Minion extends Character {

    float auxX,auxY;
    Texture  minionImage = new Texture(Gdx.files.internal("minion1.png"));

    public Minion(Boss chefe){

        this.x = chefe.getX();
        this.y = chefe.getY();
        this.HP = 2;
        this.maxHP = 2;
        setWidth(80);
        setHeight(80);

    }

    public void move(Player jogador){
        auxX = jogador.getX() - this.getX() ;
        auxY = jogador.getY() - this.getY() ;

        setX(getX() + auxX * Gdx.graphics.getDeltaTime());
        setY(getY() + auxY * Gdx.graphics.getDeltaTime());
    }


    public Texture getMinionImage(){
        return minionImage;
    }
}
