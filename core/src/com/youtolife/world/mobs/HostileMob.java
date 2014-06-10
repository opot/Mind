package com.youtolife.world.mobs;

import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.AbstractAmmo;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Mob;

public abstract class HostileMob extends Mob {

	/**
	 */
	float FinalAngle;
	/**
	 */
	int direction = 1;
	/**
	 */
	Random rand;
	/**
	 */
	float cooldown = 1000;
	/**
	 */
	Vector<AbstractAmmo> ammos;
	/**
	 */
	ImageContainer container;
	/**
	 */
	public AbstractAmmo use;

	public HostileMob(float angle, float worldAngle, ImageContainer container, Vector<AbstractAmmo> ammos){
		super(angle, worldAngle, container);
		rand = new Random();
		this.container = container;
		FinalAngle = angle;
		this.ammos = ammos;
	}

	@Override
	public void update(float worldAngle, float radius, Circle world,float player){
		
		float delta = Gdx.graphics.getDeltaTime()*1000;
		
		if(cooldown<1000)
			cooldown+=delta;
		
		if (angle > 360)
			angle -= 360;
		if (angle < -360)
			angle += 360;

		float angle_N = this.angle + worldAngle - player;

		if (angle_N > 360)
			angle_N -= 360;
		if (angle_N < -360)
			angle_N += 360;

		if (angle_N < 0)
			angle_N += 360;

		if (Math.abs(2 * angle_N * Math.PI * world.radius / 360) < 1000
				|| Math.abs(2 * (360 - angle_N) * Math.PI * world.radius / 360) < 1000) {

			if (angle_N > 180)
				FinalAngle = 360 - (float) (180 * 200 / (Math.PI * world.radius));
			else
				FinalAngle = (float) (180 * 200 / (Math.PI * world.radius));

			if (FinalAngle > 360)
				FinalAngle -= 360;
			if (FinalAngle < -360)
				FinalAngle += 360;

			if (angle_N < FinalAngle) {
				direction = 1;
				anim.setFlipped(true);
			} else {
				direction = -1;
				anim.setFlipped(false);
			}
			anim.setAnimActive(true);
			if ((int) (angle_N / 1800 * Math.PI * world.radius) != (int) (FinalAngle
					/ 1800 * Math.PI * world.radius))
				angle = angle + direction * 15 * delta / radius;
			else {
				anim.setAnimActive(false);
				direction *= -1;
				if (direction<0)
					anim.setFlipped(true);
				else
					anim.setFlipped(false);
				if(cooldown>=1000){
					cooldown-=1000;
					ammos.add(use.copy(direction,this.angle,0,container,world));
				}
			}
			anim.update(delta);
			super.createRect(world, worldAngle);
		}
	}
}
