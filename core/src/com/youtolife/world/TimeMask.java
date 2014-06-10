package com.youtolife.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TimeMask {

	/**
	 */
	public float CurrentTime = 12f;
	/**
	 */
	Sprite day_mask;
	/**
	 */
	Sprite night_mask;
	/**
	 */
	public float time_speed = 1f;

	public TimeMask(ImageContainer container){
		day_mask = new Sprite(container.getImage("time/day"));
		night_mask = new Sprite(container.getImage("time/night"));
		day_mask.setSize(MainGame.w, MainGame.h);
		night_mask.setSize(MainGame.w, MainGame.h);
		day_mask.setPosition(-MainGame.w/2, -MainGame.h/2);
		night_mask.setPosition(-MainGame.w/2, -MainGame.h/2);
		day_mask.setSize(MainGame.w, MainGame.h);
		night_mask.setSize(MainGame.w, MainGame.h);
	}

	public void render(SpriteBatch batch) {
		night_mask.draw(batch);
		day_mask.draw(batch);
	}

	public int update() {

		float delta = Gdx.graphics.getDeltaTime();
		
		int result = 0;

		CurrentTime += (delta / time_speed);
		if (CurrentTime > 24) {
			CurrentTime = 0.001f;
			result = 1;
		}
		if (CurrentTime >= 12f && CurrentTime < 22f)
			day_mask.setColor(1f, 1f, 1f, (float) (1 - (CurrentTime - 12) / 10));
		else if (CurrentTime >= 2f && CurrentTime < 12f)
			day_mask.setColor(1f, 1f, 1f, (float) (CurrentTime - 2) / 10);
		else if ((CurrentTime >= 22f && CurrentTime < 24f)
				|| (CurrentTime >= 0f && CurrentTime < 2f))
			day_mask.setColor(1f, 1f, 1f, 0);
		return result;
	}

}
