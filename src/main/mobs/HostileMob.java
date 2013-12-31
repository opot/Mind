package main.mobs;

import java.util.Random;
import java.util.Vector;

import main.AbstractAmmo;
import main.ImageContainer;
import main.Mob;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public abstract class HostileMob extends Mob {

	float FinalAngle;
	int direction = 1;
	Random rand;
	float cooldown = 1000;
	Vector<AbstractAmmo> ammos;
	ImageContainer container;
	public AbstractAmmo use;

	public HostileMob(float angle, float worldAngle, ImageContainer container, Vector<AbstractAmmo> ammos)
			throws SlickException {
		super(angle, worldAngle, container);
		rand = new Random();
		this.container = container;
		FinalAngle = angle;
		this.ammos = ammos;
	}

	@Override
	public void update(int delta, float worldAngle, float radius, Circle world,float player) throws SlickException {
		
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
				anim.setAnimStart(0);
				anim.setAnimStop(anim.getAnimCount() / 2 - 1);
			} else {
				direction = -1;
				anim.setAnimStart(anim.getAnimCount() / 2);
				anim.setAnimStop(anim.getAnimCount() - 1);
			}
			anim.setAnimActive(true);
			if ((int) (angle_N / 1800 * Math.PI * world.radius) != (int) (FinalAngle
					/ 1800 * Math.PI * world.radius))
				angle = angle + direction * 15 * delta / radius;
			else {
				anim.setAnimActive(false);
				direction *= -1;
				if (anim.getAnimStart() == 0) {
					anim.setAnimStart(anim.getAnimCount() / 2);
					anim.setAnimStop(anim.getAnimCount() - 1);
				} else {
					anim.setAnimStart(0);
					anim.setAnimStop(anim.getAnimCount() / 2 - 1);
				}
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
