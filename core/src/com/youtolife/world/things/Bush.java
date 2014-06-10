package com.youtolife.world.things;

import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.StateBasedGame;
import com.youtolife.world.Thing;
import com.youtolife.world.states.GamePlayState;

public class Bush extends Thing {

	/**
	 */
	private int SubId;
	
	public Bush(ImageContainer container){
		super(container);
		this.id = 3;
		SubId = new Random().nextInt(3)+1;
		Sprite img = new Sprite(container.getImage("things/bush_"+String.valueOf(SubId)));
		this.width = img.getWidth();
		this.anim = new AnimatedSprite(0,0,img.getWidth(),img.getHeight(),img,0);
		hp = 30;
	}

	@Override
	public void drop(Vector<Item> items,ImageContainer container) {
	}

	@Override
	public void update(GamePlayState game, StateBasedGame gc) {
		// TODO Auto-generated method stub
		
	}

}
