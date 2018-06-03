package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Projectile extends Rectangle {
    Character disparou;
    int speed;
    int direction; //0-360, começando em apontando para cima
    int acceleration;
    double angle;
    Boolean poison;

    Texture imagem;
    Texture tiroBoss;
    Texture tiroHero;
    Texture tiroBoss2;

    public Projectile(){};

    public Projectile(Character personagem, int nSpeed, float angle, float width, float height){
    disparou = personagem;
    x = personagem.getX();
    y = personagem.getY();
    speed = nSpeed;
    this.angle = angle;
    this.width = width;
    this.height = height;
    poison = false;
    }

    public Projectile(Character personagem, int nSpeed, float width, float height){
        disparou = personagem;
        x = personagem.getX();
        y = personagem.getY();
        speed = nSpeed;
        angle = personagem.getAngle();
        this.width = width;
        this.height = height;
        poison = false;

    }

    private void poisonProjectile(){
        poison = true;
        imagem = tiroBoss2;
    }

    public void setTiro(Texture img)
    {
        imagem = img;


    }

    public void setTiroBoss(Texture img){ tiroBoss = img;}

    public void setTiroBoss2(Texture img){ tiroBoss2 = img;}

    public void setTiroHero(Texture img){ tiroHero = img;}

    public void setAngle(float angle){ this.angle = angle; };

    public double getAngle(){return Math.toRadians(angle);}

    public double getAngleDegrees(){return angle;}

    public int getSpeed(){return speed;}

    public void setBossTiro(){
        imagem = tiroBoss;
        if(disparou.getState() == "Dying")
            poisonProjectile();
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

    public boolean getPoison(){ return poison; }

    public Boolean outofbounds()
    {
        if (this.getX() < 0 || this.getX() > 800 || this.getY() < 0 || this.getY() > 480)
            return true;

        return false;
    }
}
