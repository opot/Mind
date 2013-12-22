package main.mobs;

import java.util.Random;

import main.ImageContainer;
import main.Mob;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public abstract class NeutralMob extends Mob {

	float StartingAngle;
	float FinalAngle;
	int direction = 1;
	Random rand;

	public NeutralMob(float angle, float worldAngle, ImageContainer container)
			throws SlickException {
		super(angle, worldAngle, container);
		StartingAngle = this.angle;
		rand = new Random();
		FinalAngle = StartingAngle + rand.nextInt(360);
	}

	@Override
	public void update(int delta, float worldAngle, float radius, Circle world,float player) {
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
					float buf = FinalAngle;
					FinalAngle = StartingAngle;
					StartingAngle = buf;
					direction *= -1;
					if (anim.getAnimStart() == 0) {
						anim.setAnimStart(anim.getAnimCount() / 2);
						anim.setAnimStop(anim.getAnimCount() - 1);
					} else {
						anim.setAnimStart(0);
						anim.setAnimStop(anim.getAnimCount() / 2 - 1);
					}
				}
		}
	}

}
