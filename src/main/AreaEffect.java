package main;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AreaEffect extends GameObject {

	int TTL;
	
	public AreaEffect(int TTL, float angle){
		this.TTL = TTL;
		this.angle = angle;
	}
	
	public  boolean update(GamePlayState game, float delta){
		boolean result = false;
		super.createRect(game.world, game.world_mask.getRotation());
		TTL-=delta;
		if(TTL<=0)
			result = true;
		return result;
	}
	
	public abstract void interract(GamePlayState game, StateBasedGame main) throws SlickException;
	
}
