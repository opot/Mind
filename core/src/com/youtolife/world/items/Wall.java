package com.youtolife.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.youtolife.world.Functions;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.states.GamePlayState;
import com.youtolife.world.things.Nothing;

public class Wall extends Item {

	public Wall(Texture img, int stack) {
		super(img, stack);
		id = 10;
	}

	@Override
	public void use(ImageContainer container, GamePlayState game) {
		for (int i = 0; i < game.objects.size(); i++) 
			if(Intersector.overlapConvexPolygons(game.objects.get(i).rect,game.player.rect)&&(game.objects.get(i) instanceof Nothing)){
				float angle = game.objects.get(i).angle;
				game.objects.set(i, Functions.createThingById(container, 5));
				game.objects.get(i).angle = angle;
				//Stack--;
				game.player.armAdd = -50;
				return;
			}
				
	}

}
