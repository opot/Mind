package main.things;

import java.util.Vector;

import org.newdawn.slick.SlickException;

import main.Functions;
import main.ImageContainer;
import main.Item;
import main.Thing;

public class Wall extends Thing {

	public Wall(ImageContainer container) throws SlickException {
		super(container);
		id = 5;
		hp = 200;
		img = container.getImage("things/stoneWall");
	}

	@Override
	public void drop(Vector<Item> items, ImageContainer container) {
		items.add(Functions.createItem(9, 2, container, (int) this.angle));
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub

	}

}
