package com.youtolife.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.states.GamePlayState;


public class stones extends Item {

    public stones(Texture img, int stack) {
        super(img, stack);
        id = 9;
    }

    @Override
    public void use(ImageContainer container, GamePlayState game) {

    }
}
