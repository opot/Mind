package com.youtolife.world.mobs;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.Functions;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;

public class Nyan extends NeutralMob {

	public Nyan(float angle, float worldAngle,ImageContainer container) {
		super(angle, worldAngle,container);
		id = 1;
		anim = new AnimatedSprite(0, 0, 200, 100, new Sprite(new TextureRegion(container.getImage("nyan"),400,100)), 0);
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
			items.add(Functions.createItem(1, count, container, (int)this.angle));
	}

}
