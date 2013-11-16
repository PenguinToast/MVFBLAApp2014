package com.mvfbla.app2014.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TestScreen implements Screen {
	protected Stage stage;
	private Table table;

	public TestScreen() {
		OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		stage = new Stage();
		stage.setCamera(camera);
		
		Gdx.input.setInputProcessor(stage);
		
		Skin skin = new Skin(Gdx.files.internal("data/skin.json"), new TextureAtlas("game.atlas"));
		table = new Table(skin);	
		stage.addActor(table);
		table.setFillParent(true);
		table.setBackground("btnUp");
		table.debug();
		
		table.add(new TextButton("Top Button", skin).pad(0)).expandX().top().colspan(3);
		table.row();
		table.add(new TextButton("Left Button", skin).pad(0)).expandY().left();
		table.add(new TextButton("Middle Button", skin).pad(0)).expand();
		table.add(new TextButton("Right Button", skin).pad(0)).expandY().right();
		table.row();
		table.add(new TextButton("Bottom Button", skin).pad(0)).expandX().bottom().colspan(3);
		
		Fighter fighter = new Fighter();
		fighter.setPosition(250, 250);
		stage.addActor(fighter);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(1000, 1000, true);
//		table.layout();
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
	
	public class Fighter extends Actor {
		private Texture tex;
		public Fighter() {
			tex = new Texture("data/Fighter.gif");
		}
		
		@Override
		public void draw(SpriteBatch batch, float parentAlpha) {
			batch.draw(tex, getX(), getY(), tex.getWidth(), tex.getHeight());
		}
	}
}
