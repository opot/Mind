package main.mobs;

import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import main.AbstractAmmo;
import main.ImageContainer;
import main.Item;
import main.Sprite;
import main.Functions;
import main.ammos.Present;
import main.ammos.blackhole;

public class blackman extends HostileMob{

	public blackman(float angle, float worldAngle, ImageContainer container, Vector<AbstractAmmo> ammos)
			throws SlickException {
		super(angle, worldAngle, container, ammos);
		id = 2;
		anim = new Sprite(0,0,90,120,false,container.getImage("blackman"),angle);
		anim.setAnimStart(0);
		anim.setAnimStop(anim.getAnimCount()/2-1);
		anim.setPreferedDelta(100);
		anim.setAnimActive(true);
		super.width = anim.getWidth();
		super.height = anim.getHeigth();
		this.use = new blackhole(0,0,0,container,new Circle(0,0,1));
	}

	@Override
	public void drop(Vector<Item> items, ImageContainer container) {
		items.add(Functions.createItem(3, 1, container, (int) angle));
	}

}
