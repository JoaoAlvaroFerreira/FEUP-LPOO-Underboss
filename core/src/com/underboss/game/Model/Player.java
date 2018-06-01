package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Player extends Character {

    Texture playerImage = new Texture(Gdx.files.internal("Shooter1.png"));

    public Player(){
        super();
        this.height = 78;
        this.width = 48;
    }

    public Player(int x, int y){
        super(x, y);
        this.height = 78;
        this.width = 48;
    }

    public Texture getPlayerImage(){
        return playerImage;
    }

    void setX(int x){
        this.x = x;
    }

    void setY(int y){
        this.y = y;
    }

    public void walkUp(){
        setY(this.getY() + 400 * Gdx.graphics.getDeltaTime());
    };

    public void walkDown(){
        setY(this.getY() - 400 * Gdx.graphics.getDeltaTime());
    };

    public void walkLeft(){
        setX(this.getX() - 400 * Gdx.graphics.getDeltaTime());
    };

    public void walkRight(){
        setX(this.getX() + 400 * Gdx.graphics.getDeltaTime());
    };



}
