package main.items;

import main.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Dynamite extends Item {

    public Dynamite(Image img, int stack) {
        super(img, stack);
        id = 8;
    }

    @Override
    public void use(ImageContainer container, GamePlayState game) throws SlickException {
    	//this.Stack--;
    	game.areas.add(new main.AreaEffects.Dinamite(container,game.player.angle));
    }
}
