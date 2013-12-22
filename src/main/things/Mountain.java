package main.things;

import java.util.Vector;

import org.newdawn.slick.SlickException;

import main.ImageContainer;
import main.Item;
import main.Thing;
import main.functions;

public class Mountain extends Thing {

	public Mountain(ImageContainer container) throws SlickException {
		super(container);
		id = 4;
		img = container.getImage("Things/mountaines");
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
	}

}
