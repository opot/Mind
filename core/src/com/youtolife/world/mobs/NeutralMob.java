package com.youtolife.world.mobs;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Mob;

public abstract class NeutralMob extends Mob {

	/**
	 */
	float StartingAngle;
	/**
	 */
	float FinalAngle;
	/**
	 */
	int direction = 1;
	/**
	 */
	Random rand;

	public NeutralMob(float angle, float worldAngle, ImageContainer container) {
		super(angle, worldAngle, container);
		StartingAngle = this.angle;
		rand = new Random();
		FinalAngle = StartingAngle + rand.nextInt(360);
	}

	@Override
	public void update(float worldAngle, float radius, Circle world,
			float player) {
		float delta = Gdx.graphics.getDeltaTime() * 1000;

		anim.update(delta);
		super.createRect(world, worldAngle);

		if ((new Random()).nextInt(200) == 15) {
			StartingAngle = angle;
			FinalAngle = StartingAngle + (rand.nextInt(240) + 60)
					* (rand.nextInt(4) - 1);
		} else {
			if (Math.sqrt((StartingAngle - FinalAngle)
					* (StartingAngle - FinalAngle)) > 5)
				if ((int) (angle / 10) != (int) (FinalAngle / 10))
					angle = angle + direction * 15 * delta / radius;
				else {
					revert();
				}
		}
	}

	public void revert() {
		float buf = FinalAngle;
		FinalAngle = StartingAngle;
		StartingAngle = buf;
		direction *= -1;
		if (direction>0)
			anim.setFlipped(true);
		else
			anim.setFlipped(false);
	}

}
