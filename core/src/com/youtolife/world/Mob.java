package com.youtolife.world;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.states.GamePlayState;


public abstract class Mob extends GameObject {

	/**
	 */
	Item[] inventory = null;
	/**
	 */
	public int hp = 100;
	/**
	 */
	Sprite hp_mask = null;
	/**
	 */
	public int id = -1;
	
	public Mob(float angle, float worldAngle, ImageContainer container) {
		this.angle = angle - worldAngle;
		inventory = new Item[64];
		hp_mask = new Sprite(container.getImage("GUI/hp"));
		
	}
	
	public void draw(SpriteBatch batch, Sprite world_mask, GamePlayState game) {
		super.draw(batch, world_mask, game);
		float angle = this.angle + world_mask.getRotation()+90;
		hp_mask.setOrigin(hp_mask.getWidth()/2, 0);
		hp_mask.setRotation(world_mask.getRotation() + angle);
		float x = (float) ((game.world.radius + anim.getHeigth()) * Math.cos(Math.toRadians(angle))) - hp;
		if (x > -500 && x < 500) {
			float y = (float) ((game.world.radius + anim.getHeigth()) * Math.sin(Math.toRadians(angle)))+ game.world.y / 2;
			hp_mask.setPosition(x, y);
			hp_mask.setSize(hp*2, hp_mask.getHeight());
			hp_mask.setRotation(angle-90);
			hp_mask.draw(batch);
		}
	}
	
	abstract public void update(float worldAngle, float radius, Circle world, float player);
	abstract public void drop(Vector<Item> items, ImageContainer container);

}
