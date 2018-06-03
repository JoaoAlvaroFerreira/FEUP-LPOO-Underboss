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

	public void create() {
		batch = new SpriteBatch();

		font = new BitmapFont();
		this.setScreen(new Overworld(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public void setManager(GameManager gestor){
		manager = gestor;
	}

}