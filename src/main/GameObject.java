package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;

public abstract class GameObject {

	public Image img = null;
	public Sprite anim = null;
	public float angle = 0;
	public float rise = 0;
	public float width = 0;
	public float height = 0;
	public Polygon rect = new Polygon();
	
	public void draw(Graphics g, int x, int y){
		if(img != null) {
			g.drawImage(img, x, y);
		}else{
			anim.setX(x);
			anim.setY(y);
			anim.draw(g);
		}
	}
	
	public void draw(Graphics g, Image world_mask, Circle world, int width) {
		if (img != null) {
			img.setCenterOfRotation(img.getWidth()/2, img.getHeight());
			img.setRotation(world_mask.getRotation() + angle);
			float x = (float) (world.getCenterX() - img.getWidth()/2 + (world.radius - 5)
					* Math.sin(Math.toRadians(img.getRotation())));
			if (x > -100 && x < width + 50)
				img.draw(x,
						(float) (world.getCenterY() - img.getHeight() - (world.radius - 5)
								* Math.cos(Math.toRadians(img.getRotation()))));
		}else{
			float angle = this.angle + world_mask.getRotation();
			anim.setRotation(angle);
			float x = (float) (world.getCenterX() - anim.getWidth() / 2 + (world.radius - 5 + rise)
					* Math.sin(Math.toRadians(angle)));
			if (x > -200 && x < width + 50) {
				anim.setX(x);
				anim.setY((float) (world.getCenterY() - anim.getHeigth() - (world.radius - 5 + rise)
						* Math.cos(Math.toRadians(angle))));
				anim.draw(g);
			}
		}
		//g.fill(rect);
	}

	public void createRect(Circle world, float rotation){
		Polygon rect = new Polygon();

		float angle = this.angle+rotation;
		rect.addPoint(
				world.getCenterX() + (height + world.radius+ rise)
						* (float) Math.sin(Math.toRadians(angle))
						- width/2
						* (float) Math.cos(Math.toRadians(angle)),
				world.getCenterY() - (height + world.radius + rise)
						* (float) Math.cos(Math.toRadians(angle))
						- width/2
						* (float) Math.sin(Math.toRadians(angle)));
		rect.addPoint(
				rect.getPoint(0)[0] + width
						* (float) Math.cos(Math.toRadians(angle)),
				rect.getPoint(0)[1] + width
						* (float) Math.sin(Math.toRadians(angle)));
		rect.addPoint(
				world.getCenterX() + (world.radius - 10)
						* (float) Math.sin(Math.toRadians(angle))
						+ width/2
						* (float) Math.cos(Math.toRadians(angle)),
				world.getCenterY() - (world.radius - 10)
						* (float) Math.cos(Math.toRadians(angle))
						+ width/2
						* (float) Math.sin(Math.toRadians(angle)));
		rect.addPoint(
				world.getCenterX() + (world.radius - 10)
						* (float) Math.sin(Math.toRadians(angle))
						- width/2
						* (float) Math.cos(Math.toRadians(angle)),
				world.getCenterY() - (world.radius - 10)
						* (float) Math.cos(Math.toRadians(angle))
						- width/2
						* (float) Math.sin(Math.toRadians(angle)));
		this.rect = rect;
	}
	
}
