package main.items;

import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import main.AbstractAmmo;
import main.AreaEffect;
import main.ImageContainer;
import main.Item;
import main.Player;

public class drug extends Item {

	public drug(Image img, int stack) {
		super(img, stack);
		id = 1;
	}

	@Override
	public void use(Vector<AbstractAmmo> shots, float angle, ImageContainer container,
			Player player, Circle world, Vector<AreaEffect> areas)
			throws SlickException {
		container.nyan.play();
		player.armAdd = 50;
		Stack--;		
	}


}
