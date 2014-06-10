package com.youtolife.world.mobs;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.AbstractAmmo;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.ammos.Present;

public class Santa extends HostileMob{

	public Santa(float angle, float worldAngle, ImageContainer container, Vector<AbstractAmmo> ammos) {
		super(angle, worldAngle, container, ammos);
		id = 3;
		anim = new AnimatedSprite(0,0,133,124,new Sprite(new TextureRegion(container.getImage("santa"),2141,124)),angle);
		anim.setAnimStart(0);
		anim.setAnimStop(anim.getAnimCount()-1);
		anim.setPreferedDelta(50);
		anim.setAnimActive(true);
		super.width = anim.getWidth();
		super.height = anim.getHeigth();
		this.use = new Present(0,0,0,container,new Circle(0,0,1));
	}

	@Override
	public void drop(Vector<Item> items, ImageContainer container) {
		//items.add(Functions.createItem(3, 1, container, (int) angle));
	}

}
