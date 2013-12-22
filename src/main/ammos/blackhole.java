package main.ammos;

import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import main.Ammo;
import main.ImageContainer;
import main.Item;

public class blackhole extends Ammo {

	public blackhole(float AngularSpeed, float angle, float worldAngle, ImageContainer container,
			Circle world) throws SlickException {
		super(AngularSpeed*21, angle, worldAngle, container.getImage("weapons/range/sphere"), world, 0);
		anim.setPreferedDelta(300);
		damage = 5;
	}

	@Override
	public void drop(ImageContainer container, Vector<Item> items) {}

}