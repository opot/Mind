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

public class boom extends Item {

	public boom(Image img, int stack) {
		super(img, stack);
		id = 2;
	}

	@Override
	public void use(Vector<AbstractAmmo> shots, float angle, ImageContainer container,
			Player player, Circle world, Vector<AreaEffect> areas)
			throws SlickException {
		player.armAdd = 50;
		shots.add(new main.ammos.boom((player.direction)==0?-1:1,player.angle,angle,container,world));
		Stack--;		
	}


}