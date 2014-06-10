package com.youtolife.world.items;


import com.badlogic.gdx.graphics.Texture;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.melee.Axe;
import com.youtolife.world.states.GamePlayState;

public class axe extends Item {

	public axe(Texture img, int stack) {
		super(img, stack);
		id = 4;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game) {
		if(game.player.melee == null)
			game.player.melee = (new Axe(game.player,container));
	}
}
