package com.youtolife.world.ammos;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.AbstractAmmo;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;

public class blackhole extends AbstractAmmo {

	public blackhole(float AngularSpeed, float angle, float worldAngle,
			ImageContainer container, Circle world) {
		super(AngularSpeed * 21, angle, worldAngle, new Sprite(new TextureRegion(
				container.getImage("sphere"),300,50)), world, 0);
		anim.setPreferedDelta(300);
		damage = 5;
	}

	@Override
	public AbstractAmmo copy(float AngularSpeed, float angle, float worldAngle,
			ImageContainer container, Circle world) {
		return new blackhole(AngularSpeed, angle, worldAngle, container, world);
	}

	@Override
	public void drop(ImageContainer container, Vector<Item> items) {

	}

}
