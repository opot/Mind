package main.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.Functions;
import main.GamePlayState;
import main.ImageContainer;
import main.Item;
import main.things.Nothing;

public class Wall extends Item {

	public Wall(Image img, int stack) {
		super(img, stack);
		id = 10;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game)
			throws SlickException {
		for (int i = 0; i < game.objects.size(); i++) 
			if(game.objects.get(i).rect.intersects(game.player.rect)&&(game.objects.get(i) instanceof Nothing)){
				game.objects.set(i, Functions.createThingById(container, 5));
				Stack--;
			}
				
	}

}
