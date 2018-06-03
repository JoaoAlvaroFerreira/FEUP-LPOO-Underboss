package com.underboss.game.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.underboss.game.Model.Player;
import com.underboss.game.View.Ecra;

public class MyInputProcessor implements InputProcessor {

    Player jogador;
    GameManager controlo;
    Ecra camera;

    public MyInputProcessor(Player jogador){

        this.jogador = jogador;
    }

    public void setControlo(GameManager controlo) {
        this.controlo = controlo;
    }

    public void setCamera(Ecra camera){this.camera = camera;}

    public void processUserInput(){

        // process user input
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.getCamera().unproject(touchPos);
            jogador.setX(touchPos.x - 64 / 2);
            jogador.setY(touchPos.y - 64/ 2);
        }


        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            jogador.walkLeft();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            jogador.walkRight();

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            jogador.walkUp();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            jogador.walkDown();



            if(Gdx.input.getGyroscopeX() > 3)
                controlo.heroFire(0);
            if(Gdx.input.getGyroscopeX() < -3)
                controlo.heroFire(180);
            if(Gdx.input.getGyroscopeY() > 3)
                controlo.heroFire(90);
            if(Gdx.input.getGyroscopeY() < -3)
                controlo.heroFire(270);


//        if(Gdx.input.getAccelerometerY() > 3)
//            controlo.heroFire(0);
//        if(Gdx.input.getAccelerometerY() < -3)
//            controlo.heroFire(180);
//        if(Gdx.input.getAccelerometerZ() > 3)
//            controlo.heroFire(90);
//        if(Gdx.input.getAccelerometerZ() < -3)
//            controlo.heroFire(270);


//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            jogador();
//        }

    }

    public boolean keyDown (int keycode) {

        if(keycode == Input.Keys.W)
            controlo.heroFire(90);
        else if(keycode == Input.Keys.S)
            controlo.heroFire(270);
       else if(keycode == Input.Keys.D)
            controlo.heroFire(0);
       else  if(keycode == Input.Keys.A)
            controlo.heroFire(180);



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
