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

    /**
     * constructor for the projectile class
     * @param personagem
     * @param nSpeed
     * @param angle
     * @param width
     * @param height
     */
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

    /**
     * constructor for the projectile class
     * @param personagem
     * @param nSpeed
     * @param width
     * @param height
     */
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

    /**
     * poisons the projectile
     */

    private void poisonProjectile(){
        poison = true;
        imagem = tiroBoss2;
    }

    /**
     * sets the shot's image to a specific one
     * @param img
     */
    public void setTiro(Texture img)
    {
        imagem = img;


    }

    /**
     * sets the boss's normal shot image
     * @param img
     */
    public void setTiroBoss(Texture img){ tiroBoss = img;}

    /**
     * sets the boss's poisoned shot image
     * @param img
     */

    public void setTiroBoss2(Texture img){ tiroBoss2 = img;}

    /**
     * sets the hero's shot image
     * @param img
     */
    public void setTiroHero(Texture img){ tiroHero = img;}

    /**
     * sets the projectile's angle
     * @param angle
     */
    public void setAngle(float angle){ this.angle = angle; };

    /**
     * gets the projectile's angle in radians, for easy of usage in trigonometric operations
     * @return angle (in radians)
     */
    public double getAngle(){return Math.toRadians(angle);}

    /**
     * gets the projectile's angle in degrees
     * @return angle
     */
    public double getAngleDegrees(){return angle;}

    /**
     * gets the projectile's speed
     * @return speed
     */
    public int getSpeed(){return speed;}

    /**
     * sets the boss's shot and poisons it if needed
     */
    public void setBossTiro(){
        imagem = tiroBoss;
        if(disparou.getState() == "Dying")
            poisonProjectile();
    }

    /**
     * sets the hero's shot
     */
    public void setHeroTiro(){
        imagem = tiroHero;
    }

    /**
     * gets the current shot image being used by the projectile
     * @return imagem
     */
    public Texture getImagem(){
        return imagem;
    }

    /**
     * gets the character that fired the shot
     * @return disparou
     */

    public Character getDisparou() {
        return disparou;
    }

    /**
     * gets the information about if the projectile is poisoned or not
     * @return
     */

    public boolean getPoison(){ return poison; }

    /**
     * gets if the projectile is out of bounds, to be eliminated by another process
     * @return
     */
    public Boolean outofbounds()
    {
        if (this.getX() < 0 || this.getX() > 800 || this.getY() < 0 || this.getY() > 480)
            return true;

        return false;
    }
}
