package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Level {

    String name;
    Texture sala;


    Level(String nome){
        if(nome == "Base")
        sala = new Texture(Gdx.files.internal("roomtemp.png"));

        this.name = nome;
    }

    public Texture getSala(){
        return sala;
    }

    public String getName(){
        return name;
    }
}
