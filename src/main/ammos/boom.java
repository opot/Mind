package main.ammos;

import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import main.AbstractAmmo;
import main.ImageContainer;
import main.Item;
import main.Functions;

public class boom extends AbstractAmmo {

	public boom(float AngularSpeed, float angle, float worldAngle, ImageContainer container,
			Circle world) throws SlickException {
		super(AngularSpeed*40, angle, worldAngle, AngularSpeed==1?container.getImage("weapons/range/shotR"):container.getImage("weapons/range/shotL"), world, 0.025f);
		anim.setPreferedDelta(200);
		damage = 10;
	}

	@Override
	public void drop(ImageContainer container, Vector<Item> items) {
		items.add(Functions.createItem(2, 1, container, (int) angle));
	}

	@Override
	public AbstractAmmo copy(float AngularSpeed, float angle, float worldAngle,
			ImageContainer container, Circle world) throws SlickException {
		return new boom(AngularSpeed,angle,worldAngle,container,world);
	}

}
