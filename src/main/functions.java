package main;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.SlickException;

import main.items.*;
import main.mobs.Nyan;
import main.mobs.blackman;
import main.things.Birch;
import main.things.Bush;
import main.things.Mountain;
import main.things.Nothing;
import main.things.Oak;
import main.things.Spruce;

public class functions {

public static float getAngle(float x1, float y1, float x2, float y2){
	
	float angle;
	double vectX = x1-x2;
	double vectY = y2-y1;
	double hyp = Math.sqrt(vectX*vectX+vectY*vectY);
	
	if(vectX<0)
		vectX*=-1;
	if(vectY<0)
		vectY*=-1;
	
	if(x1>=x2)
		if(y1<y2)
			angle = (float) Math.acos(vectX/hyp);
		else
			angle = (float) (Math.acos(vectY/hyp)+Math.PI/2*3);
	else
		if(y1<y2)
			angle = (float) (Math.acos(vectY/hyp)+Math.PI/2);
		else
			angle = (float) (Math.acos(vectX/hyp)+Math.PI);
	return angle;
}

	public static Thing createRandomThing(ImageContainer container) throws SlickException{
		Random r = new Random();
		int id = r.nextInt(5);
		return createThingById(container,id);
	}
	
	public static Thing createThingById(ImageContainer container,int id) throws SlickException{
		if(id==0)
			return new Oak(container);
		if(id==1)
			return new Spruce(container);
		if(id==2)
			return new Birch(container);
		if(id==3)
			return new Bush(container);
		if(id==4)
			return new Mountain(container);
		if(id == -1)
			return new Nothing(container);
		return null;
	}

	public static Item createItem(int id, int Stack, ImageContainer container,Integer angle){
		if(id == 1){
			drug d = new drug(container.getImage("items/drug"),Stack);
			d.angle = angle;
			return d;
		}
		if(id == 2){
			boom d = new boom(container.getImage("items/boom"),Stack);
			d.angle = angle;
			return d;
		}
		if(id == 3){
			blackhole d = new blackhole(container.getImage("items/blackhole"),Stack);
			d.angle = angle;
			return d;
		}
		if(id == 4){
			axe d = new axe(container.getImage("items/axe"),1);
            d.angle = angle;
			return d;
		}
        if(id == 5){
            wood d = new wood(container.getImage("items/wood"),Stack);
            d.angle = angle;
            return d;
        }
        if(id == 6){
            RainbowDust d = new RainbowDust(container.getImage("items/rainbow_dust"),Stack);
            d.angle = angle;
            return d;
        }
        if(id == 7){
            BlackmanDust d = new BlackmanDust(container.getImage("items/blackstone_dust"),Stack);
            d.angle = angle;
            return d;
        }
        if(id == 8){
            Dynamite d = new Dynamite(container.getImage("items/TNT"),Stack);
            d.angle = angle;
            return d;
        }

		return null;
	}
	
	public static Mob createMobById(int id,float angle,ImageContainer container, Vector<Ammo> ammo) throws SlickException{
		Mob mob = null;
		if(id == 1)
			mob = new Nyan(angle,0,	container);
		if(id == 2)
			mob = new blackman(angle,0,	container, ammo);
		return mob;
	}
	
}