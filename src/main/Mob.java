package main;

import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public abstract class Mob extends GameObject {

	Item[] inventory = null;
	public int hp = 100;
	Image hp_mask = null;
	public int id = -1;
	
	public Mob(float angle, float worldAngle, ImageContainer container) {
		this.angle = angle - worldAngle;
		inventory = new Item[64];
		hp_mask = container.getImage("hp");
		rect.addPoint(0, 0);
	}
	
	public void draw(Graphics g, Image world_mask, Circle world, int width) {
		super.draw(g, world_mask, world, width);
		hp_mask.setCenterOfRotation(hp_mask.getWidth()/2, hp_mask.getHeight());
		hp_mask.setRotation(world_mask.getRotation() + angle);
		float x = (float) (world.getCenterX() - hp_mask.getWidth()/2 + (world.radius + anim.getHeigth())
				* Math.sin(Math.toRadians(hp_mask.getRotation())) - anim.getWidth()/2* Math.cos(Math.toRadians(hp_mask.getRotation())));
		if (x > -100 && x < width + 50)
			hp_mask.draw(x,
					(float) (world.getCenterY() - hp_mask.getHeight() - (world.radius + anim.getHeigth())
							* Math.cos(Math.toRadians(hp_mask.getRotation()))- anim.getWidth()/2* Math.sin(Math.toRadians(hp_mask.getRotation()))),hp,25);
	}
	
	abstract public void update(int delta, float worldAngle, float radius, Circle world, float player) throws SlickException;
	abstract public void drop(Vector<Item> items, ImageContainer container);

}
