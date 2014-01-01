package main.items;

import main.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class BlackmanDust extends Item {


    public BlackmanDust(Image img, int stack) {
        super(img, stack);
        id = 7;
    }

    @Override
    public void use(ImageContainer container, GamePlayState game) throws SlickException {

    }
}
