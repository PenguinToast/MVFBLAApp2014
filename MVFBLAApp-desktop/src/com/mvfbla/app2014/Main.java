package com.mvfbla.app2014;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class Main {
	public static void main(String[] args) {
		TexturePacker2.process("../assets/load", "../MVFBLAApp-android/assets/", "game");
		TexturePacker2.process("../assets/preload", "../MVFBLAApp-android/assets/", "preload");
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "MVFBLAApp";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 320;
		
		new LwjglApplication(new App2014Main(), cfg);
	}
}
