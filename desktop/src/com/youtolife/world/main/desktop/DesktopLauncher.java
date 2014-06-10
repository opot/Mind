package com.youtolife.world.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.youtolife.world.MainGame;

public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "WorldAdventure";
		cfg.width = 800;
		cfg.height = 600;
		
		new LwjglApplication(new MainGame(), cfg);
	}
}
