package com.underboss.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Minion;
import com.underboss.game.Model.Player;
import com.underboss.game.Model.Projectile;

import java.awt.Rectangle;
import java.util.Iterator;

public class GameManager {

    private static GameManager instance;
    private static MyInputProcessor inputs;
    public static Player jogador;
    public static Boss chefao;
    public Array<Projectile> bossBullets;
    public Array<Projectile> heroBullets;
    public Array<Minion> minions;
    long lastShotTime;
    long lastHeroShotTime;


    public GameManager(){
        init();
    }

    public void init(){


        jogador = new Player (800 / 2 -  48 / 2,20, 8);
        jogador.setAngle(90);

        //init the Boss
       chefao = new Boss(800 / 2 -  78 / 2, 380, 15);
      //  chefao = new Boss(400, 200, 15);
        chefao.setAngle(270);

        lastShotTime = 0;
        bossBullets = new Array<Projectile>();
        heroBullets = new Array<Projectile>();
        minions = new Array<Minion>();

        instance = this;
        inputs = new MyInputProcessor(jogador);
        Gdx.input.setInputProcessor(inputs);
        inputs.setControlo(instance);
    }

    public void logic(float delta){

        if (jogador.getX() < 0)
            jogador.setX(0);
        if (jogador.getX() > 800 - 48)
            jogador.setX(800- 48);
        if (jogador.getY() < 0)
            jogador.setY(0);
        if (jogador.getY() > 480 - 78)
            jogador.setY(480-78);

        if(outofbounds(chefao))
        {
            chefao.setX(400);
            chefao.setY(240);
        }




     //   chefao.move(delta);
        chefao.angleChange(jogador);

        chefao.checkState();
       jogador.checkState();
      //  timeMinions();
        timeFire();
        bossFireIterator();
           heroFireIterator();
       // minionIterator();

//
//        if(chefao.getState() == "Dead")
//            winGame();
//
//        if(jogador.getState() == "Dead")
//            loseGame();


    }

    public static GameManager controlador() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    public static MyInputProcessor inputs(){

        return inputs;
    }

    private void timeFire(){
        if(TimeUtils.nanoTime() - lastShotTime > chefao.getFireFrequency()) {
        bossFire();
        lastShotTime = TimeUtils.nanoTime();
    }
    }

    private void timeMinions() {
        if(chefao.getState() == "Dying") {
            if (TimeUtils.nanoTime() - lastShotTime > chefao.getFireFrequency() * 2) {
                generateMinions();
                lastShotTime = TimeUtils.nanoTime();
            }
        }
    }
    public void bossFire() {
        Projectile tiro = new Projectile(chefao, chefao.getFireSpeed(), 180,(float)chefao.getAngle(), 70, 80 );
        tiro.setBossTiro();
        bossBullets.add(tiro);

        lastShotTime = TimeUtils.nanoTime();
    }
    public void heroFire(float angle) {
        jogador.setAngle(angle);
        Projectile tiro = new Projectile(jogador, 300, 180, angle, 40, 30 );
        tiro.setHeroTiro();
        heroBullets.add(tiro);
    }

    public void generateMinions(){
        Minion bicho = new Minion(chefao);
        minions.add(bicho);
    }

    private void bossFireIterator(){

        Iterator<Projectile> iter = bossBullets.iterator();
        while (iter.hasNext()) {
            Projectile tiro = iter.next();
            tiro.setY((float)(tiro.getY() +  tiro.getSpeed() * Math.sin(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            tiro.setX((float)(tiro.getX() +  tiro.getSpeed() * Math.cos(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            if (outofbounds(tiro) || jogador.bulletDamage(tiro))
                iter.remove();

            if(chefao.overlaps(jogador)){
                jogador.loseHP();
            }
        }

    }

    private void heroFireIterator(){


        Iterator<Projectile> iterNovo = heroBullets.iterator();
        Iterator<Minion> iterMinion = minions.iterator();
        while (iterNovo.hasNext()) {
            Projectile tiro = iterNovo.next();
            tiro.setY((float)(tiro.getY() +  tiro.getSpeed() * Math.sin(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            tiro.setX((float)(tiro.getX() +  tiro.getSpeed() * Math.cos(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            if (outofbounds(tiro) || chefao.bulletDamage(tiro))
                iterNovo.remove();


            while (iterMinion.hasNext()) {
                Minion bicho = iterMinion.next();
                if(bicho.bulletDamage(tiro))
                    iterMinion.remove();
            }

        }
    }

    private void minionIterator(){

        Iterator<Minion> iterMinion = minions.iterator();
        while (iterMinion.hasNext()) {
            Minion bicho = iterMinion.next();
            bicho.move(jogador);
            if(jogador.minionDamage(bicho) || outofbounds(bicho))
                iterMinion.remove();
        }


    }

    public Boolean swordAttack(){
        if(chefao.tooClose(jogador)){
            jogador.loseHP();
        return true;
        }
    return false;
    }




    private Boolean outofbounds(com.badlogic.gdx.math.Rectangle obj)
    {
      if (obj.getX() < 0 || obj.getX() > 800 || obj.getY() < 0 || obj.getY() > 480)
          return true;

      return false;
    }

    private void winGame(){}

    private void loseGame(){}

}
