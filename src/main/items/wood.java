package main.items;

import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import main.Ammo;
import main.AreaEffect;
import main.ImageContainer;
import main.Item;
import main.Player;

public class wood extends Item {

	public wood(Image img, int stack) {
		super(img, stack);
		id = 5;
	}

	@Override
	public void use(Vector<Ammo> shots, float angle, ImageContainer container,
			Player player, Circle world, Vector<AreaEffect> areas)
			throws SlickException {
		
	}

}
