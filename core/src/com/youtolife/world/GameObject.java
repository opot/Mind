package com.youtolife.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.youtolife.world.states.GamePlayState;

public abstract class GameObject {

	/**
	 */
	public AnimatedSprite anim = null;
	/**
	 */
	public float angle = 0;
	/**
	 */
	public float rise = 0;
	/**
	 */
	public float width = 0;
	/**
	 */
	public float height = 0;

	/**
	 */
	float[] vert = { 0, 0, 1, 1, 2, 2 };
	/**
	 */
	public Polygon rect = new Polygon(vert);

	public void draw(SpriteBatch batch, float x, float y) {
		anim.setX(x);
		anim.setY(y);
		anim.draw(batch);
	}

	public void draw(SpriteBatch batch, Sprite world_mask, GamePlayState game) {
		float angle = this.angle + world_mask.getRotation() + 90;
		float x = (float) ((game.world.radius + rise - 10) * Math.cos(Math
				.toRadians(angle))) - anim.getWidth() / 2;
		if (x > -500 && x < 500) {
			float y = (float) ((game.world.radius + rise - 10) * Math.sin(Math
					.toRadians(angle))) + game.world.y / 2;
			anim.setRotation(angle - 90);
			anim.setX(x);
			anim.setY(y);
			anim.draw(batch);
		}
		// if(game.debug){
		// g.setColor(Color.white);
		// if(rect.intersects(game.player.rect))
		// g.setColor(Color.green);
		// g.fill(rect);
		// }
	}

	public void createRect(Circle world, float rotation) {
		Polygon rect = new Polygon();

		float angle = this.angle + rotation - 180;
		vert = new float[8];
		vert[0] = world.x + world.radius / 2 + (height + world.radius + rise)
				* (float) Math.sin(Math.toRadians(angle)) - width / 2
				* (float) Math.cos(Math.toRadians(angle));
		vert[1] = world.y + world.radius / 2 - (height + world.radius + rise)
				* (float) Math.cos(Math.toRadians(angle)) - width / 2
				* (float) Math.sin(Math.toRadians(angle));
		vert[2] = vert[0] + width * (float) Math.cos(Math.toRadians(angle));
		vert[3] = vert[1] + width * (float) Math.sin(Math.toRadians(angle));
		vert[4] = world.x + world.radius / 2 + (world.radius - 10)
				* (float) Math.sin(Math.toRadians(angle)) + width / 2
				* (float) Math.cos(Math.toRadians(angle));
		vert[5] = world.y + world.radius / 2 - (world.radius - 10)
				* (float) Math.cos(Math.toRadians(angle)) + width / 2
				* (float) Math.sin(Math.toRadians(angle));
		vert[6] = world.x + world.radius / 2 + (world.radius - 10)
				* (float) Math.sin(Math.toRadians(angle)) - width / 2
				* (float) Math.cos(Math.toRadians(angle));
		vert[7] = world.y + world.radius / 2 - (world.radius - 10)
				* (float) Math.cos(Math.toRadians(angle)) - width / 2
				* (float) Math.sin(Math.toRadians(angle));
		rect.setVertices(vert);
		this.rect = rect;
	}
}
