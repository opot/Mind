package com.youtolife.world;

import com.badlogic.gdx.Gdx;
import com.youtolife.world.states.GamePlayState;


public abstract class AreaEffect extends GameObject {

	/**
	 */
	int TTL;
	
	public AreaEffect(int TTL, float angle, GamePlayState game){
		this.TTL = TTL;
		this.angle = angle;
		createRect(game.world, game.world_mask.getRotation());
	}
	
	public  boolean update(GamePlayState game){
		boolean result = false;
		super.createRect(game.world, game.world_mask.getRotation());
		TTL-=Gdx.graphics.getDeltaTime()*1000;
		if(TTL<=0)
			result = true;
		return result;
	}
	
	public abstract void interract(GamePlayState game, StateBasedGame main);
	
}
