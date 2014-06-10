package com.youtolife.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.states.GamePlayState;

public class Drug extends Item {

	public Drug(Texture img, int stack) {
		super(img, stack);
		id = 1;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game) {
		if ((game.player.hp + 20) > 100)
			game.player.hp = 100;
		else
			game.player.hp = (game.player.hp + 20);
		game.player.armAdd = -50;
		Stack--;
	}

}
