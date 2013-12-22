package main;

import java.util.Vector;

import org.newdawn.slick.SlickException;

public abstract class Thing extends GameObject {

	public int id;
	
	public Thing(ImageContainer container) throws SlickException {
		super.rect.addPoint(0, 0);
		width = 100;
		height = 200;
	}

	public void setAngle(float angle){
		this.angle = angle;
	}
	
	public abstract void drop(Vector<Item> items,ImageContainer container);
	public abstract void update(int delta);
	
}
