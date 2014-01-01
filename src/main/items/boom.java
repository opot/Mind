package main.items;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.GamePlayState;
import main.ImageContainer;
import main.Item;

public class boom extends Item {

	public boom(Image img, int stack) {
		super(img, stack);
		id = 2;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game)
			throws SlickException {
		game.player.armAdd = 50;
		game.playerAmmos.add(new main.ammos.boom((game.player.direction)==0?-1:1,game.player.angle,angle,container,game.world));
		Stack--;		
	}


}