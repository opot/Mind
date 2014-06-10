package com.youtolife.world.things;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.Functions;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.StateBasedGame;
import com.youtolife.world.Thing;
import com.youtolife.world.states.GamePlayState;

public class Mountain extends Thing {

	public Mountain(ImageContainer container) {
		super(container);
		id = 4;
		Sprite img = new Sprite(container.getImage("things/mountaines"));
		this.width = img.getWidth();
		this.anim = new AnimatedSprite(0,0,img.getWidth(),img.getHeight(),img,0);
	}


	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
		items.add(Functions.createItem(9, 1, container, this.angle));
	}

	@Override
	public void update(GamePlayState game, StateBasedGame gc) {
		
	}

}
