package main.mobs;

import java.util.Vector;

import main.ImageContainer;
import main.Item;
import main.Sprite;
import main.functions;

import org.newdawn.slick.SlickException;

public class Nyan extends NeutralMob {

	public Nyan(float angle, float worldAngle,ImageContainer container) throws SlickException {
		super(angle, worldAngle,container);
		id = 1;
		anim = new Sprite(0, 0, 200, 100, false,
				container.getImage("nyan"), 0);
		anim.setAnimStart(0);
		anim.setAnimStop(1);
		anim.setAnimActive(true);
		super.width = anim.getWidth();
		super.height = anim.getHeigth();
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
		int count = rand.nextInt(3);
		if(count>1)
			items.add(functions.createItem(1, count, container, (int)this.angle));
	}

}
