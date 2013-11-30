package com.mvfbla.app2014.gui.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mvfbla.app2014.App2014Main;
import com.mvfbla.app2014.Global;
import com.mvfbla.app2014.tween.ActorAccessor;

public class MenuScreen extends BaseScreen {

	final int PADDING = 15;
	final int BUTTON_WIDTH = 350;
	final int BUTTON_HEIGHT = 50;
	
	private TextButton buttonExit, buttonPlay, settings;	// The buttons to play and exit
	private Label heading;	// the title
	private TweenManager tweenManager;
	
	public MenuScreen() {
		// Creating Animations
		tweenManager = new TweenManager();
	}
	@Override
	public void render(float delta) {
		tweenManager.update(delta);
		super.render(delta);
	}


	@Override
	public void show() {
		buttonPlay = new TextButton("ENTER FORUMS", Global.skin);
		/*buttonPlay.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new LevelMenu());;
			}
		});*/
		buttonPlay.pad(PADDING);
		
		settings = new TextButton("SETTINGS", Global.skin);
		/*settings.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				((Game)Gdx.app.getApplicationListener()).setScreen(new Settings());
			}
		});*/
		settings.pad(PADDING);
		
		buttonExit = new TextButton("EXIT", Global.skin);
		buttonExit.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		buttonExit.pad(PADDING);
		
		
		//Creating heading
		heading = new Label(App2014Main.TITLE, Global.skin);
		heading.setFontScale(2);
		
		// Putting stuff together
		table.add(heading);
		table.getCell(heading).spaceBottom(3*BUTTON_HEIGHT).row();
		table.add(buttonPlay).uniform().spaceBottom(BUTTON_HEIGHT).row();
		table.add(settings).uniform().spaceBottom(BUTTON_HEIGHT).row();
		table.add(buttonExit).uniform();

		Tween.registerAccessor(Actor.class, new ActorAccessor());
		Timeline.createSequence().beginSequence()
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 0, 1))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 0))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 0))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 0))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 1))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 1))
			.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 1))
			.end().repeat(Tween.INFINITY, 0).start(tweenManager);
		
		// Heading and buttons fade in
		Timeline.createSequence().beginSequence()
			.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(settings, ActorAccessor.ALPHA).target(0))
			.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
			.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
			.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .25f).target(1))
			.push(Tween.to(settings, ActorAccessor.ALPHA, .25f).target(1))
			.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
			.end().start(tweenManager);
		
		// Table fade in
		Tween.from(table, ActorAccessor.ALPHA, .5f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .8f).target(Gdx.graphics.getHeight()/8).start(tweenManager);
	}
}
