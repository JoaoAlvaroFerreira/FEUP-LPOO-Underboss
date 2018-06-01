package com.underboss.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Map;
import com.underboss.game.Model.Player;
import com.underboss.game.Model.Projectile;

import java.util.Iterator;

public class GameManager {

    private static GameManager instance;
    private static MyInputProcessor inputs;
    public Player jogador;
    public Boss chefao;
    public Map sala;
    public Array<Projectile> bossBullets;
    public Array<Projectile> heroBullets;
    long lastShotTime;

    public GameManager(){
        init();
    }

    public void init(){
        instance = this;

        jogador = new Player (800 / 2 -  48 / 2,20);
        jogador.setHP(5);

        //inputs = new MyInputProcessor(jogador);
        inputs = new MyInputProcessor(jogador);
        Gdx.input.setInputProcessor(inputs);
        inputs.setControlo(instance);
        //init the Boss
        chefao = new Boss(800 / 2 -  78 / 2, 380);
        chefao.setHP(20);

        lastShotTime = 0;
        bossBullets = new Array<Projectile>();
        heroBullets = new Array<Projectile>();
    }

    public void logic(float delta){
       // instance.logic(delta);
        // make sure the bucket stays within the screen bounds
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

        chefao.move(delta);

        Iterator<Projectile> iter = bossBullets.iterator();
        while (iter.hasNext()) {
            Projectile tiro = iter.next();
            tiro.setY(tiro.getY() -  200 * Gdx.graphics.getDeltaTime());
            if (tiro.getX() + 64 < 0)
                iter.remove();

            if (tiro.overlaps(jogador)) {
                jogador.setHP(jogador.getHP() - 1);
                //  dropSound.play();
                iter.remove();
            }
        }


        Iterator<Projectile> iterNovo = heroBullets.iterator();
        while (iterNovo.hasNext()) {
            Projectile tiro = iterNovo.next();
            tiro.setY(tiro.getY() +  200 * Gdx.graphics.getDeltaTime());
            if (tiro.getY() + 64 > 480)
                iterNovo.remove();
            if (tiro.overlaps(chefao)) {
                chefao.setHP(chefao.getHP() - 1);
                iterNovo.remove();
            }
        }

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
        Projectile tiro = new Projectile(chefao, 200, 180,0, 46, 13 );
        tiro.setBossTiro();
        bossBullets.add(tiro);

        lastShotTime = TimeUtils.nanoTime();
    }
    public void heroFire() {
        Projectile tiro = new Projectile(jogador, 200, 180,0, 46, 13 );
        tiro.setHeroTiro();
        heroBullets.add(tiro);
    }



}
