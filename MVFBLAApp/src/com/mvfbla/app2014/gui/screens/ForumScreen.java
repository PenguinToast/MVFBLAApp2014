package com.mvfbla.app2014.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;


public class ForumScreen extends BaseScreen {
	
	final String sampleData = "This is the first question";
	public ForumScreen() {
		
	}
	
	@Override
	public void render(float delta) {
		// Clear Colors
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
	}
}