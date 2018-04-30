package com.underboss.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;



public class Projectile extends Rectangle {
    Character disparou;
    int speed;
    int direction; //0-360, começando em apontando para cima
    int acceleration;
    Texture imagem;

    Projectile(Character personagem, int nSpeed, int nDirection, int nAcceleration, float width, float height){
    disparou = personagem;
    x = personagem.getX();
    y = personagem.getY();
    speed = nSpeed;
    direction = nDirection;
    acceleration = nAcceleration;
    this.width = width;
    this.height = height;
    }

    Projectile(Character personagem, int xPos, int yPos, int nSpeed, int nDirection, int nAcceleration, float width, float height){
        disparou = personagem;
        speed = nSpeed;
        direction = nDirection;
        acceleration = nAcceleration;
        this.width = width;
        this.height = height;
    }

    void setTiro(Texture img)
    {
        imagem = img;
    }

    Texture getImagem(){
        return imagem;
    }


}
