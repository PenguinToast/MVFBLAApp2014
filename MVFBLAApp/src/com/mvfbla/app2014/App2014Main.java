package com.mvfbla.app2014;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mvfbla.app2014.gui.screens.LoadingScreen;
import com.mvfbla.app2014.gui.screens.TransitionScreen;

public class App2014Main extends Game {
	public static final String TITLE = "MVFBLAApp2014", VERSION = "v0.0.1";
	@Override
	public void create() {		
		Global.game = this;
		setScreen(new LoadingScreen());
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	public void transition(Screen target) {
		setScreen(new TransitionScreen(getScreen(), target));
	}
}
