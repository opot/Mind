package main.things;

import java.util.Random;
import java.util.Vector;

import main.ImageContainer;
import main.Item;
import main.functions;

import org.newdawn.slick.SlickException;

public class Spruce extends Tree {

	public Spruce(ImageContainer container) throws SlickException {
		super(container);
		this.id = 1;
		img = container.getImage("Things/spruce_"+String.valueOf(new Random().nextInt(2)+1));
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
		items.add(functions.createItem(5, 1, container, (int) this.angle));
	}

}
