package com.youtolife.world.items;


import com.badlogic.gdx.graphics.Texture;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.states.GamePlayState;

public class blackhole extends Item {

	public blackhole(Texture img, int stack) {
		super(img, stack);
		id = 3;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game){
		game.player.armAdd = -50;
		game.playerAmmos.add(new com.youtolife.world.ammos.blackhole((game.player.direction)==0?-1:1,game.player.angle,angle,container,game.world));
		Stack--;		
	}
}