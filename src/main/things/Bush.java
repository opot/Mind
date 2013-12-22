package main.things;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.SlickException;

import main.ImageContainer;
import main.Item;
import main.Thing;
import main.functions;


public class Bush extends Thing {

	private int SubId;
	
	public Bush(ImageContainer container) throws SlickException {
		super(container);
		this.id = 3;
		SubId = new Random().nextInt(3)+1;
		img = container.getImage("Things/bush_"+String.valueOf(SubId));
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
	}

}
