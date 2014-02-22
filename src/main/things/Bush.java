package main.things;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.GamePlayState;
import main.ImageContainer;
import main.Item;
import main.Thing;


public class Bush extends Thing {

	private int SubId;
	
	public Bush(ImageContainer container) throws SlickException {
		super(container);
		this.id = 3;
		SubId = new Random().nextInt(3)+1;
		img = container.getImage("Things/bush_"+String.valueOf(SubId));
		hp = 30;
	}

	@Override
	public void update(int delta, GamePlayState game, StateBasedGame gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
	}

}
