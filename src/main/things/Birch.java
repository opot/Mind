package main.things;

import java.util.Vector;

import main.ImageContainer;
import main.Item;
import main.Functions;

import org.newdawn.slick.SlickException;

public class Birch extends Tree {

	public Birch(ImageContainer container) throws SlickException {
		super(container);
		id = 2;
		img = container.getImage("Things/Birch");
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
		items.add(Functions.createItem(5, 1, container, this.angle));
	}

}
