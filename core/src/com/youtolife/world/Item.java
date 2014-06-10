package com.youtolife.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.states.GamePlayState;


public abstract class Item extends GameObject {

	/**
	 */
	public Integer Stack = 1;
	/**
	 */
	public Integer id;
	/**
	 */
	public int x;
	/**
	 */
	public int y;
	
	public Item(Texture img, int stack) {
		this.anim = new AnimatedSprite(0,0,64,64,new Sprite(img),0);
		this.anim.setAnimActive(false);
		Stack = stack;
		super.width = 50;
		super.height = 50;
	}

	public float getAngle(){
		return super.angle;
	}
	
	public void update(Circle world, float rotation){
		super.createRect(world, rotation);
	}
	
	public abstract void use(ImageContainer container, GamePlayState game);
	
}
