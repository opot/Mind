package main.items;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.GamePlayState;
import main.ImageContainer;
import main.Item;
import main.melee.Axe;

public class axe extends Item {

	public axe(Image img, int stack) {
		super(img, stack);
		id = 4;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game)
			throws SlickException {
		if(game.player.melee==null)
			game.player.melee = new Axe(game.player,container);
	}
}
