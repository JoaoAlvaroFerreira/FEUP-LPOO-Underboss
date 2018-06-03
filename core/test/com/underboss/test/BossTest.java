package com.underboss.test;

import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Player;

import org.junit.Test;

import static org.junit.Assert.*;

public class BossTest extends GameTest {

    @Test
    public void getFireFrequency() {
        Boss chefe = new Boss(0,0, 20);
        assertEquals(2000000000, chefe.getFireFrequency(), 0.00001);
        chefe.doubleFire();
        assertEquals(1000000000, chefe.getFireFrequency(), 0.00001);
    }


    @Test
    public void move() {
        Boss chefe = new Boss(0,0, 20);
        assertEquals(0, chefe.getX(), 0.00001);
        assertEquals(0, chefe.getY(), 0.00001);
        chefe.move(0.1);
        assertNotEquals(0, chefe.getX(), 0.00001);
        assertNotEquals(0, chefe.getY(), 0.00001);


    }

    @Test
    public void doubleFire() {
        Boss chefe = new Boss(0,0, 20);
        assertEquals(2000000000, chefe.getFireFrequency(), 0.00001);
        assertEquals(100,chefe.getFireSpeed(),0.00001);
        chefe.doubleFire();
        assertEquals(1000000000, chefe.getFireFrequency(), 0.00001);
        assertEquals(200,chefe.getFireSpeed(),0.00001);
        chefe.doubleFire();
        assertEquals(500000000, chefe.getFireFrequency(), 0.00001);
        assertEquals(400,chefe.getFireSpeed(),0.00001);
        chefe.doubleFire();
        assertEquals(250000000, chefe.getFireFrequency(), 0.00001);
        assertEquals(800,chefe.getFireSpeed(),0.00001);


    }

    @Test
    public void getFireSpeed() {
        Boss chefe = new Boss(0,0, 20);
        assertEquals(100,chefe.getFireSpeed(),0.00001);
        chefe.doubleFire();
        assertEquals(200,chefe.getFireSpeed(),0.00001);
        chefe.doubleFire();
        assertEquals(400,chefe.getFireSpeed(),0.00001);
        chefe.doubleFire();
        assertEquals(800,chefe.getFireSpeed(),0.00001);
    }

    @Test
    public void angleChange() {
        Boss chefe = new Boss(0,0, 20);
        Player jogador = new Player(2,2,20);
        assertEquals(0, chefe.getAngle(),0.00001);
        chefe.angleChange(jogador);
        assertEquals(45, chefe.getAngle(),2);
    }

    @Test
    public void tooClose() {

        Boss chefe = new Boss(0,0, 20);
        Player jogador1 = new Player(2,2,20);
        Player jogador2 = new Player(300,2,20);
        assertTrue(chefe.tooClose(jogador1));
        assertFalse(chefe.tooClose(jogador2));
    }
}