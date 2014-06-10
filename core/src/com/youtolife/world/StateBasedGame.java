package com.youtolife.world;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class StateBasedGame {

	/**
	 */
	private Vector<GameState> states = new Vector<GameState>();
	/**
	 */
	private int CurrentState = -1;
	
	public void init(){
		for (GameState state : states)
			state.init(this);
	}
	
	public void dispose(){
		for (GameState state : states)
				state.dispose();
	}
	
	public void render(SpriteBatch batch){
		for (GameState state : states)
			if (state.getID() == CurrentState)
				state.draw(batch);
	}
	
	public void update(){
		for (GameState state : states)
			if (state.getID() == CurrentState)
				state.update(this);
	}
	
	public void enterState(int StateId) {
		CurrentState = StateId;
		for (GameState state : states)
			if (state.getID() == CurrentState)
				state.enter(this);
	}
	
	public GameState getState(int StateId){
		for (GameState state : states)
			if (state.getID() == StateId)
				return state;
		return null;
	}
	
	public int getStateId() {
		return CurrentState;
	}
	
	public void addState(GameState state) {
		states.add(state);
	}
	
}
