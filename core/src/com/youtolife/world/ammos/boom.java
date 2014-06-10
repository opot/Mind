package com.youtolife.world.ammos;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.AbstractAmmo;
import com.youtolife.world.Functions;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;

public class boom extends AbstractAmmo {

	public boom(float AngularSpeed, float angle, float worldAngle,
			ImageContainer container, Circle world) {
		super(AngularSpeed * 40, angle, worldAngle, new Sprite(new TextureRegion(container.getImage("weapons/range/boom"))), world,0.025f);
		anim.setPreferedDelta(200);
		if(AngularSpeed!=1)
			anim.setFlipped(true);
		damage = 10;
	}

	@Override
	public void drop(ImageContainer container, Vector<Item> items) {
		items.add(Functions.createItem(2, 1, container, (int) angle));
	}

	@Override
	public AbstractAmmo copy(float AngularSpeed, float angle, float worldAngle,
			ImageContainer container, Circle world) {
		return new boom(AngularSpeed, angle, worldAngle, container, world);
	}

}
