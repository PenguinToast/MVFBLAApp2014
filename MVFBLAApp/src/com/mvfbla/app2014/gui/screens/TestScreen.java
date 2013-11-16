package com.mvfbla.app2014.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TestScreen implements Screen {
	protected Stage stage;

	public TestScreen() {
		OrthographicCamera camera = new OrthographicCamera(1, Gdx.graphics.getHeight()/Gdx.graphics.getWidth());

		stage = new Stage();
		stage.setCamera(camera);
		
		Gdx.input.setInputProcessor(stage);
		
		Skin skin = new Skin(Gdx.files.internal("data/skin.json"), new TextureAtlas("game.atlas"));
		Table table = new Table(skin);	
		stage.addActor(table);
		table.setFillParent(true);
		table.setBackground("btnUp");
		
		table.add(new TextButton("TEST BasddasdddasdUTTON", skin).pad(0));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(400, width/height * 400);
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
