package com.underboss.test;

import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Player;
import com.underboss.game.Model.Projectile;
import com.underboss.test.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectileTest extends GameTest {

    @Test
    public void setAngle() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
        assertEquals(0,tiro.getAngleDegrees(),0.00001);
        tiro.setAngle(180);
        assertEquals(180,tiro.getAngleDegrees(),0.00001);
        tiro.setAngle(-153);
        assertEquals(-153,tiro.getAngleDegrees(),0.00001);
    }

    @Test
    public void getAngle() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
        assertEquals(0,tiro.getAngle(),0.00001);
        tiro.setAngle(180);
        assertEquals(Math.toRadians(180),tiro.getAngle(),0.00001);
        tiro.setAngle(-153);
        assertEquals(Math.toRadians(-153),tiro.getAngle(),0.00001);
    }

    @Test
    public void getAngleDegrees() { Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
        assertEquals(0,tiro.getAngleDegrees(),0.00001);
        tiro.setAngle(180);
        assertEquals(180,tiro.getAngleDegrees(),0.00001);
        tiro.setAngle(-153);
        assertEquals(-153,tiro.getAngleDegrees(),0.00001);
    }

    @Test
    public void getDisparou() {
        Boss chefe = new Boss(0,0,20);
        Player jogador = new Player(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
        Projectile tiro2 = new Projectile(jogador, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);

        assertEquals("Boss",tiro.getDisparou().getClass().getSimpleName());
        assertEquals("Player",tiro2.getDisparou().getClass().getSimpleName());

    }

    @Test
    public void getPoison() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
        assertFalse(tiro.getPoison());
        chefe.setState(2);
        Projectile tiro2 = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
        tiro2.setBossTiro();
        assertTrue(tiro2.getPoison());
    }



    @Test
    public void outofbounds() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
        assertFalse(tiro.outofbounds());
        assertEquals(0, tiro.getY(),0.00001);
        assertEquals(0, tiro.getX(),0.00001);
        tiro.setX(-25);
        assertTrue(tiro.outofbounds());
        tiro.setX(25);
        tiro.setY(25);
        assertFalse(tiro.outofbounds());
        tiro.setX(-25);
        tiro.setY(-25);
        assertTrue(tiro.outofbounds());
    }
}