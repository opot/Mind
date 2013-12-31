package main.ammos;

import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import main.AbstractAmmo;
import main.ImageContainer;
import main.Item;

public class Present extends AbstractAmmo {

	public Present(float AngularSpeed, float angle, float worldAngle, ImageContainer container,
			Circle world) throws SlickException {
		super(AngularSpeed*21, angle, worldAngle, container.getImage("weapons/range/present"), world, 0);
		anim.setPreferedDelta(100);
		damage = 5;
	}

	@Override
	public void drop(ImageContainer container, Vector<Item> items) {}

	@Override
	public AbstractAmmo copy(float AngularSpeed, float angle, float worldAngle,
			ImageContainer container, Circle world) throws SlickException {
		return new Present(AngularSpeed,angle,worldAngle,container,world);
	}
}
