package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

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
	private int armAngle = 0;
	private int armSpeed = 1;
	private int legAngle = 0;
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
		this.width = 20;
		this.height = 200;
	}

	public void update(float delta, Circle world, Input input, float rotation) {

		super.createRect(world, rotation);

		if (armAngle > 180 || armAngle < 0)
			armSpeed *= -1;
		if (legAngle > 180 || legAngle < 0)
			legSpeed *= -1;
		if (input.isKeyDown(Input.KEY_LEFT)) {
			angle-=20*delta/world.radius;
			armAngle += armSpeed * delta / 3;
			legAngle += legSpeed * delta / 1.75;
			if (melee == null) {
				leg = lLeg;
				body = lBody;
				arm = lArm;
				direction = 0;
			}
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			angle+=20*delta/world.radius;
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
		
		if(armAdd>0)
			armAdd -= delta / 3;
		
		if (melee != null)
			if (melee.update(delta))
				melee = null;
	}

	@Override
	public void draw(Graphics g, Image world_mask, Circle world, int width) {
		body.setCenterOfRotation(body.getWidth() / 2, 0);
		body.setRotation(world_mask.getRotation() + angle);

		leg.setCenterOfRotation(leg.getWidth() / 2, 0);
		leg.setRotation(world_mask.getRotation() + angle);
		leg.setCenterOfRotation(leg.getWidth() / 2, 5);
		leg.rotate((float) (Math.sin(Math.toRadians(legAngle - 90)) * 25));
		leg.draw(
				(float) (world.getCenterX() - leg.getWidth() / 2 + (world.radius)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (world.getCenterY() - leg.getHeight() - (world.radius)
						* Math.cos(Math.toRadians(body.getRotation()))));

		arm.setCenterOfRotation(arm.getWidth() / 2, 0);
		arm.setRotation(world_mask.getRotation() + angle);
		arm.setCenterOfRotation(arm.getWidth() / 2, 5);
		arm.rotate((float) (Math.sin(Math.toRadians(armAngle - 90)) * 30));
		arm.draw(
				(float) (world.getCenterX() - arm.getWidth() / 2 + (world.radius
						+ leg.getHeight() - 50)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (world.getCenterY() - arm.getHeight() - (world.radius
						+ leg.getHeight() - 50)
						* Math.cos(Math.toRadians(body.getRotation()))));

		body.draw(
				(float) (world.getCenterX() - body.getWidth() / 2 + (world.radius
						+ leg.getHeight() - 10)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (world.getCenterY() - body.getHeight() - (world.radius
						+ leg.getHeight() - 10)
						* Math.cos(Math.toRadians(body.getRotation()))));

		leg.setCenterOfRotation(leg.getWidth() / 2, 0);
		leg.setRotation(world_mask.getRotation() + angle);
		leg.setCenterOfRotation(leg.getWidth() / 2, 5);
		leg.rotate((float) (Math.cos(Math.toRadians(legAngle)) * 25));
		leg.draw(
				(float) (world.getCenterX() - leg.getWidth() / 2 + (world.radius)
						* Math.sin(Math.toRadians(body.getRotation()))),
				(float) (world.getCenterY() - leg.getHeight() - (world.radius)
						* Math.cos(Math.toRadians(body.getRotation()))));

		if (melee != null) {
			melee.draw(g, armAngle, world);

			arm.setCenterOfRotation(arm.getWidth() / 2, 0);
			arm.setRotation(world_mask.getRotation() + angle);
			arm.setCenterOfRotation(arm.getWidth() / 2, 5);
			arm.rotate(melee.angle);
			arm.draw(
					(float) (world.getCenterX() - arm.getWidth() / 2 + (world.radius
							+ leg.getHeight() - 50)
							* Math.sin(Math.toRadians(body.getRotation()))),
					(float) (world.getCenterY() - arm.getHeight() - (world.radius
							+ leg.getHeight() - 50)
							* Math.cos(Math.toRadians(body.getRotation()))));
		} else {
			arm.setCenterOfRotation(arm.getWidth() / 2, 0);
			arm.setRotation(world_mask.getRotation() + angle);
			arm.setCenterOfRotation(arm.getWidth() / 2, 5);
			arm.rotate((float) (Math.cos(Math.toRadians(armAngle)) * 25)-armAdd*(direction*2-1));
			arm.draw(
					(float) (world.getCenterX() - arm.getWidth() / 2 + (world.radius
							+ leg.getHeight() - 50)
							* Math.sin(Math.toRadians(body.getRotation()))),
					(float) (world.getCenterY() - arm.getHeight() - (world.radius
							+ leg.getHeight() - 50)
							* Math.cos(Math.toRadians(body.getRotation()))));
		}
	}
}