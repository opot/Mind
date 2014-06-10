package com.youtolife.world;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.states.GamePlayState;


public abstract class AbstractAmmo extends GameObject{

	/**
	 */
	private float AngularSpeed;
	/**
	 */
	private float falling = 50;
	/**
	 */
	public int damage = 10;

	public AbstractAmmo(float AngularSpeed, float angle, float worldAngle, Sprite shot,Circle world,float falling) {

		this.falling = falling;
		this.angle = angle;
		this.AngularSpeed = AngularSpeed;

		anim = new AnimatedSprite(0, 0, 50, 50, shot, 0);
		anim.setAnimStart(0);
		anim.setAnimStop(anim.getAnimCount()-1);
		rise  = anim.getHeigth();
		this.width = anim.getWidth();
		this.height = anim.getHeigth();
	}

	public boolean update(GamePlayState game) {
		super.createRect(game.world, game.world_mask.getRotation());
		float delta = Gdx.graphics.getDeltaTime()*1000;
		anim.update(delta);
		angle = angle + AngularSpeed * delta / game.world.radius;
		rise-=delta*falling;
		return rise<0;
	}

	public abstract AbstractAmmo copy(float AngularSpeed, float angle, float worldAngle, ImageContainer container,Circle world);
	public abstract void drop(ImageContainer container, Vector<Item> items);
	
}
