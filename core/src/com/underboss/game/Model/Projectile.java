package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Projectile extends Rectangle {
    Character disparou;
    int speed;
    int direction; //0-360, começando em apontando para cima
    int acceleration;
    float angle;

    Texture imagem;
    Texture tiroBoss = new Texture(Gdx.files.internal("bossShot1.png"));
    Texture tiroHero = new Texture(Gdx.files.internal("heroShot1.png"));

    public Projectile(Character personagem, int nSpeed, int nDirection, float angle, float width, float height){
    disparou = personagem;
    x = personagem.getX();
    y = personagem.getY();
    speed = nSpeed;
    direction = nDirection;
    this.angle = angle;
    this.width = width;
    this.height = height;

    }

    public Projectile(Character personagem, int nSpeed, int nDirection, float width, float height){
        disparou = personagem;
        x = personagem.getX();
        y = personagem.getY();
        speed = nSpeed;
        angle = personagem.getAngle();
        direction = nDirection;
        this.width = width;
        this.height = height;
    }

    public void setTiro(Texture img)
    {
        imagem = img;
    }

    public void setAngle(float angle){ this.angle = angle;};

    public float getAngle(){return angle;}

    public void setBossTiro(){
        imagem = tiroBoss;
    }

    public void setHeroTiro(){
        imagem = tiroHero;
    }

    public Texture getImagem(){
        return imagem;
    }

    public Character getDisparou() {
        return disparou;
    }

}
