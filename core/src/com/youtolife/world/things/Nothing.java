package com.youtolife.world.things;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.StateBasedGame;
import com.youtolife.world.Thing;
import com.youtolife.world.states.GamePlayState;

public class Nothing extends Thing {

	public Nothing(ImageContainer container) {
		super(container);
		id = -1;
		Sprite img = new Sprite(container.getImage("things/nothing"));
		this.width = img.getWidth();
		this.anim = new AnimatedSprite(0,0,img.getWidth(),img.getHeight(),img,0);
		hp  = 1000;
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
	}

	@Override
	public void update(GamePlayState game, StateBasedGame gc) {
		// TODO Auto-generated method stub
		
	}

}
