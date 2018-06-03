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
import com.underboss.game.Model.Minion;
import com.underboss.game.Model.Projectile;
import com.underboss.game.Underboss;


public class Ecra implements Screen {
    private Underboss game;
    private GameManager manager;
    private int OriginX;
    private int OriginY;
    private int bossOriginX;
    private int bossOriginY;
    private int playerOriginX;
    private int playerOriginY;
    private TextureRegion regiaoChefe;
    private TextureRegion regiaoJogador;
    private TextureRegion regiaoSword;


    Texture room;
    Texture swords;
    Texture poison;



    OrthographicCamera camera;


    /**
     * the constructor for ecra, the main gameplay rendering class
     * @param gam
     */
    public Ecra(Underboss gam) {
        this.game = gam;
        this.manager = new GameManager();
        preload();
        manager.inputs().setCamera(this);
        gam.setManager(manager);

        initCamera();


    }

    /**
     * initiates the camera with its proper dimensions
     */

    private void initCamera(){

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    /**
     * loads images and initializes variables important to the class
     */
    private void preload(){

        bossOriginX = (int)manager.chefao.getWidth();
        bossOriginY = (int)manager.chefao.getHeight();
        playerOriginX = (int)manager.jogador.getWidth();
        playerOriginY = (int)manager.jogador.getHeight();
        regiaoJogador = new TextureRegion(manager.jogador.getPlayerImage());
        regiaoChefe =  new TextureRegion(manager.chefao.getBossImage());

        room = new Texture(Gdx.files.internal("roomtemp.png"));
        swords = new Texture(Gdx.files.internal("swords.png"));
        poison = new Texture(Gdx.files.internal("poisoned.png"));


        regiaoSword = new TextureRegion(swords);
    }

    /**
     * gets the current camera being used
     * @return
     */

    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * draws all of the gameplay on screen
     * @param delta
     */

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

        game.font.draw(game.batch, "Boss State: " + manager.chefao.getState() + "    Boss HP: " + manager.chefao.getHP(), 0, 480);
        game.font.draw(game.batch, "Your State: " +  manager.jogador.getState() + "    Your HP: " + manager.jogador.getHP() , 0, 40);

       // game.batch.draw(manager.jogador.getPlayerImage(), manager.jogador.getX(), manager.jogador.getY());


        bossOriginX = (int)manager.chefao.getWidth()/2;
        bossOriginY = (int)manager.chefao.getHeight()/2;
        playerOriginX = (int)manager.jogador.getWidth()/2;
        playerOriginY = (int)manager.jogador.getHeight()/2;
        if(manager.swordAttack())
            game.batch.draw(regiaoSword, manager.chefao.getX(), manager.chefao.getY(), bossOriginX, bossOriginY, regiaoSword.getRegionWidth(), regiaoSword.getRegionHeight(), 1, 1,(float)manager.chefao.getAngle());

        game.batch.draw(regiaoChefe, manager.chefao.getX(), manager.chefao.getY(), bossOriginX, bossOriginY, regiaoChefe.getRegionWidth(), regiaoChefe.getRegionHeight(), 1, 1,(float)manager.chefao.getAngle());
        game.batch.draw(regiaoJogador, manager.jogador.getX(), manager.jogador.getY(), playerOriginX, playerOriginY, regiaoJogador.getRegionWidth(), regiaoJogador.getRegionHeight(), 1, 1, (float)manager.jogador.getAngle());

        if(manager.jogador.getState() == "Poisoned")
        game.batch.draw(poison, manager.jogador.getX()-10, manager.jogador.getY());

        drawProjectiles();
        drawMinions();
        game.batch.end();


       manager.inputs().processUserInput();
       manager.logic(delta);


       if(manager.getGameState() != 0){
           EndScreen fim = new EndScreen(game);

           game.setScreen(new EndScreen(game));


       }

    }


    /**
     * calls the method to draw the projectiles of both the boss and the player
     */

    private void drawProjectiles(){

       drawIndividualProjectiles(manager.bossBullets);
       drawIndividualProjectiles(manager.heroBullets);
    }

    /**
     * draws any projectiles
     * @param balas
     */

    private void drawIndividualProjectiles(Array<Projectile> balas){
        for (Projectile tiro : balas) {

           if(tiro.getHeight() < 0)
              balas.removeValue(tiro, true);


            OriginX = (int) tiro.getWidth() / 2;
            OriginY = (int) tiro.getHeight() / 2;
            TextureRegion regiao = new TextureRegion(tiro.getImagem());
            game.batch.draw(regiao, tiro.getX(), tiro.getY(), OriginX, OriginY, regiao.getRegionWidth(), regiao.getRegionHeight(), 1, 1,(float)tiro.getAngleDegrees());
        }

    }

    /**
     * draws the minions spawned by the boss
     */

    private void drawMinions(){

        for(Minion bicho: manager.minions)
        {
            game.batch.draw(bicho.getMinionImage(), bicho.getX(), bicho.getY());
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