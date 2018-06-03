package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;
import com.underboss.game.Control.MyInputProcessor;


public class Player extends Character {

    float TimeSinceHit;
    float SafetyTime;
    MyInputProcessor vibra;
    Texture playerImage;

    /**
     * the default constructor for the player class
     */
    public Player(){
        super();
       initVariables();
    }

    /**
     * a constructor for the player class
     * @param x
     * @param y
     */

    public Player(int x, int y){
        super(x, y);
       initVariables();
    }

    /**
     * a constructor for the player class
     * @param x
     * @param y
     * @param HP
     */

    public Player(int x, int y, int HP){
        super(x, y, HP);
        initVariables();
    }

    /**
     * initializes the variables associated with a player
     */
    private void initVariables(){

        this.height = 78;
        this.width = 48;
        this.angle = 0;
        TimeSinceHit = 0;
        SafetyTime = 2000000000;
    }

    /**
     * gets the player's image
     * @return playerImage
     */
    public Texture getPlayerImage(){
        return playerImage;
    }

    /**
     * sets the player's image
     * @param textura
     */
    public void setTexture(Texture textura){
        playerImage = textura;
    }


    /**
     * makes the player character walk up
     */
    public void walkUp(){
        setY(this.getY() + 400 * Gdx.graphics.getDeltaTime());
    };

    /**
     * makes the player character walk down
     */
    public void walkDown(){
        setY(this.getY() - 400 * Gdx.graphics.getDeltaTime());
    };

    /**
     * makes the player character walk left
     */
    public void walkLeft(){
        setX(this.getX() - 400 * Gdx.graphics.getDeltaTime());
    };

    /**
     * makes the player character walk right
     */

    public void walkRight(){
        setX(this.getX() + 400 * Gdx.graphics.getDeltaTime());
    };


    /**
     * deals damage from contact with a minion to the player
     * @param bicho
     * @return if the minion did damage or not
     */
    public Boolean minionDamage(Minion bicho){

        if (bicho.overlaps(this)) {

            if(getState() == "Healthy")
            setHP(getHP() - 1);


            loseHP();
            return true;
        }
        return false;
    }

    /**
     * makes the player temporarily invincible, giving him a period of invulnerability after getting hit
     */
    public void makePlayerInvincible(){
        this.setState(4);
        this.TimeSinceHit = TimeUtils.nanoTime();


    }

    /**
     * returns the player back to normal state
     */
    public void makePlayerNormal(){
        this.setState(0);
        TimeSinceHit = 0;
    }


    /**
     * custom method to lose HP for the player, integrating the temporary invulnerability
     */
    @Override
    public void loseHP(){


        if(getState() != "Invincible") {
            if (HP > 0) {
                setHP(getHP() - 1);
               Gdx.input.vibrate(250);

               if(HP > 1)
                makePlayerInvincible();
            }
        }

        checkState();
    }

    /**
     * custom method to check state for the player, integrating the temporary invulnerability
     */
    @Override
    public void checkState() {

        if(TimeUtils.nanoTime() - TimeSinceHit > SafetyTime && getState() != "Poisoned")
            makePlayerNormal();

        if(getState() != "Invincible") {

            if (HP <= maxHP / 3)
                estadoAtual = 2;
            if (HP < 1)
                estadoAtual = 3;
        }



        poisonTick();
    }


}