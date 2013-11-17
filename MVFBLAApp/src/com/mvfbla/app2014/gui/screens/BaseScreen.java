package com.mvfbla.app2014.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mvfbla.app2014.Global;

public abstract class BaseScreen implements Screen {
	protected Stage stage;
	protected Table table;

	public BaseScreen() {
		OrthographicCamera camera = new OrthographicCamera(Global.WIDTH, Global.HEIGHT);

		stage = new Stage(Global.WIDTH, Global.HEIGHT, false);
		stage.setCamera(camera);
		
		Gdx.input.setInputProcessor(stage);
		
		table = new Table(Global.skin);
		table.setFillParent(true);
		stage.addActor(table);
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
		stage.setViewport(1080, 1080, false);
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
