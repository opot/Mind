package com.youtolife.world.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.youtolife.world.GameState;
import com.youtolife.world.MainGame;
import com.youtolife.world.StateBasedGame;

public class EndOfGameState extends GameState {

	public EndOfGameState(int id, MainGame game) {
		super(id,game);
	}

	/**
	 */
	Sprite img = null;
	/**
	 */
	BitmapFont font;
	/**
	 */
	MainGame g;
	
	public void enter(StateBasedGame game) {
		if (Gdx.files.local("Saves/" + g.buf.name).exists())
			Gdx.files.local("Saves/" + g.buf.name).delete();
		img.setSize(MainGame.w, MainGame.h);
		img.setPosition(-MainGame.w / 2, -MainGame.h / 2);
	}

	@Override
	public void init(StateBasedGame game) {
		g = (MainGame) game;
		img = new Sprite(((MainGame) game).container.getImage("end"));
		font = new BitmapFont(
				Gdx.files.internal("data/font.fnt"),
				new TextureRegion(((MainGame) game).container.getImage("font")),
				false);
	}

	@Override
	public void draw(SpriteBatch batch) {
		img.draw(batch);
		font.draw(batch,"You Score is " + String.valueOf(g.buf.Score),-font.getBounds("Your Score is "
				+ String.valueOf(g.buf.Score)).width / 2,(float) (-MainGame.h/4));
	}

	@Override
	public void update(StateBasedGame game) {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)||Gdx.input.isTouched()) {
			game.enterState(MainGame.MAINMENUSTATE);
		}
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
