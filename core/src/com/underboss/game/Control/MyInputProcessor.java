package com.underboss.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.underboss.game.Model.Player;

public class MyInputProcessor implements InputProcessor {

    Player jogador;
    GameManager controlo;

    public MyInputProcessor(Player jogador){

        this.jogador = jogador;
    }

    public void setControlo(GameManager controlo) {
        this.controlo = controlo;
    }

    public void processUserInput(){

        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            jogador.setX(touchPos.x - 100 / 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            jogador.walkLeft();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            jogador.walkRight();

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            jogador.walkUp();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            jogador.walkDown();

//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            jogador();
//        }

    }

    public boolean keyDown (int keycode) {
        if(keycode == Input.Keys.W)
            controlo.heroFire();
//            jogador.walkLeft();
//        else if(keycode == Input.Keys.W)
//            jogador.walkUp();
//        else if(keycode == Input.Keys.D)
//            jogador.walkRight();
//        else if(keycode == Input.Keys.S)
//            jogador.walkDown();

        return false;
    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }
}
