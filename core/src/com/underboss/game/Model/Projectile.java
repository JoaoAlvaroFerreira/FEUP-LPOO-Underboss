package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Projectile extends Rectangle {
    Character disparou;
    int speed;
    int direction; //0-360, começando em apontando para cima
    int acceleration;

    Texture imagem;
    Texture tiroHero;
    Texture tiroBoss;

    public Projectile(Character personagem, int nSpeed, int nDirection, int nAcceleration, float width, float height){
    disparou = personagem;
    x = personagem.getX();
    y = personagem.getY();
    speed = nSpeed;
    direction = nDirection;
    acceleration = nAcceleration;
    this.width = width;
    this.height = height;

    tiroBoss = new Texture(Gdx.files.internal("bossShot1.png"));
    tiroHero = new Texture(Gdx.files.internal("heroShot1.png"));
    }

    public Projectile(Character personagem, int xPos, int yPos, int nSpeed, int nDirection, int nAcceleration, float width, float height){
        disparou = personagem;
        speed = nSpeed;
        direction = nDirection;
        acceleration = nAcceleration;
        this.width = width;
        this.height = height;
    }

    public void setTiro(Texture img)
    {
        imagem = img;
    }

    public void setBossTiro(){
        imagem = tiroBoss;
    }

    public void setHeroTiro(){
        imagem = tiroHero;
    }

    public Texture getImagem(){
        return imagem;
    }



}
