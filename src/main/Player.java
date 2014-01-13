package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {

	public Melee melee;
	public Image leg, arm, body;
	Image lLeg, rLeg;
	Image lBody, rBody;
	Image lArm, rArm;
	public Item[] inventory = new Item[25];
	public int hp = 100;
	public int current = 0;
	public int direction = 0;
	public int armAdd = 0;
	public int armAngle = 0;
	private int armSpeed = 1;
	public int legAngle = 0;
	private int legSpeed = 1;

	public Player(float width, float height, ImageContainer container)
			throws SlickException {
		rect.addPoint(0, 0);
		lBody = container.getImage("player/lBody");
		rBody = container.getImage("player/rBody");
		lArm = container.getImage("player/lArm");
		rArm = container.getImage("player/rArm");
		lLeg = container.getImage("player/lLeg");
		rLeg = container.getImage("player/rLeg");
		body = lBody;
		leg = lLeg;
		arm = lArm;
		this.width = 80;
		this.height = 200;
	}

	public void update(float delta, GamePlayState game, Input input, float rotation) {

		super.createRect(game.world, rotation);

		if (armAngle > 180)
			armSpeed = -1;
		if (armAngle < 0)
			armSpeed = 1;
		if (legAngle > 180)
			legSpeed = -1;
		if (legAngle < 0)
			legSpeed = 1;
		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)
				|| input.isControllerLeft(Input.ANY_CONTROLLER)) {
			angle -= 20 * delta / game.world.radius;
			
			armAngle += armSpeed * delta / 3;
			legAngle += legSpeed * delta / 1.75;
			if (melee == null) {
				leg = lLeg;
				body = lBody;
				arm = lArm;
				direction = 0;
			}
		} else if (input.isKeyDown(Input.KEY_RIGHT)
				|| input.isKeyDown(Input.KEY_D)
				|| input.isControllerRight(Input.ANY_CONTROLLER)) {
			angle += 20 * delta / game.world.radius;
			
			armAngle += armSpeed * delta / 3;
			legAngle += legSpeed * delta / 1.75;
			if (melee == null) {
				leg = rLeg;
				arm = rArm;
				body = rBody;
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

		for(Thing a:game.objects)
			if((a instanceof main.things.Wall))
				if(a.rect.intersects(this.rect)){
					if(a.rect.getCenterX()<this.rect.getCenterX())
						angle += 20 * delta / game.world.radius;
					else
						angle -= 20 * delta / game.world.radius;
					System.out.println(a.rect.getCenterX());
					break;
				}
		
		if (armAdd > 0)
			armAdd -= delta / 3;

		if (melee != null)
			if (melee.update(delta))
				melee = null;
	}

	@Override
	public void draw(Graphics g, Image world_mask, GamePlayState game, int width) {
		body.setCenterOfRotation(body.getWidth() / 2, 0);
		body.setRotation(world_mask.getRotation() + angle);

		leg.setCenterOfRotation(leg.getWidth() / 2, 0);
		leg.setRotation(world_mask.getRotation() + angle);
		leg.setCenterOfRotation(leg.getWidth() / 2, 5);
		leg.rotate((float) (Math.sin(Math.toRadians(legAngle - 90)) * 25));
		leg.draw(
				(float) (game.world.getCenterX() - leg.getWidth() / 2 + (game.world.radius)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (game.world.getCenterY() - leg.getHeight() - (game.world.radius)
						* Math.cos(Math.toRadians(body.getRotation()))));

		arm.setCenterOfRotation(arm.getWidth() / 2, 0);
		arm.setRotation(world_mask.getRotation() + angle);
		arm.setCenterOfRotation(arm.getWidth() / 2, 5);
		arm.rotate((float) (Math.sin(Math.toRadians(armAngle - 90)) * 30));
		arm.draw(
				(float) (game.world.getCenterX() - arm.getWidth() / 2 + (game.world.radius
						+ leg.getHeight() - 50)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (game.world.getCenterY() - arm.getHeight() - (game.world.radius
						+ leg.getHeight() - 50)
						* Math.cos(Math.toRadians(body.getRotation()))));

		body.draw(
				(float) (game.world.getCenterX() - body.getWidth() / 2 + (game.world.radius
						+ leg.getHeight() - 10)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (game.world.getCenterY() - body.getHeight() - (game.world.radius
						+ leg.getHeight() - 10)
						* Math.cos(Math.toRadians(body.getRotation()))));

		leg.setCenterOfRotation(leg.getWidth() / 2, 0);
		leg.setRotation(world_mask.getRotation() + angle);
		leg.setCenterOfRotation(leg.getWidth() / 2, 5);
		leg.rotate((float) (Math.cos(Math.toRadians(legAngle)) * 25));
		leg.draw(
				(float) (game.world.getCenterX() - leg.getWidth() / 2 + (game.world.radius)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (game.world.getCenterY() - leg.getHeight() - (game.world.radius)
						* Math.cos(Math.toRadians(body.getRotation()))));

		if (melee != null) {
			melee.draw(g, armAngle, game.world);

			arm.setCenterOfRotation(arm.getWidth() / 2, 0);
			arm.setRotation(world_mask.getRotation() + angle);
			arm.setCenterOfRotation(arm.getWidth() / 2, 5);
			arm.rotate(melee.angle);
			arm.draw(
					(float) (game.world.getCenterX() - arm.getWidth() / 2 + (game.world.radius
							+ leg.getHeight() - 50)
							* Math.sin(Math.toRadians(body.getRotation()))),
					(float) (game.world.getCenterY() - arm.getHeight() - (game.world.radius
							+ leg.getHeight() - 50)
							* Math.cos(Math.toRadians(body.getRotation()))));
		} else {
			arm.setCenterOfRotation(arm.getWidth() / 2, 0);
			arm.setRotation(world_mask.getRotation() + angle);
			arm.setCenterOfRotation(arm.getWidth() / 2, 5);
			arm.rotate((float) (Math.cos(Math.toRadians(armAngle)) * 25)
					- armAdd * (direction * 2 - 1));
			arm.draw(
					(float) (game.world.getCenterX() - arm.getWidth() / 2 + (game.world.radius
							+ leg.getHeight() - 50)
							* Math.sin(Math.toRadians(body.getRotation()))),
					(float) (game.world.getCenterY() - arm.getHeight() - (game.world.radius
							+ leg.getHeight() - 50)
							* Math.cos(Math.toRadians(body.getRotation()))));
		}
		if(game.debug)
			g.fill(this.rect);
	}
	
}