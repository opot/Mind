package main.items;

import main.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class stones extends Item {

    public stones(Image img, int stack) {
        super(img, stack);
        id = 9;
    }

    @Override
    public void use(ImageContainer container, GamePlayState game) throws SlickException {

    }
}
