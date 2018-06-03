package com.underboss.test;

import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Minion;
import com.underboss.game.Model.Player;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinionTest extends GameTest{

    @Test
    public void move() {
        Boss chefe = new Boss(0,0);
        Minion bicho = new Minion(chefe);
        Player jogador = new Player(20,20);


        double deltaX1 = Math.abs(jogador.getX() - bicho.getX());
        double deltaY1 = Math.abs(jogador.getY() - bicho.getY());
        assertEquals(0,bicho.getX(),0.00001);
        assertEquals(0,bicho.getY(),0.00001);

        bicho.move(jogador);
        double deltaX2 = Math.abs(jogador.getX() - bicho.getX());
        double deltaY2 = Math.abs(jogador.getY() - bicho.getY());
        assertTrue(deltaX1 > deltaX2);
        assertTrue(deltaY1 > deltaY2);

        bicho.move(jogador);

        double deltaX3 = Math.abs(jogador.getX() - bicho.getX());
        double deltaY3 = Math.abs(jogador.getY() - bicho.getY());
        assertTrue(deltaX2 > deltaX3);
        assertTrue(deltaY2 > deltaY3);

    }

    @Test
    public void outofbounds() {
        Boss chefe = new Boss(0,0);
        Minion bicho = new Minion(chefe);
        Player jogador = new Player(-20,-20);
        assertFalse(bicho.outofbounds());
        assertEquals(0, bicho.getY(),0.00001);
        assertEquals(0, bicho.getX(),0.00001);
        bicho.move(jogador);
        assertTrue(bicho.outofbounds());
        bicho.setX(25);
        bicho.setY(25);
        assertFalse(bicho.outofbounds());
        bicho.setX(-25);
        bicho.setY(-25);
        assertTrue(bicho.outofbounds());

    }
}