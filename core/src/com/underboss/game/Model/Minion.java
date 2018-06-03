package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Minion extends Character {

    float auxX,auxY;
    Texture  minionImage;

    public Minion(Boss chefe){

        this.x = chefe.getX();
        this.y = chefe.getY();
        this.HP = 2;
        this.maxHP = 2;
        setWidth(100);
        setHeight(100);

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

    public void setTexture(Texture textura){
        minionImage = textura;
    }

    public Boolean outofbounds()
    {
        if (this.getX() < 0 || this.getX() > 800 || this.getY() < 0 || this.getY() > 480)
            return true;

        return false;
    }
}
