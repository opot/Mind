package com.youtolife.world.items;


import com.badlogic.gdx.graphics.Texture;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.states.GamePlayState;

public class Wood extends Item {

	public Wood(Texture img, int stack) {
		super(img, stack);
		id = 5;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game) {
		
	}

}
