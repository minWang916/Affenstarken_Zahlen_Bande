package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();


		config.setForegroundFPS(60);
		config.setTitle("Affenstarke Zahlen-Bande PC");
		config.setWindowedMode(1000,800);
		config.setWindowIcon("img/monkey_icon.png");

		new Lwjgl3Application(new Game(), config);
	}
}
