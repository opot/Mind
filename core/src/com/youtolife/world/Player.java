package com.youtolife.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.youtolife.world.states.GamePlayState;
import com.youtolife.world.states.GamePlayState.ButtonTouch;
import com.youtolife.world.things.Wall;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;

public class Player extends GameObject {

	Texture tbodyl, tarml, tlegl;
	public Sprite body, leg, arm;
	FrameBuffer fbo = null;
	TextureRegion tr = null;
	Sprite player = null;
	
	public Item[] inventory = new Item[25];
	public int hp = 100;
	public int current = 0;
	public int direction = 0;
	public float armAdd = 0;
	
	float armAngle = 0;
	float armSpeed = 1;
	float legAngle = 0;
	float legSpeed = 1;

	public Melee melee;

	public Player(ImageContainer container, GamePlayState game) {
		tbodyl = container.getImage("player/lBody");
		tarml = container.getImage("player/lArm");
		tlegl = container.getImage("player/lLeg");

		arm = new Sprite(new TextureRegion(tarml, 26, 79));
		leg = new Sprite(new TextureRegion(tlegl, 36, 84));
		body = new Sprite(new TextureRegion(tbodyl, 60, 80));

		body.setPosition(-body.getWidth() / 2, leg.getHeight() - 10);
		leg.setPosition(-leg.getWidth() / 2, 0);
		arm.setPosition(-arm.getWidth() / 2, leg.getHeight() / 2);

		arm.setOrigin(arm.getWidth() / 2, arm.getHeight() - 5);
		leg.setOrigin(leg.getWidth() / 2, leg.getHeight() - 5);

		fbo = new FrameBuffer(Pixmap.Format.RGBA8888, 800, 600, true);
		tr = new TextureRegion(fbo.getColorBufferTexture(), 300, 300, 200, 220);
		player = new Sprite(tr);
		player.setOrigin(player.getWidth() / 2, 0);
		player.setPosition(0, 0);
		player.flip(true, true);

		this.width = 80;
		this.height = 200;
	}

	public void draw(SpriteBatch batch, Sprite world_mask, GamePlayState game) {
		batch.end();

		fbo.begin();

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		leg.setRotation(-(float) (Math.sin(Math.toRadians(legAngle - 90)) * 25));
		leg.draw(batch);
		arm.setRotation(-(float) (Math.sin(Math.toRadians(armAngle - 90)) * 30));
		arm.draw(batch);

		body.draw(batch);

		leg.setRotation(-(float) (Math.cos(Math.toRadians(legAngle)) * 25));
		leg.draw(batch);

		if (melee != null) {
			melee.draw(batch, armAngle, game.world);
			arm.setRotation(-melee.angle);
		} else {
			arm.setRotation(-(float) (Math.cos(Math.toRadians(armAngle)) * 30 - armAdd));
		}

		arm.draw(batch);

		batch.end();
		fbo.end();

		batch.begin();

		float angle = this.angle + game.world_mask.getRotation();

		float x = (float) ((game.world.radius - 10) * Math.cos(Math
				.toRadians(angle + 90))) - player.getWidth() / 2;
		float y = (float) ((game.world.radius - 10) * Math.sin(Math
				.toRadians(angle + 90))) + game.world.y / 2;

		player.setRotation(angle);
		player.setPosition(x, y);

		player.draw(batch);
	}

	public void update(GamePlayState game, ButtonTouch bt[], float rotation) {
		float delta = Gdx.graphics.getDeltaTime() * 1000;

		createRect(game.world, rotation);

		Input input = Gdx.input;

		if (armAngle > 180)
			armSpeed = -1;
		if (armAngle < 0)
			armSpeed = 1;
		if (legAngle > 180)
			legSpeed = -1;
		if (legAngle < 0)
			legSpeed = 1;

		if (input.isKeyPressed(Input.Keys.RIGHT)
				|| input.isKeyPressed(Input.Keys.D)
				|| bt[0] == ButtonTouch.Right || bt[1] == ButtonTouch.Right) {
			angle -= 20 * delta / game.world.radius;

			armAngle += armSpeed * delta / 3f;
			legAngle += (legSpeed * delta / 1.75f);
			if (melee == null && direction == 1) {
				player.flip(true, false);
				direction = 0;
			}
		} else if (input.isKeyPressed(Input.Keys.LEFT)
				|| input.isKeyPressed(Input.Keys.A)
				|| bt[0] == ButtonTouch.Left || bt[1] == ButtonTouch.Left) {
			angle += 20 * delta / game.world.radius;

			armAngle += armSpeed * delta / 3f;
			legAngle += (legSpeed * delta / 1.75f);
			if (melee == null && direction == 0) {
				player.flip(true, false);
				direction = 1;
			}
		} else {
			if (legAngle > 130)
				legAngle -= delta / 3;
			if (legAngle < 50)
				legAngle += delta / 3;
		}

		if (angle > 360)
			angle -= 360;
		if (angle < -360)
			angle += 360;
		if (angle < 0)
			angle += 360;

		for (Thing a : game.objects) {
			if ((a instanceof Wall))
				if (Intersector.overlapConvexPolygons(a.rect, this.rect)) {
					if (a.rect.getVertices()[0] < this.rect.getVertices()[0])
						angle += 20 * delta / game.world.radius;
					else
						angle -= 20 * delta / game.world.radius;
					break;
				}

			if (armAdd < 0)
				armAdd += delta / 50;

			if (melee != null){
				melee.createRect(player);
				if (melee.update())
					melee = null;
			}
				
		}
	}
}