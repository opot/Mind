package main;

import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public abstract class Item extends GameObject {

	public Integer Stack = 1;
	public Integer id;
	public int x,y;
	
	public Item(Image img, int stack) {
		this.img = img;
		Stack = stack;
		super.width = this.img.getWidth();
		super.height = this.img.getHeight();
		super.rect.addPoint(0, 0);
	}

	public float getAngle(){
		return super.angle;
	}
	
	public void update(Circle world, float rotation){
		super.createRect(world, rotation);
	}
	
	public abstract void use(Vector<Ammo> shots, float angle, ImageContainer container,Player player, Circle world,Vector<AreaEffect> areas) throws SlickException;
	
}
