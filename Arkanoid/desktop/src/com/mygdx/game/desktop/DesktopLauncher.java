/*
DesktopLauncher.java
Usman Farooqi & Ghanem Ghanem
launcher class for the Arkanoid game
 */
package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	//hello world
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config);
		config.width = 672; // sets the width of screen
		config.height = 768; // set height of screen
		config.title = "Arkanoid";

	}
}
