package com.underboss.test;

import com.underboss.game.Model.Boss;
import com.underboss.game.Model.Projectile;
import com.underboss.test.GameTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectileTest extends GameTest {

    @Test
    public void setAngle() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getAngle() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getAngleDegrees() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getDisparou() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getPoison() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void setAngle1() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getAngle1() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getAngleDegrees1() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getSpeed() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void getDisparou1() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);

    }

    @Test
    public void getPoison1() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }

    @Test
    public void outofbounds() {
        Boss chefe = new Boss(0,0,20);
        Projectile tiro = new Projectile(chefe, chefe.getFireSpeed(), (float) chefe.getAngle(), 80, 70);
    }
}