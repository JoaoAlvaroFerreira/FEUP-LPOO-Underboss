package com.underboss.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.underboss.game.Control.GameManager;
import com.underboss.game.View.Overworld;

public class Underboss extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public GameManager manager;

	/**
	 * creates a new batch and font, and sets up the intro screen
	 */
	public void create() {
		batch = new SpriteBatch();

		font = new BitmapFont();
		this.setScreen(new Overworld(this));
	}

	/**
	 * draws everything
	 */
	public void render() {
		super.render();
	}

	/**
	 * disposes of the assets once the game is done
	 */
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	/**
	 * sets a GameManager for this Game
	 * @param gestor
	 */

	public void setManager(GameManager gestor){
		manager = gestor;
	}

}