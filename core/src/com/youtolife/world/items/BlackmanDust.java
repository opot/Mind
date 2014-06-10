package com.youtolife.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.states.GamePlayState;


public class BlackmanDust extends Item {


    public BlackmanDust(Texture img, int stack) {
        super(img, stack);
        id = 7;
    }

    @Override
    public void use(ImageContainer container, GamePlayState game) {

    }
}
