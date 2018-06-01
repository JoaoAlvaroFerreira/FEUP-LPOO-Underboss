package com.underboss.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.underboss.game.Underboss;

public class Overworld implements Screen {
    private Underboss game;
    OrthographicCamera camera;
    Texture initScreen;
  //  Texture Shooter1;
    //Stage palco;
  //  Button startGame;


    public Overworld(final Underboss gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.resize(800,400);
       // palco = new Stage(new ScreenViewport());
        initScreen = new Texture(Gdx.files.internal("initScreen.PNG"));
     //   Shooter1 = new Texture(Gdx.files.internal("Shooter1.PNG"));
    //   palco.addActor(startGame);
      //  Gdx.input.setInputProcessor(palco);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
//        game.font.draw(game.batch, "Welcome to Underboss!!! ", 100, 150);
//        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.draw(initScreen, 0,0);
      //  game.batch.draw(Shooter1, 800,480);
        game.batch.end();
     //   palco.draw();
        if(Gdx.input.isTouched()){
        //if (Gdx.input.getX() > 335 && Gdx.input.getX() < 430 && Gdx.input.getY() > 285 && Gdx.input.getY() < 310) {
            game.setScreen(new Ecra(game));
            dispose();
        }
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
