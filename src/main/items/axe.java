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
import main.melee.Axe;

public class axe extends Item {

	public axe(Image img, int stack) {
		super(img, stack);
		id = 4;
	}

	@Override
	public void use(Vector<AbstractAmmo> shots, float angle, ImageContainer container,
			Player player, Circle world, Vector<AreaEffect> areas)
			throws SlickException {
		if(player.melee==null)
			player.melee = new Axe(player,container);
	}
}
