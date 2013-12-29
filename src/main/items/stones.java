package main.items;

import main.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import java.util.Vector;

public class stones extends Item {

    public stones(Image img, int stack) {
        super(img, stack);
        id = 9;
    }

    @Override
    public void use(Vector<Ammo> shots, float angle, ImageContainer container, Player player, Circle world, Vector<AreaEffect> areas) throws SlickException {

    }
}
