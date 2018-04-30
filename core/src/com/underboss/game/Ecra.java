package com.underboss.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;



public class Ecra implements Screen {
    final StartGame game;

    Texture tiroBoss;
    Texture room;
    Texture tiroHero;
    // Texture dropImage;
    //Texture bucketImage;
    //Sound dropSound;
    //Music rainMusic;
    OrthographicCamera camera;
    Player jogador;
    Boss chefao;
   // Rectangle bucket;
    Array<Rectangle> raindrops;
    Array<Projectile> bossBullets;
    Array<Projectile> heroBullets;
    int OriginX;
    int OriginY;
    long lastDropTime;
    long lastShotTime;
    int dropsGathered;


    public Ecra(final StartGame gam) {
        this.game = gam;

        //init the Player
        jogador = new Player (800 / 2 -  48 / 2,20);
        jogador.setHP(5);

        //init the Boss
        chefao = new Boss(800 / 2 -  78 / 2, 380);
        chefao.setHP(20);

        // load the images for the droplet and the bucket, 64x64 pixels each
       // dropImage = new Texture(Gdx.files.internal("1 (1).png"));
        tiroBoss = new Texture(Gdx.files.internal("bossShot1.png"));
        tiroHero = new Texture(Gdx.files.internal("heroShot1.png"));
        room = new Texture(Gdx.files.internal("roomtemp.png"));


        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

       /* // create a Rectangle to logically represent the bucket
        bucket = new Rectangle();
        bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
        bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
        // the bottom screen edge
        bucket.width = 64;
        bucket.height = 64; */

        // create the raindrops array and spawn the first raindrop
        raindrops = new Array<Rectangle>();
       bossBullets = new Array<Projectile>();
       heroBullets = new Array<Projectile>();
       bossFire();
       // spawnRaindrop();

    }
/*
    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    */

   private void bossFire() {
        Projectile tiro = new Projectile(chefao, 200, 180,0, 46, 13 );
        tiro.setTiro(tiroBoss);
        bossBullets.add(tiro);

        lastShotTime = TimeUtils.nanoTime();
    }

    private void heroFire() {
        Projectile tiro = new Projectile(jogador, 200, 180,0, 46, 13 );
        tiro.setTiro(tiroHero);
        heroBullets.add(tiro);
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
        game.batch.draw(room,0,0);
        game.font.draw(game.batch, "HP do Boss: " + chefao.getHP(), 0, 480);
        game.font.draw(game.batch, "HP do Heroi: " + jogador.getHP(), 0, 40);
        //game.batch.draw(bucketImage, bucket.x, bucket.y);
        game.batch.draw(jogador.getPlayerImage(), jogador.getX(), jogador.getY());
        game.batch.draw(chefao.getBossImage(), chefao.getX(), chefao.getY());


       for(Projectile tiro: bossBullets)
       {
           OriginX = (int)tiro.getWidth() / 2;
           OriginY = (int)tiro.getHeight() / 2;
           TextureRegion regiao = new TextureRegion(tiro.getImagem());
           game.batch.draw(regiao, tiro.getX(), tiro.getY(), OriginX, OriginY,regiao.getRegionWidth(),regiao.getRegionHeight(), 1, 1 ,270);
       }

        for(Projectile tiro: heroBullets)
        {
            OriginX = (int)tiro.getWidth() / 2;
            OriginY = (int)tiro.getHeight() / 2;
            TextureRegion regiao = new TextureRegion(tiro.getImagem());
            game.batch.draw(regiao, tiro.getX(), tiro.getY(), OriginX, OriginY,regiao.getRegionWidth(),regiao.getRegionHeight(), 1, 1 ,90);
        }
        game.batch.end();


        // process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            jogador.setX(touchPos.x - 100 / 2);
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT))
           jogador.setX(jogador.getX() - 400 * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            jogador.setX(jogador.getX() + 400 * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Keys.UP))
            jogador.setY(jogador.getY() + 400 * Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            jogador.setY(jogador.getY() - 400 * Gdx.graphics.getDeltaTime());

        if(Gdx.input.isKeyPressed(Keys.SPACE))
            heroFire();


        // make sure the bucket stays within the screen bounds
        if (jogador.getX() < 0)
            jogador.setX(0);
        if (jogador.getX() > 800 - 48)
            jogador.setX(800- 48);
        if (jogador.getY() < 0)
            jogador.setY(0);
        if (jogador.getY() > 480 - 78)
            jogador.setY(480-78);
        /*
        if (bucket.x > 800 - 64)
            bucket.x = 800 - 64;
        if (bucket.y < 0)
            bucket.y = 0;
        if (bucket.y > 480 - 64)
            bucket.y = 480 - 64;
*/
        // check if we need to create a new raindrop
       /* if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();*/

        if (TimeUtils.nanoTime() - lastShotTime > 1000000000)
            bossFire();

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
       /* Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(jogador)) {
                dropsGathered++;
              //  dropSound.play();
                iter.remove();
            }
        } */

        Iterator<Projectile> iter = bossBullets.iterator();
        while (iter.hasNext()) {
            Projectile tiro = iter.next();
            tiro.setY(tiro.getY() -  200 * Gdx.graphics.getDeltaTime());
            if (tiro.getX() + 64 < 0)
                iter.remove();

            if (tiro.overlaps(jogador)) {
                jogador.setHP(jogador.getHP() - 1);
                //  dropSound.play();
                iter.remove();
            }
        }

        Iterator<Projectile> iterNovo = heroBullets.iterator();
        while (iterNovo.hasNext()) {
            Projectile tiro = iterNovo.next();
            tiro.setY(tiro.getY() +  200 * Gdx.graphics.getDeltaTime());
            if (tiro.getX() + 64 > 480)
                iterNovo.remove();

            if (tiro.overlaps(chefao)) {
                chefao.setHP(chefao.getHP() - 1);
                //  dropSound.play();
                iterNovo.remove();
            }
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
       tiroBoss.dispose();
        //dropImage.dispose();
       // bucketImage.dispose();
       // dropSound.dispose();
        //rainMusic.dispose();
    }

}