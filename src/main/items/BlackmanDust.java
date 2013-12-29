package main.items;

import main.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import java.util.Vector;

public class BlackmanDust extends Item {


    public BlackmanDust(Image img, int stack) {
        super(img, stack);
        id = 7;
    }

    @Override
    public void use(Vector<AbstractAmmo> shots, float angle, ImageContainer container, Player player, Circle world, Vector<AreaEffect> areas) throws SlickException {

    }
}
