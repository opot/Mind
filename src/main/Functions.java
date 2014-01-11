package main;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import main.items.*;
import main.mobs.Nyan;
import main.mobs.Santa;
import main.mobs.blackman;
import main.things.Birch;
import main.things.Bush;
import main.things.Mountain;
import main.things.Nothing;
import main.things.Oak;
import main.things.Spruce;

public class Functions {

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
		if (id == 5)
			return new main.things.Wall(container);
		if (id == -1)
			return new Nothing(container);
		return null;
	}

	public static Item createItem(int id, int Stack, ImageContainer container,
			final Integer angle) {
		Item item = null;
		if (id == 1) 
			item = new Drug(container.getImage("items/drug"), Stack);
		if (id == 2) 
			item = new boom(container.getImage("items/boom"), Stack);
		if (id == 3) 
			item = new blackhole(container.getImage("items/blackhole"),
					Stack);
		if (id == 4) 
			item = new axe(container.getImage("items/axe"), 1);
		if (id == 5) 
			item = new Wood(container.getImage("items/wood"), Stack);
		if (id == 6) 
			item = new RainbowDust(
					container.getImage("items/rainbow_dust"), Stack);
		if (id == 7) 
			item = new BlackmanDust(
					container.getImage("items/blackstone_dust"), Stack);
		if (id == 8) 
			item = new Dynamite(container.getImage("items/TNT"), Stack);
		if (id == 9) 
			item = new stones(container.getImage("items/stones"), Stack);
		if(id == 10)
			item = new Wall(container.getImage("items/wall"), Stack);
		item.angle = angle;
		return item;
	}

	public static Mob createMobById(int id, float angle,
			ImageContainer container, Vector<AbstractAmmo> ammo) throws SlickException {
		Mob mob = null;
		if (id == 1)
			mob = new Nyan(angle, 0, container);
		if (id == 2)
			mob = new blackman(angle, 0, container, ammo);
		if (id == 3)
			mob = new Santa(angle, 0, container, ammo);
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

		for (int i = 2; i <= 10; i++) {
			recipe = getRecipe(i, container);
			boolean accepted = true;
			if (recipe != null)
				if (buf.size() == recipe.size()) {
					for (int j = 0; j <= buf.size() - 1; j++)
						if (buf.get(j).id != recipe.get(j).id||buf.get(j).Stack < recipe.get(j).Stack)
							accepted = false;
					if (accepted) {
						for (int j = 0; j <= buf.size() - 1; j++)
							buf.get(j).Stack-=recipe.get(j).Stack;
						for (int j = 0; j <= buf.size() - 1; j++)
							if (buf.get(j).Stack <= 0) {
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
				}else if(buf.size() == recipe.size()+1){
					accepted = true;
					recipe.add(createItem(i,1,container,0));
					for (int j = 0; j <= recipe.size() - 1; j++)
						for (int k = 1; k <= recipe.size() - 1; k++)
							if (recipe.get(k).id < recipe.get(k - 1).id) {
								Item b = recipe.get(k);
								recipe.set(k, recipe.get(k - 1));
								recipe.set(k - 1, b);
							}
					
								for (int j = 0; j <= buf.size() - 1; j++)
									if (buf.get(j).id != recipe.get(j).id||buf.get(j).Stack < recipe.get(j).Stack)
										accepted = false;
								if (accepted) {
									for (int j = 0; j <= buf.size() - 1; j++)
										buf.get(j).Stack-=recipe.get(j).Stack;
									for (int j = 0; j <= buf.size() - 1; j++)
										if (buf.get(j).Stack <= 0) {
											buf.remove(j);
											j--;
										}
									buf.add(createItem(i, 2, container, 0));
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
			recipe.add(createItem(5, 2, container, 0));
		if (id == 3)
			return null;
		if (id == 4) {
			recipe.add(createItem(5,2,container,0));
			recipe.add(createItem(9,1,container,0));
		}
		if (id == 5)
			return null;
		;
		if (id == 6)
			recipe.add(createItem(1, 1, container, 0));
		if (id == 7)
			recipe.add(createItem(3, 1, container, 0));
		if (id == 8) {
			recipe.add(createItem(6, 1, container, 0));
			recipe.add(createItem(7, 1, container, 0));
		}
		if(id == 9)
			return null;
		if(id == 10)
			recipe.add(createItem(9,4,container,0));
		return recipe;
	}

	public static void drawDebug(Graphics g, GamePlayState game){
		g.setColor(Color.red);
		g.drawString("Object = "+String.valueOf(game.objects.size()), 10, 30);
		g.drawString("Mobs = "+String.valueOf(game.mobs.size()), 10, 45);
		g.drawString("Areas = "+String.valueOf(game.areas.size()), 10, 60);
		g.drawString("PlayerBullets = "+String.valueOf(game.playerAmmos.size()), 10, 75);
		g.drawString("MobBullts = "+String.valueOf(game.mobAmmos.size()), 10, 90);
		g.drawString("Items = "+String.valueOf(game.items.size()), 10, 105);
		g.drawString("Rotation = "+String.valueOf(game.world_mask.getRotation()), 10, 120);
		g.drawString("armAngle = "+String.valueOf(game.player.armAngle), 10, 135);
		g.drawString("legAngle = "+String.valueOf(game.player.legAngle), 10, 150);
	}
	
}