package com.underboss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Player extends Character {

    Texture playerImage = new Texture(Gdx.files.internal("Shooter1.png"));

    Player(){
        super();
        this.height = 78;
        this.width = 48;
    }

    Player(int x, int y){
        super(x, y);
        this.height = 78;
        this.width = 48;
    }

    Texture getPlayerImage(){
        return playerImage;
    }

    void setX(int x){
        this.x = x;
    }

    void setY(int y){
        this.y = y;
    }

}
