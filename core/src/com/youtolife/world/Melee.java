package com.youtolife.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.youtolife.world.states.GamePlayState;

public abstract class Melee{

	/**
	 */
	public Player player;
	/**
	 */
	public float angle = 180;
	/**
	 */
	public Polygon rect = new Polygon();
	/**
	 */
	public int damage = 10;
	/**
	 */
	public boolean active = true;
	/**
	 */
	public Sprite img;
	
	public Melee(Player player,ImageContainer container,int damage) {
		this.player = player;
		float[] vert = {0,0,1,1,2,2};
		this.rect.setVertices(vert);
		this.damage = damage;
	}

	public abstract void draw(SpriteBatch batch,float armAngle,Circle world);
	
	public abstract boolean update();

	public abstract void createRect(Sprite player);
	
	public abstract void interract(GamePlayState gamePlayState);
	
}
