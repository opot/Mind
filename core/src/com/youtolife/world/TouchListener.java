package com.youtolife.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.youtolife.world.states.MainMenuState;
import com.youtolife.world.states.MainMenuState.touchAction;

public class TouchListener implements GestureListener {

	/**
	 */
	MainGame game;

	public TouchListener(MainGame mainGame) {
		this.game = mainGame;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (Gdx.input.isPeripheralAvailable(Input.Peripheral.MultitouchScreen))
			if (game.getStateId() == MainGame.MAINMENUSTATE) {
				MainMenuState menu = (MainMenuState) game
						.getState(MainGame.MAINMENUSTATE);
				if (x > Gdx.graphics.getWidth() * 0.9
						&& y < Gdx.graphics.getHeight()
						&& y > Gdx.graphics.getHeight() * 0.9f) {
					menu.touchAction(touchAction.Back, game);
				}else
					menu.touchAction(touchAction.Enter, game);
			}else{
				
			}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (Gdx.input.isPeripheralAvailable(Input.Peripheral.MultitouchScreen)) {
			if (game.getStateId() == MainGame.MAINMENUSTATE) {
				MainMenuState menu = (MainMenuState) game
						.getState(MainGame.MAINMENUSTATE);
				if (Math.abs(velocityX) > Math.abs(velocityY))
					if (velocityX > 0) {
						menu.touchAction(MainMenuState.touchAction.Right, game);
					} else {
						menu.touchAction(MainMenuState.touchAction.Left, game);
					}
			}
		}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
