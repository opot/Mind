package main;

import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public abstract class AbstractAmmo extends GameObject{

	private float AngularSpeed;
	private float falling = 50;
	public int damage = 10;

	public AbstractAmmo(float AngularSpeed, float angle, float worldAngle, Image shot,Circle world,float falling)
			throws SlickException {

		this.falling = falling;
		this.angle = angle;
		this.AngularSpeed = AngularSpeed;

		anim = new Sprite(0, 0, 50, 50, false, shot, 0);
		anim.setAnimStart(0);
		anim.setAnimStop(anim.getAnimCount()-1);
		rise  = anim.getHeigth();
		this.width = anim.getWidth();
		this.height = anim.getHeigth();
		rect.addPoint(0, 0);
	}

	public boolean update(final float delta,final  float worldAngle, final Circle world) {
		super.createRect(world, worldAngle);
		anim.update(delta);
		angle = angle + AngularSpeed * delta / world.radius;
		rise-=delta*falling;
		return rise<0;
	}

	public abstract void drop(ImageContainer container, Vector<Item> items);
	
}
