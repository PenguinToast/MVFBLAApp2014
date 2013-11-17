package com.mvfbla.app2014.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mvfbla.app2014.Global;

public class LoadingScreen extends BaseScreen {
	private AssetManager assets;
	private static final float delay = 1.0f;
	private float timer;
	private ShapeRenderer shape;
	
	public LoadingScreen() {
		super();
		assets = new AssetManager();
		Global.assets = assets;
		
		assets.load("preload.atlas", TextureAtlas.class);
		assets.finishLoading();
		TextureAtlas preload = assets.get("preload.atlas", TextureAtlas.class);
		
		Image logo = new Image(preload.findRegion("fblalogo"));
		table.add(logo).expand();
		
		assets.load("game.atlas", TextureAtlas.class);
		
		shape = new ShapeRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLACK);
		shape.rect(0, 0, stage.getWidth(), stage.getHeight());	
		shape.end();

		stage.act(delta);
		stage.draw();
		timer += delta;
		if(assets.update() && timer > delay) {
			Global.skin = new Skin(Gdx.files.internal("data/skin.json"), assets.get("game.atlas", TextureAtlas.class));
			Global.skin.addRegions(assets.get("preload.atlas", TextureAtlas.class));
			Global.game.transition(new TestScreen());
		}
		Gdx.gl.glDisable(GL10.GL_BLEND);
	
	}

}
