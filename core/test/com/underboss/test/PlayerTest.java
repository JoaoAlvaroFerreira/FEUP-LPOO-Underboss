package com.underboss.test;

import com.badlogic.gdx.Gdx;
import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Character;
import com.underboss.game.Model.Minion;
import com.underboss.game.Model.Player;
import com.underboss.game.Model.Projectile;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest extends GameTest{

    @Test
    public void walkUp() {
        Player jogador = new Player(500,500,20);
        assertEquals(500,jogador.getX(),0.0000001);
        jogador.walkUp();
        assertEquals(500 + 400 * Gdx.graphics.getDeltaTime(),jogador.getX(),9);
        jogador.walkUp();
        assertEquals(500 + 800 * Gdx.graphics.getDeltaTime(),jogador.getX(),18);

    }

    @Test
    public void walkDown() {
        Player jogador = new Player(500,500,20);
        assertEquals(500,jogador.getX(),0.0000001);
        jogador.walkDown();
        assertEquals(500 - 400 * Gdx.graphics.getDeltaTime(),jogador.getX(),9);
        jogador.walkDown();
        assertEquals(500 - 800 * Gdx.graphics.getDeltaTime(),jogador.getX(),18);
    }

    @Test
    public void walkLeft() {
        Player jogador = new Player(500,500,20);
        assertEquals(500,jogador.getX(),0.0000001);
        jogador.walkLeft();
        assertEquals(500 - 400 * Gdx.graphics.getDeltaTime(),jogador.getY(),9);
        jogador.walkLeft();
        assertEquals(500 - 800 * Gdx.graphics.getDeltaTime(),jogador.getY(),18);
    }

    @Test
    public void walkRight() {
        Player jogador = new Player(500,500,20);
        assertEquals(500,jogador.getX(),0.0000001);
        jogador.walkRight();
        assertEquals(500 + 400 * Gdx.graphics.getDeltaTime(),jogador.getY(),9);
        jogador.walkRight();
        assertEquals(500 + 800 * Gdx.graphics.getDeltaTime(),jogador.getY(),18);
    }

    @Test
    public void minionDamage() {
        Player jogador = new Player(500,500,20);
        jogador.checkState();
        Boss chefe = new Boss (500,500,20);
        Minion bicho = new Minion(chefe);

        assertEquals(20, jogador.getHP(),0.00001);
        jogador.minionDamage(bicho);
        assertEquals(18, jogador.getHP(),0.00001);
        jogador.setHP(5);
        jogador.checkState();
        jogador.minionDamage(bicho);
        assertEquals(5, jogador.getHP(),0.00001);

    }

    @Test
    public void makePlayerInvincible() {
        Player jogador = new Player(500,500,20);
        assertEquals(20, jogador.getHP(),0.00001);
        jogador.loseHP();
        assertEquals(19, jogador.getHP(),0.00001);
        jogador.makePlayerInvincible();
        Character personagem = new Boss(500,500,25);
        Projectile tiro = new Projectile(personagem, 300, 0, 40, 30);
        jogador.bulletDamage(tiro);
        jogador.loseHP();
        assertEquals(19, jogador.getHP(),0.00001);
        jogador.makePlayerNormal();
        jogador.loseHP();
        assertEquals(18, jogador.getHP(),0.00001);
    }

    @Test
    public void makePlayerNormal() {
        Player jogador = new Player(500,500,5);
        assertEquals(5, jogador.getHP(),0.00001);
        jogador.loseHP();
        assertEquals(4, jogador.getHP(),0.00001);
        jogador.loseHP();
        assertEquals(4, jogador.getHP(),0.00001);
        jogador.makePlayerNormal();
        jogador.loseHP();
        assertEquals(3, jogador.getHP(),0.00001);
    }

    @Test
    public void loseHP() {
        Player jogador = new Player(500,500,20);
        assertEquals(20, jogador.getHP(),0.00001);
        jogador.loseHP();
        assertEquals(19, jogador.getHP(),0.00001);
        jogador.makePlayerNormal();
        jogador.loseHP();
        assertEquals(18, jogador.getHP(),0.00001);
        jogador.loseHP();
        assertEquals(18, jogador.getHP(),0.00001);
    }

    @Test
    public void checkState() {
        Player jogador = new Player(500,500,20);
        jogador.checkState();
        assertEquals("Healthy", jogador.getState());
        jogador.setState(1);
        jogador.checkState();
        assertEquals("Poisoned", jogador.getState());
        jogador.setState(0);
        jogador.setHP(4);
        jogador.checkState();
        assertEquals("Dying", jogador.getState());
        jogador.setHP(0);
        jogador.checkState();
        assertEquals("Dead", jogador.getState());
        jogador.makePlayerInvincible();
        assertEquals("Invincible", jogador.getState());
    }
}