package main.melee;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;

import main.ImageContainer;
import main.Melee;
import main.Player;

public class Axe extends Melee {

	float x, y;

	public Axe(Player player, ImageContainer container) {
		super(player, container);
		Image buf = container.getImage("weapons/melee/axe");
		if (player.direction == 0) {
			img = buf.getSubImage(buf.getWidth() / 2, 0, buf.getWidth() / 2,
					buf.getHeight());
			angle = 180;
		} else {
			img = buf.getSubImage(0, 0, buf.getWidth() / 2, buf.getHeight());
			angle = -180;
		}
		rect.addPoint(0, 0);
		img.setCenterOfRotation(img.getWidth() / 2, 0);
	}

	@Override
	public void draw(Graphics g, float armAngle, Circle world) {

		float bangle = super.player.body.getRotation() + angle;

		float sy = (float) ((player.arm.getHeight() - 20) * Math.cos(Math
				.toRadians((180 - bangle))));
		float sx = (float) ((player.arm.getHeight() - 20) * Math.sin(Math
				.toRadians((180 - bangle))));

		img.setRotation(bangle);
		y = (float) (world.getCenterY() - img.getHeight() - (world.radius
				+ player.leg.getHeight() - 60)
				* Math.cos(Math.toRadians(player.body.getRotation())))
				- sy;
		x = (float) (world.getCenterX() - img.getWidth() / 2 - 8 + (world.radius
				+ player.leg.getHeight() - 60)
				* Math.sin(Math.toRadians(player.body.getRotation())))
				- sx;
		img.draw(x, y);
	}

	@Override
	public boolean update(float delta) {
		if (angle > 0) {
			if (angle <= 80)
				return true;
			angle -= delta / 3;
		}
		if (angle < 0) {
			if (angle >= -80)
				return true;
			angle += delta / 3;
		}

		if (active) {
			rect = new Polygon();
			rect.addPoint(x + 20, y);
			rect.addPoint(x + 50, y);
			rect.addPoint(x + 5, y + 30);
		}

		return false;
	}

}