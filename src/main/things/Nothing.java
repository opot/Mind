package main.things;

import java.util.Vector;

import org.newdawn.slick.SlickException;

import main.ImageContainer;
import main.Item;
import main.Thing;

public class Nothing extends Thing {

	public Nothing(ImageContainer container) throws SlickException {
		super(container);
		id = -1;
		img = container.getImage("Things/nothing");
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
	}

}
