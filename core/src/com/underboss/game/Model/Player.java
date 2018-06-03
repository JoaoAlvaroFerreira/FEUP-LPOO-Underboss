package com.underboss.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;


public class Player extends Character {

    float TimeSinceHit;
    float SafetyTime;
    Texture playerImage = new Texture(Gdx.files.internal("Shooter1.png"));

    public Player(){
        super();
       initVariables();
    }

    public Player(int x, int y){
        super(x, y);
       initVariables();
    }

    public Player(int x, int y, int HP){
        super(x, y, HP);
        initVariables();
    }

    private void initVariables(){

        this.height = 78;
        this.width = 48;
        this.angle = 0;
        TimeSinceHit = 0;
        SafetyTime = 2000000000;
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

    public Boolean minionDamage(Minion bicho){

        if (bicho.overlaps(this)) {
            this.loseHP();
            this.loseHP();
            return true;
        }
        return false;
    }

    public void makePlayerInvincible(){
        this.setState(4);
        this.TimeSinceHit = TimeUtils.nanoTime();


    }

    public void makePlayerNormal(){
        this.setState(0);
        TimeSinceHit = 0;
    }



    @Override
    public void loseHP(){


        if(getState() != "Invincible") {
            if (HP > 0) {
                setHP(getHP() - 1);
                Gdx.input.vibrate(1000);
                makePlayerInvincible();
            }
        }

        checkState();
    }

    @Override
    public void checkState() {


        if(getState() != "Invincible") {

            if (HP <= maxHP / 3)
                estadoAtual = 2;
            if (HP < 1)
                estadoAtual = 3;
        }

        if(TimeUtils.nanoTime() - TimeSinceHit > SafetyTime)
         makePlayerNormal();

        poisonTick();
    }




}
