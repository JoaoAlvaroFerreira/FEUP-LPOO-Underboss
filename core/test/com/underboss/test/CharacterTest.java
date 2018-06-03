package com.underboss.test;

import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Character;
import com.underboss.game.Model.Player;
import com.underboss.game.Model.Projectile;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest extends GameTest {

    @Test
    public void setAngle() {

        Character personagem = new Player(0,0);
        personagem.setAngle(140);
        assertEquals(140, personagem.getAngle(), 0.0001);
        personagem.setAngle(380.72);
        assertEquals(380.72, personagem.getAngle(), 0.0001);
        personagem.setAngle(-15);
        assertEquals(-15, personagem.getAngle(), 0.0001);

    }

    @Test
    public void getHP() {
        Character personagem = new Player(0,0);
        personagem.setHP(25);
        assertEquals(25, personagem.getHP(), 0.0001);
        personagem.setHP(-25);
        assertEquals(-25, personagem.getHP(), 0.0001);
        personagem.setHP(0);
        assertEquals(0, personagem.getHP(), 0.0001);
        personagem.setHP(100000);
        assertEquals(100000, personagem.getHP(), 0.0001);

    }

    @Test
    public void loseHP() {
        Character personagem = new Boss(0,0);
        personagem.setHP(1);
        assertEquals(1, personagem.getHP(), 0.0001);
        personagem.loseHP();
        assertEquals(0, personagem.getHP(), 0.0001);
        personagem.loseHP();
        assertEquals(0, personagem.getHP(), 0.0001);

    }

    @Test
    public void checkState() {
        Character personagem = new Boss(0,0, 3);
        assertEquals("Healthy", personagem.getState());
        personagem.loseHP();
        personagem.checkState();
        assertEquals("Healthy", personagem.getState());
        personagem.loseHP();
        personagem.checkState();
        assertEquals("Dying", personagem.getState());
        personagem.loseHP();
        personagem.checkState();
        assertEquals("Dead", personagem.getState());

    }

    @Test
    public void setHP() {
        Character personagem = new Player(0,0);
        personagem.setHP(70);
        assertEquals(70, personagem.getHP(), 0.0001);
        personagem.setHP(-125);
        assertEquals(-125, personagem.getHP(), 0.0001);
        personagem.setHP(0);
        assertEquals(0, personagem.getHP(), 0.0001);
        personagem.setHP(999999);
        assertEquals(999999, personagem.getHP(), 0.0001);

    }

    @Test
    public void getAngle() {

        Character personagem = new Player(0,0);
        personagem.setAngle(140000);
        assertEquals(140000, personagem.getAngle(), 0.0001);
        personagem.setAngle(380.772);
        assertEquals(380.772, personagem.getAngle(), 0.0001);
        personagem.setAngle(-15);
        assertEquals(-15, personagem.getAngle(), 0.0001);
        personagem.setAngle(0);
        assertEquals(0, personagem.getAngle(), 0.0001);

    }

    @Test
    public void bulletDamage() {
        Character personagem = new Boss(0,0,25);
        Character jogador = new Player(0,0);
        Projectile tiro = new Projectile(jogador, 300, 0, 40, 30);
        assertEquals(25, personagem.getHP(), 0.0001);
        personagem.bulletDamage(tiro);
        assertEquals(24, personagem.getHP(), 0.0001);
        personagem.bulletDamage(tiro);
        assertEquals(23, personagem.getHP(), 0.0001);

    }

    @Test
    public void getState() {

        Character personagem = new Boss(0,0, 3);
        assertEquals("Healthy", personagem.getState());
        personagem.loseHP();
        personagem.checkState();
        assertEquals("Healthy", personagem.getState());
        personagem.loseHP();
        personagem.checkState();
        assertEquals("Dying", personagem.getState());
        personagem.loseHP();
        personagem.checkState();
        assertEquals("Dead", personagem.getState());
        personagem.setState(1);
        assertEquals("Poisoned", personagem.getState());
        personagem.setState(3);
        assertEquals("Dead", personagem.getState());
        personagem.setState(4);
        assertEquals("Invincible", personagem.getState());
    }

    @Test
    public void setState() {
        Character personagem = new Boss(0,0, 3);
        personagem.setState(0);
        assertEquals("Healthy", personagem.getState());
        personagem.setState(1);
        assertEquals("Poisoned", personagem.getState());
        personagem.setState(2);
        assertEquals("Dying", personagem.getState());
        personagem.setState(3);
        assertEquals("Dead", personagem.getState());
        personagem.setState(4);
        assertEquals("Invincible", personagem.getState());
    }

    @Test
    public void poisonTick() {
    Character personagem = new Boss(0,0,20);
    personagem.setState(1);
    assertEquals("Poisoned", personagem.getState());
    assertEquals(20, personagem.getHP(), 0.000001);
    personagem.poisonTick();
        personagem.poisonTick();
        personagem.poisonTick();
        personagem.poisonTick();
        personagem.poisonTick();
    assertTrue(20 >= personagem.getHP());

    }

    @Test
    public void recalibrate() {
        Character personagem = new Player(900,-15, 10);
        assertEquals(-15, personagem.getY(),0.00001);
        assertEquals(900, personagem.getX(),0.00001);
        personagem.recalibrate();
        assertEquals(0, personagem.getY(),0.00001);
        assertEquals(800- 48, personagem.getX(),0.00001);

    }
}