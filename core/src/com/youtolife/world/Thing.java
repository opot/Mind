package com.youtolife.world;

import java.util.Vector;

import com.youtolife.world.states.GamePlayState;


public abstract class Thing extends GameObject {

	/**
	 */
	public int id;
	/**
	 */
	public int hp = 100;
	
	public Thing(ImageContainer container){
		width = 100;
		height = 200;
		float[] def_vert = {0,0,1,1,2,2};
		this.rect.setVertices(def_vert);
	}

	public void setAngle(float angle){
		this.angle = angle;
	}
	
	public abstract void drop(Vector<Item> items,ImageContainer container);
	public abstract void update(GamePlayState game, StateBasedGame gc);
	
}
