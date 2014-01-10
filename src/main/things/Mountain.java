package main.things;

import java.util.Vector;

import org.newdawn.slick.SlickException;

import main.ImageContainer;
import main.Item;
import main.Thing;
import main.Functions;

public class Mountain extends Thing {

	public Mountain(ImageContainer container) throws SlickException {
		super(container);
		id = 4;
		img = container.getImage("Things/mountaines");
		hp = 100;
	}

	@Override
	public void update(int delta) {
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
		items.add(Functions.createItem(9, 1, container, (int) this.angle));
	}

}
