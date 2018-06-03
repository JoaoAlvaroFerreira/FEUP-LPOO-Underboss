package com.underboss.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.underboss.game.Underboss;

import java.util.concurrent.TimeUnit;

public class Tutorial implements Screen {
    private Underboss game;
    OrthographicCamera camera;
    Texture tutorialScreen;


    public Tutorial(final Underboss gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.resize(800,400);
        tutorialScreen = new Texture(Gdx.files.internal("tutorialScreen.PNG"));


    }

    @Override
    public void render(float delta) {


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(tutorialScreen, 0, 0);
        game.batch.end();

        if (Gdx.input.justTouched()) {

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
