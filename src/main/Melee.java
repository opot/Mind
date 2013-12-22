package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;

public abstract class Melee extends GameObject{

	public Player player;
	public float angle = 180;
	public Polygon rect = new Polygon();
	public int damage = 10;
	public boolean active = true;
	
	public Melee(Player player,ImageContainer container) {
		this.player = player;
	}

	public abstract void draw(Graphics g,float armAngle,Circle world);
	
	public abstract boolean update(float delta);
	
}
