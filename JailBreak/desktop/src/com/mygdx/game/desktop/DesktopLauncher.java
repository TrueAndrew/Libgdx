package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.JailBreak;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = JailBreak.WIDTH;
		config.height = JailBreak.HEIGHT;
		config.title = JailBreak.TITLE;
		new LwjglApplication(new JailBreak(), config);
	}
}
