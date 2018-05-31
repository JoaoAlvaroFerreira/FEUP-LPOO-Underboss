package com.underboss.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.underboss.game.Underboss;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Underboss";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new Underboss(), config);
	}
}
