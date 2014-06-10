package com.youtolife.world.AreaEffects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.AreaEffect;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.StateBasedGame;
import com.youtolife.world.states.GamePlayState;

public class Dinamite extends AreaEffect {

	/**
	 */
	int fireTime = 1000;
	/**
	 */
	int damage = 5;
	/**
	 */
	AnimatedSprite stative;
	/**
	 */
	AnimatedSprite dynamic;
	
	public Dinamite(ImageContainer container, GamePlayState game) {
		super(2200, game.player.angle, game);
		dynamic = new AnimatedSprite(0, 0, 150, 150,
				new Sprite(container.getImage("/area/burst")), angle);
		dynamic.setPreferedDelta(50);
		Sprite img = new Sprite(container.getImage("items/TNT"));
		stative = new AnimatedSprite(0,0,64,64,img,game.player.angle);
		stative.setAnimActive(false);
		this.anim = stative;
		this.width = 100;
		this.height = 100;
	}

	@Override
	public boolean update(GamePlayState game) {
		float delta = Gdx.graphics.getDeltaTime()*1000;
		if (fireTime > 0)
			fireTime -= delta;
		else{
			anim = dynamic;
			dynamic.update(delta);
			if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Vibrator))
				Gdx.input.vibrate(100);
		}
		return super.update(game);
	}

	@Override
	public void interract(GamePlayState game, StateBasedGame main) {
		if (fireTime <= 0) {
			if (Intersector.overlapConvexPolygons(rect,game.player.rect))
				game.player.hp = (game.player.hp - damage);
				
			for (int i = 0; i <= game.mobs.size() - 1; i++) {
				if (Intersector.overlapConvexPolygons(game.mobs.get(i).rect,rect)) {
					game.mobs.get(i).hp -= damage;
					if (game.mobs.get(i).hp < 1) {
						game.mobs.get(i).drop(game.items,
								game.game.container);
						game.mobs.remove(i);
						game.Score += 1;
						i--;
					}
				}
			}
			for (int i = 0; i <= game.objects.size() - 1; i++) {
				if (Intersector.overlapConvexPolygons(game.objects.get(i).rect,rect))
					game.objects.get(i).hp -= damage;
			}
		}
	}
}
