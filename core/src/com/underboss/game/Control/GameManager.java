package com.underboss.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Player;
import com.underboss.game.Model.Projectile;

import java.util.Iterator;

public class GameManager {

    private static GameManager instance;
    private static MyInputProcessor inputs;
    public static Player jogador;
    public static Boss chefao;
    public Array<Projectile> bossBullets;
    public Array<Projectile> heroBullets;
    long lastShotTime;

    public GameManager(){
        init();
    }

    public void init(){


        jogador = new Player (800 / 2 -  48 / 2,20);
        jogador.setHP(10);

        //init the Boss
        chefao = new Boss(800 / 2 -  78 / 2, 380);
        chefao.setHP(15);

        lastShotTime = 0;
        bossBullets = new Array<Projectile>();
        heroBullets = new Array<Projectile>();
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



        if(TimeUtils.nanoTime() - lastShotTime > 1000000000) {
            bossFire();
            lastShotTime = TimeUtils.nanoTime();
        }




        Iterator<Projectile> iter = bossBullets.iterator();
        while (iter.hasNext()) {
            Projectile tiro = iter.next();
            tiro.setY((float)(tiro.getY() +  200 * Math.sin(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            tiro.setX((float)(tiro.getX() +  200 * Math.cos(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            if (tiro.getX() < 0 || tiro.getX() > 800 || tiro.getY() < 0 || tiro.getY() > 480)
                iter.remove();

            if (tiro.overlaps(jogador)) {
                jogador.loseHP();
                iter.remove();
            }

            if(chefao.overlaps(jogador)){
                jogador.loseHP();
            }
        }


        Iterator<Projectile> iterNovo = heroBullets.iterator();
        while (iterNovo.hasNext()) {
            Projectile tiro = iterNovo.next();
            tiro.setY((float)(tiro.getY() +  200 * Math.sin(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            tiro.setX((float)(tiro.getX() +  200 * Math.cos(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            if (tiro.getX() < 0 || tiro.getX() > 800 || tiro.getY() < 0 || tiro.getY() > 480)

                iterNovo.remove();
            if (tiro.overlaps(chefao)) {
                chefao.loseHP();
                iterNovo.remove();
            }
        }


        chefao.move(delta);
        chefao.angleChange(jogador);
        chefao.checkState();
        jogador.checkState();




    }

    public static GameManager controlador() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    public static MyInputProcessor inputs(){

        return inputs;
    }

    public void bossFire() {
        Projectile tiro = new Projectile(chefao, 200, 180,0, 70, 80 );
        tiro.setBossTiro();
        bossBullets.add(tiro);

        lastShotTime = TimeUtils.nanoTime();
    }
    public void heroFire(float angle) {
        Projectile tiro = new Projectile(jogador, 200, 180, angle, 40, 30 );
        tiro.setHeroTiro();
        heroBullets.add(tiro);
    }

    public Boolean swordAttack(){
        if(chefao.tooClose(jogador)){
            jogador.loseHP();
        return true;
        }
    return false;
    }


}
