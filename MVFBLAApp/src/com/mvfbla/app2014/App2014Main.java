package com.mvfbla.app2014;

import com.badlogic.gdx.Game;
import com.mvfbla.app2014.gui.screens.TestScreen;

public class App2014Main extends Game {
	
	@Override
	public void create() {		
		setScreen(new TestScreen());
	}
	
	@Override
	public void render() {
		super.render();
	}
}
