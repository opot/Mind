package com.youtolife.world.melee;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Melee;
import com.youtolife.world.Player;
import com.youtolife.world.Thing;
import com.youtolife.world.states.GamePlayState;
import com.youtolife.world.things.Tree;

public class Axe extends Melee {

	/**
	 */
	float x;
	/**
	 */
	float y;

	public Axe(Player player, ImageContainer container) {
		super(player, container, 60);
		TextureRegion buf = new TextureRegion(
				container.getImage("weapons/melee/axe"), 30, 64);
		img = new Sprite(buf);
		angle = 180;
		img.setOrigin(img.getWidth() / 2, img.getHeight());
	}

	@Override
	public void draw(SpriteBatch batch, float armAngle, Circle world) {

		img.setRotation(-angle - 90);

		float dx = (float) ((player.arm.getHeight() - 10) * Math.cos(Math
				.toRadians(angle - 90)));
		float dy = (float) ((player.arm.getHeight() - 10) * Math.sin(Math
				.toRadians(angle - 90)));

		img.setPosition(-img.getWidth() / 2 - dx, player.leg.getHeight() / 2
				+ dy);
		img.draw(batch);
	}

	@Override
	public boolean update() {
		float delta = Gdx.graphics.getDeltaTime() * 20;
		if (angle < 90)
			return true;
		angle -= delta;
		return false;
	}

	@Override
	public void interract(GamePlayState game) {
		if (active) {
			Iterator<Thing> object = game.objects.iterator();
			while (object.hasNext()) {
				Thing a = object.next();
				if (a instanceof Tree) {
					if (Intersector.overlapConvexPolygons(a.rect, this.rect)) {
						active = false;
						a.hp -= damage * 3;
						if (a.hp <= 0)
							a.drop(game.items, game.container);
						return;
					}
				}

			}
		}
	}

	@Override
	public void createRect(Sprite player) {
		rect = new Polygon();
		float vert[] = new float[6];
		if (this.player.direction!=0) {
			vert[0] = player.getX();
			vert[1] = player.getY();
			vert[2] = player.getX() - 100;
			vert[3] = player.getY();
			vert[4] = player.getX() - 100;
			vert[5] = player.getY() - 40;
		} else {
			vert[0] = player.getX();
			vert[1] = player.getY();
			vert[2] = player.getX() + 100;
			vert[3] = player.getY();
			vert[4] = player.getX() + 100;
			vert[5] = player.getY() - 40;
		}
		rect.setVertices(vert);
	}
}