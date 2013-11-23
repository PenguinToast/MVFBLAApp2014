package com.mvfbla.app2014.gui.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mvfbla.app2014.Global;

public class TestScreen extends BaseScreen {
	public TestScreen() {
		table.setBackground("btnUp");
		table.debug();
		
		table.add(new TextButton("Top Button", Global.skin).pad(0)).expandX().top().colspan(3);
		table.row();
		table.add(new TextButton("Left Button", Global.skin).pad(0)).expandY().left();
		table.add(new TextButton("Middle Button", Global.skin).pad(0)).expand();
		table.add(new TextButton("Right Button", Global.skin).pad(0)).expandY().right();
		table.row();
		table.add(new TextButton("Bottom Button", Global.skin).pad(0)).expandX().bottom().colspan(3);
		
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
//		Table.drawDebug(stage);

	}

	@Override
	public void show() {
		Fighter fighter = new Fighter();
		fighter.setPosition(250, 250);
		stage.addActor(fighter);
	}
	
	public class Fighter extends Actor {
		private TextureRegion tex;
		public Fighter() {
			tex = Global.assets.get("game.atlas", TextureAtlas.class).findRegion("Fighter");
		}
		
		@Override
		public void draw(Batch batch, float parentAlpha) {
			batch.draw(tex, getX(), getY(), tex.getRegionWidth(), tex.getRegionHeight());
		}
	}
}
