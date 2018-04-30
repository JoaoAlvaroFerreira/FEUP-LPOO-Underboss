package com.underboss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Boss extends Character {

    Texture bossImage = new Texture(Gdx.files.internal("Boss1.png"));

    Boss(){
        super();
    }

    Boss(int x, int y){
        super(x, y);
    }

    Texture getBossImage()
    {
        return bossImage;
    }


    void setX(int x){
        this.x = x;
    }

    void setY(int y){
        this.y = y;
    }

}
