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

    /**
     * constructor for the tutorial class
     * @param gam
     */
    public Tutorial(final Underboss gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        this.resize(800,400);
        tutorialScreen = new Texture(Gdx.files.internal("tutorialScreen.PNG"));


    }

    /**
     * renders the tutorial class for the game
     * @param delta
     */
    @Override
    public void render(float delta) {


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(tutorialScreen, 0, 0);
        game.batch.end();

        if (Gdx.input.justTouched()) {

            game.setScreen(new Ecra(game));


        }
    }


    /**
     * resizes the screen if needed (not used)
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {
    }

    /**
     * shows the screen (not used)
     */
    @Override
    public void show() {

    }

    /**
     * hides the screen (not used)
     */

    @Override
    public void hide() {
    }

    /**
     * pauses the screen activities
     */

    @Override
    public void pause() {
    }

    /**
     * resumes the screen activities
     */
    @Override
    public void resume() {
    }

    /**
     * disposes of the elements previously loaded onto the screen
     */

    @Override
    public void dispose() {

        game.batch.dispose();
        game.font.dispose();
    }
}
