package com.underboss.game.View;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.underboss.game.Control.GameManager;
import com.underboss.game.Model.Projectile;
import com.underboss.game.Underboss;


public class Ecra implements Screen {
    private Underboss game;
    private GameManager manager;
    private int OriginX;
    private int OriginY;

    Texture room;

    OrthographicCamera camera;


    public Ecra(Underboss gam) {
        this.game = gam;
        this.manager = new GameManager();

        loadTextures();

        initCamera();


    }

    private void initCamera(){

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }
    private void loadTextures(){

        room = new Texture(Gdx.files.internal("roomtemp.png"));
    }


    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the bucket and
        // all drops
        game.batch.begin();

        game.batch.draw(room, 0, 0);

        game.font.draw(game.batch, "HP do Boss: " + manager.chefao.getHP(), 0, 480);
        game.font.draw(game.batch, "HP do Heroi: " + manager.jogador.getHP(), 0, 40);

        game.batch.draw(manager.jogador.getPlayerImage(), manager.jogador.getX(), manager.jogador.getY());
        game.batch.draw(manager.chefao.getBossImage(), manager.chefao.getX(), manager.chefao.getY());


        drawProjectiles();
        game.batch.end();


       manager.inputs().processUserInput();
       manager.logic(delta);

    }



    public void drawProjectiles(){




       drawIndividualProjectiles(manager.bossBullets);
       drawIndividualProjectiles(manager.heroBullets);
    }


    public void drawIndividualProjectiles(Array<Projectile> balas){
        for (Projectile tiro : balas) {

           if(tiro.getHeight() < 0)
              balas.removeValue(tiro, true);

            OriginX = (int) tiro.getWidth() / 2;
            OriginY = (int) tiro.getHeight() / 2;
            TextureRegion regiao = new TextureRegion(tiro.getImagem());
            game.batch.draw(regiao, tiro.getX(), tiro.getY(), OriginX, OriginY, regiao.getRegionWidth(), regiao.getRegionHeight(), 1, 1, tiro.getDisparou().getAngle());
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
       // rainMusic.play();
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
      //tiroBoss.dispose();
        //dropImage.dispose();
       // bucketImage.dispose();
       // dropSound.dispose();
        //rainMusic.dispose();
    }

}