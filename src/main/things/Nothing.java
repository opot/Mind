package main.things;

import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.GamePlayState;
import main.ImageContainer;
import main.Item;
import main.Thing;

public class Nothing extends Thing {

	public Nothing(ImageContainer container) throws SlickException {
		super(container);
		id = -1;
		img = container.getImage("Things/nothing");
		hp  = 1000;
	}

	@Override
	public void update(int delta, GamePlayState game, StateBasedGame gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
	}

}
