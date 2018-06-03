package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Minion extends Character {

    float auxX,auxY;
    Texture  minionImage;

    /**
     * constructor for the minion class, an enemy summoned by a boss
     * @param chefe the boss that summoned this minion
     */
    public Minion(Boss chefe){

        this.x = chefe.getX();
        this.y = chefe.getY();
        this.HP = 2;
        this.maxHP = 2;
        setWidth(100);
        setHeight(100);

    }

    /**
     * moves the minion in the player's direction
     * @param jogador
     */

    public void move(Player jogador){
        auxX = jogador.getX() - this.getX() ;
        auxY = jogador.getY() - this.getY() ;

        setX(getX() + auxX * Gdx.graphics.getDeltaTime());
        setY(getY() + auxY * Gdx.graphics.getDeltaTime());
    }

    /**
     * gets the minion's image
     * @return minionImage
     */


    public Texture getMinionImage(){
        return minionImage;
    }

    /**
     * sets the minion's image
     * @param textura
     */

    public void setTexture(Texture textura){
        minionImage = textura;
    }

    /**
     * prevents the minion from leaving the bounds of the screen and returns it to normality if he does leave
     * @return
     */
    public Boolean outofbounds()
    {
        if (this.getX() < 0 || this.getX() > 800 || this.getY() < 0 || this.getY() > 480)
            return true;

        return false;
    }
}
