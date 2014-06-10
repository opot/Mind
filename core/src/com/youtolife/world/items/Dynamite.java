package com.youtolife.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.states.GamePlayState;


public class Dynamite extends Item {

    public Dynamite(Texture img, int stack) {
        super(img, stack);
        id = 8;
    }

    @Override
    public void use(ImageContainer container, GamePlayState game) {
    	//this.Stack--;
    	game.player.armAdd = -50;
    	game.areas.add(new com.youtolife.world.AreaEffects.Dinamite(container,game));
    }
}
