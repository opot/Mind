package main.items;

import main.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class RainbowDust extends Item {


    public RainbowDust(Image img, int stack) {
        super(img, stack);
        id = 6;
    }

    @Override
    public void use(ImageContainer container, GamePlayState game) throws SlickException {

    }
}
