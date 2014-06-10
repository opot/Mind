package com.youtolife.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameState {

	/**
	 */
	private int StateId = -1;
	/**
	 */
	public MainGame game;
	
	public GameState(int StateId, MainGame game){
		this.StateId = StateId;
		this.game = game;
	}
	public int getID(){
		return StateId;
	}
	
	public abstract void draw(SpriteBatch batch);
	public abstract void update(StateBasedGame game);
	public abstract void init(StateBasedGame game);
	public abstract void dispose();
	public abstract void enter(StateBasedGame game);
	public abstract void pause();
	public abstract void resume();
}
