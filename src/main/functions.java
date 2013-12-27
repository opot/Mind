package main;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

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

	public static Thing createRandomThing(ImageContainer container)
			throws SlickException {
		Random r = new Random();
		int id = r.nextInt(5);
		return createThingById(container, id);
	}

	public static Thing createThingById(ImageContainer container, int id)
			throws SlickException {
		if (id == 0)
			return new Oak(container);
		if (id == 1)
			return new Spruce(container);
		if (id == 2)
			return new Birch(container);
		if (id == 3)
			return new Bush(container);
		if (id == 4)
			return new Mountain(container);
		if (id == -1)
			return new Nothing(container);
		return null;
	}

	public static Item createItem(int id, int Stack, ImageContainer container,
			Integer angle) {
		if (id == 1) {
			drug d = new drug(container.getImage("items/drug"), Stack);
			d.angle = angle;
			return d;
		}
		if (id == 2) {
			boom d = new boom(container.getImage("items/boom"), Stack);
			d.angle = angle;
			return d;
		}
		if (id == 3) {
			blackhole d = new blackhole(container.getImage("items/blackhole"),
					Stack);
			d.angle = angle;
			return d;
		}
		if (id == 4) {
			axe d = new axe(container.getImage("items/axe"), 1);
			d.angle = angle;
			return d;
		}
		if (id == 5) {
			wood d = new wood(container.getImage("items/wood"), Stack);
			d.angle = angle;
			return d;
		}
		if (id == 6) {
			RainbowDust d = new RainbowDust(
					container.getImage("items/rainbow_dust"), Stack);
			d.angle = angle;
			return d;
		}
		if (id == 7) {
			BlackmanDust d = new BlackmanDust(
					container.getImage("items/blackstone_dust"), Stack);
			d.angle = angle;
			return d;
		}
		if (id == 8) {
			Dynamite d = new Dynamite(container.getImage("items/TNT"), Stack);
			d.angle = angle;
			return d;
		}
		if (id == 9) {
			stones d = new stones(container.getImage("items/stones"), Stack);
			d.angle = angle;
			return d;
		}

		return null;
	}

	public static Mob createMobById(int id, float angle,
			ImageContainer container, Vector<Ammo> ammo) throws SlickException {
		Mob mob = null;
		if (id == 1)
			mob = new Nyan(angle, 0, container);
		if (id == 2)
			mob = new blackman(angle, 0, container, ammo);
		return mob;
	}

	@SuppressWarnings("unchecked")
	public static Vector<Item> craft(Vector<Item> workbench,
			ImageContainer container) {
		Vector<Item> buf = (Vector<Item>) (workbench.clone());
		Vector<Item> recipe = null;
		for (int j = 0; j <= buf.size() - 1; j++)
			for (int i = 1; i <= buf.size() - 1; i++)
				if (buf.get(i).id < buf.get(i - 1).id) {
					Item b = buf.get(i);
					buf.set(i, buf.get(i - 1));
					buf.set(i - 1, b);
				}

		for (int i = 2; i <= 8; i++) {
			recipe = getRecipe(i, container);
			boolean Accepted = true;
			if (recipe != null)
				if (buf.size() == recipe.size()) {
					for (int j = 0; j <= buf.size() - 1; j++)
						if (buf.get(j).id != recipe.get(j).id)
							Accepted = false;
					if (Accepted) {
						for (int j = 0; j <= buf.size() - 1; j++)
							buf.get(j).Stack--;
						for (int j = 0; j <= buf.size() - 1; j++)
							if (buf.get(j).Stack == 0) {
								buf.remove(j);
								j--;
							}
						buf.add(createItem(i, 1, container, 0));
						buf.get(buf.size() - 1).x = 176;
						buf.get(buf.size() - 1).y = 167;
						buf.get(buf.size() - 1).rect = new Polygon();
						buf.get(buf.size() - 1).rect.addPoint(186, 177);
						buf.get(buf.size() - 1).rect.addPoint(186 + 30, 177);
						buf.get(buf.size() - 1).rect.addPoint(186 + 30, 207);
						buf.get(buf.size() - 1).rect.addPoint(186, 207);
					}
				}
		}
		return buf;
	}

	public static Vector<Item> getRecipe(int id, ImageContainer container) {
		Vector<Item> recipe = new Vector<Item>();
		if (id == 2)
			recipe.add(createItem(5, 1, container, 0));
		if (id == 3)
			return null;
		if(id == 4){
			return null;//add after some time
		}
		if(id == 5)
			return null;;
		if(id == 6)
			recipe.add(createItem(1,1,container,0));
		if(id == 7)
			recipe.add(createItem(3,1,container,0));
		if(id == 8){
			recipe.add(createItem(6,1,container,0));
			recipe.add(createItem(7,1,container,0));
		}
		return recipe;
	}

}