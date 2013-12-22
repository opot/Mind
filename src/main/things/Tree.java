package main.things;

import org.newdawn.slick.SlickException;

import main.ImageContainer;
import main.Thing;

public abstract class Tree extends Thing {

	public int hp = 40;
	
	public Tree(ImageContainer container) throws SlickException {
		super(container);
	}
}
