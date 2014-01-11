package main.items;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.GamePlayState;
import main.ImageContainer;
import main.Item;

public class Wood extends Item {

	public Wood(Image img, int stack) {
		super(img, stack);
		id = 5;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game)
			throws SlickException {
		
	}

}
