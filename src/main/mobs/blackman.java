package main.mobs;

import java.util.Vector;

import org.newdawn.slick.SlickException;

import main.AbstractAmmo;
import main.ImageContainer;
import main.Item;
import main.Sprite;
import main.Functions;

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
	}

	@Override
	public void drop(Vector<Item> items, ImageContainer container) {
		items.add(Functions.createItem(3, 1, container, (int) angle));
	}

}
