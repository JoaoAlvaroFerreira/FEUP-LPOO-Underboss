package com.underboss.game;

public class Projectile {
    Character disparou;
    int x;
    int y;
    int speed;
    int direction; //0-360, começando em apontando para cima
    int acceleration;

    Projectile(Character personagem, int nSpeed, int nDirection, int nAcceleration){
    disparou = personagem;
    x = personagem.getX();
    y = personagem.getY();
    speed = nSpeed;
    direction = nDirection;
    acceleration = nAcceleration;
    }

    Projectile(Character personagem, int xPos, int yPos, int nSpeed, int nDirection, int nAcceleration){
        disparou = personagem;
        speed = nSpeed;
        direction = nDirection;
        acceleration = nAcceleration;
    }


}
