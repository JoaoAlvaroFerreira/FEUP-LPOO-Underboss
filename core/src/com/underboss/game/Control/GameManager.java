package com.underboss.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Character;
import com.underboss.game.Model.Minion;
import com.underboss.game.Model.Player;
import com.underboss.game.Model.Projectile;
import com.underboss.game.View.EndScreen;
import com.underboss.game.View.Tutorial;

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
    long lastMinionTime;
    long lastHeroShotTime;
    int gameEnd;

    Texture tiroBoss;
    Texture tiroHero;
    Texture tiroBoss2;
    Texture playerImage;
    Texture bossImage;
    Texture minionImage;


    /**
     * Constructor for class GameManager
     */


    public GameManager(){
        init();
    }

    /**
     *  init for all the variables in GameManager
     */

    public void init(){


        jogador = new Player (800 / 2 -  48 / 2,20, 15);
        jogador.setAngle(90);


        chefao = new Boss(800 / 2 -  78 / 2, 380, 40);
        chefao.setAngle(270);



        lastShotTime = 0;
        lastMinionTime = 0;
        lastHeroShotTime = 0;
        bossBullets = new Array<Projectile>();
        heroBullets = new Array<Projectile>();
        minions = new Array<Minion>();

        instance = this;
        inputs = new MyInputProcessor(jogador);
        Gdx.input.setInputProcessor(inputs);
        inputs.setControlo(instance);
        loadTextures();
    }

    /**
     *  loader for all the textures in GameManager
     */

    private void loadTextures(){

        tiroBoss = new Texture(Gdx.files.internal("bossShot1.png"));
        tiroHero = new Texture(Gdx.files.internal("heroShot1.png"));
        tiroBoss2 = new Texture(Gdx.files.internal("bossShot2.png"));
        playerImage = new Texture(Gdx.files.internal("Shooter1.png"));
        jogador.setTexture(playerImage);
        bossImage = new Texture(Gdx.files.internal("Boss1.png"));
        chefao.setTexture(bossImage);
        minionImage = new Texture(Gdx.files.internal("minion1.png"));
    }

    /**
     * the logic loop in GameManager
     * @param delta is the runTime
     */


    public void logic(float delta){




        chefao.move(delta);
         timeMinions();
         timeFire();
         minionIterator();
         bossFireIterator();
        chefao.angleChange(jogador);
        chefao.checkState();
        jogador.checkState();
        heroFireIterator();
        jogador.recalibrate();
        chefao.recalibrate();

        if(chefao.getState() == "Dead")
            winGame();

        if(jogador.getState() == "Dead")
            loseGame();


    }

    /**
     * if there isn't one already, creates a static instance of this GameManager
     * @return the static instance of GameManager
     */

    public static GameManager controlador() {
        if (instance == null)
            instance = new GameManager();
        return instance;
    }

    /**
     * get method for the input processor
     * @return static input processor
     */


    public static MyInputProcessor inputs(){

        return inputs;
    }

    /**
     * Calculates the time between every new boss shot, and then shoots it once that time interval is over
     */

    private void timeFire(){
        if(TimeUtils.nanoTime() - lastShotTime > chefao.getFireFrequency()) {
        bossFire();
        lastShotTime = TimeUtils.nanoTime();
    }

    }

    /**
     * times the generation of minions by the boss
     */

    private void timeMinions() {


            if (TimeUtils.nanoTime() - lastMinionTime > chefao.getFireFrequency() * 2) {
                generateMinions();
                lastMinionTime = TimeUtils.nanoTime();
            }

    }

    /**
     * generates the projectiles for the boss fire
     */

    public void bossFire() {
        Projectile tiro = new Projectile(chefao, chefao.getFireSpeed(), (float)chefao.getAngle(), 70, 80 );
        tiro.setTiroBoss(tiroBoss);
        tiro.setTiroBoss2(tiroBoss2);
        tiro.setBossTiro();
        bossBullets.add(tiro);
        lastShotTime = TimeUtils.nanoTime();
    }

    /**
     * times and generates the projectiles for the player's shots
     * @param angle the angle at which the player will fire
     */

    public void heroFire(float angle) {

        if (TimeUtils.nanoTime() - lastHeroShotTime > chefao.getFireFrequency() / 10) {
            jogador.setAngle(angle);
            Projectile tiro = new Projectile(jogador, 300, angle, 50, 70);
            tiro.setTiroHero(tiroHero);
            tiro.setHeroTiro();
            heroBullets.add(tiro);
            lastHeroShotTime = TimeUtils.nanoTime();
        }
    }

    /**
     * generates the boss's minions
     */

    public void generateMinions(){
        Minion bicho = new Minion(chefao);
        bicho.setTexture(minionImage);
        minions.add(bicho);
    }

    /**
     * makes the boss's shots move and collide with other objects
     */

    private void bossFireIterator(){

        Iterator<Projectile> iter = bossBullets.iterator();
        while (iter.hasNext()) {
            Projectile tiro = iter.next();
            tiro.setY((float)(tiro.getY() +  tiro.getSpeed() * Math.sin(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            tiro.setX((float)(tiro.getX() +  tiro.getSpeed() * Math.cos(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            if (tiro.outofbounds() || jogador.bulletDamage(tiro))
                iter.remove();

            if(chefao.overlaps(jogador)){
                jogador.loseHP();
            }
        }

    }

    /**
     * makes the player's shots move and collide with other objects
     */
    private void heroFireIterator(){


        Iterator<Projectile> iterNovo = heroBullets.iterator();
        Iterator<Minion> iterMinion = minions.iterator();
        while (iterNovo.hasNext()) {
            Projectile tiro = iterNovo.next();
            tiro.setY((float)(tiro.getY() +  tiro.getSpeed() * Math.sin(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            tiro.setX((float)(tiro.getX() +  tiro.getSpeed() * Math.cos(tiro.getAngle()) * Gdx.graphics.getDeltaTime()));
            if (tiro.outofbounds() || chefao.bulletDamage(tiro))
                iterNovo.remove();


            while (iterMinion.hasNext()) {
                Minion bicho = iterMinion.next();
                if(bicho.bulletDamage(tiro))
                    iterMinion.remove();
            }

        }
    }

    /**
     * makes the minions move and collide with the player
     */
    private void minionIterator(){

        Iterator<Minion> iterMinion = minions.iterator();
        while (iterMinion.hasNext()) {
            Minion bicho = iterMinion.next();
            bicho.move(jogador);
            if(jogador.minionDamage(bicho) || bicho.outofbounds())
                iterMinion.remove();
        }


    }

    /**
     * deals damage to the player if he gets too close to the boss
     * @return if he the attack was done or not
     */

    public Boolean swordAttack(){
        if(chefao.tooClose(jogador)){
            jogador.loseHP();
        return true;
        }
    return false;
    }

    /**
     * changes the game state to one of victory
     */

    private void winGame(){
        gameEnd = 1;

    }

    /**
     * changes the game state to one of defeat
     */

    private void loseGame(){
        gameEnd = 2;

    }

    /**
     * gets the game state
     * @return the game state
     */

    public int getGameState(){

        return gameEnd;
    }

}
