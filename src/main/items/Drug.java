package main.items;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.GamePlayState;
import main.ImageContainer;
import main.Item;

public class Drug extends Item {

	public Drug(Image img, int stack) {
		super(img, stack);
		id = 1;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game)
			throws SlickException {
		container.nyan.play();
		if((game.player.hp+=20)>100)
			game.player.hp = 100;
		game.player.armAdd = 50;
		Stack--;		
	}


}
