package com.youtolife.world;

import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtolife.world.items.BlackmanDust;
import com.youtolife.world.items.Drug;
import com.youtolife.world.items.Dynamite;
import com.youtolife.world.items.RainbowDust;
import com.youtolife.world.items.Wood;
import com.youtolife.world.items.axe;
import com.youtolife.world.items.blackhole;
import com.youtolife.world.items.boom;
import com.youtolife.world.items.stones;
import com.youtolife.world.mobs.Nyan;
import com.youtolife.world.mobs.Santa;
import com.youtolife.world.mobs.blackman;
import com.youtolife.world.states.GamePlayState;
import com.youtolife.world.things.Birch;
import com.youtolife.world.things.Bush;
import com.youtolife.world.things.Mountain;
import com.youtolife.world.things.Nothing;
import com.youtolife.world.things.Oak;
import com.youtolife.world.things.Spruce;
import com.youtolife.world.things.Wall;

public class Functions {

	public static Thing createRandomThing(ImageContainer container){
		Random r = new Random();
		int id = r.nextInt(5);
		return createThingById(container, id);
	}

	public static Thing createThingById(ImageContainer container, int id) {
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
			return new Wall(container);
		if (id == -1)
			return new Nothing(container);
		return null;
	}

	public static Item createItem(int id, int Stack, ImageContainer container,
			final float angle) {
		Item item = null;
		if (id == 1)item = new Drug(container.getImage("items/drug"), Stack);
		if (id == 2)item = new boom(container.getImage("items/boom"), Stack);
		if (id == 3)item = new blackhole(container.getImage("items/blackhole"),Stack);
		if (id == 4)item = new axe(container.getImage("items/axe"), 1);
		if (id == 5)item = new Wood(container.getImage("items/wood"), Stack);
		if (id == 6)item = new RainbowDust(container.getImage("items/rainbow_dust"), Stack);
		if (id == 7)item = new BlackmanDust(container.getImage("items/blackstone_dust"), Stack);
		if (id == 8)item = new Dynamite(container.getImage("items/TNT"), Stack);
		if (id == 9)item = new stones(container.getImage("items/stones"), Stack);
		if(id == 10)item = new com.youtolife.world.items.Wall(container.getImage("items/wall"), Stack);
		item.anim.setSize(50, 50);
		item.angle = angle;
		return item;
	}

	public static Mob createMobById(int id, float angle,
			ImageContainer container, Vector<AbstractAmmo> ammo){
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
						//buf.add(createItem(i, 1, container, 0));
						//buf.get(buf.size() - 1).x = 176;
						//buf.get(buf.size() - 1).y = 167;
						//buf.get(buf.size() - 1).rect = new Polygon();
						//buf.get(buf.size() - 1).rect.addPoint(186, 177);
						//buf.get(buf.size() - 1).rect.addPoint(186 + 30, 177);
						//buf.get(buf.size() - 1).rect.addPoint(186 + 30, 207);
						//buf.get(buf.size() - 1).rect.addPoint(186, 207);
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
									//buf.add(createItem(i, 2, container, 0));
									//buf.get(buf.size() - 1).x = 176;
									//buf.get(buf.size() - 1).y = 167;
									//buf.get(buf.size() - 1).rect = new Polygon();
									//buf.get(buf.size() - 1).rect.addPoint(186, 177);
									//buf.get(buf.size() - 1).rect.addPoint(186 + 30, 177);
									//buf.get(buf.size() - 1).rect.addPoint(186 + 30, 207);
									//buf.get(buf.size() - 1).rect.addPoint(186, 207);
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

	public static void drawDebug(SpriteBatch batch, GamePlayState game, BitmapFont font){
		font.draw(batch,"Object = "+String.valueOf(game.objects.size()), -MainGame.w/2+10, MainGame.h/2-30);
		font.draw(batch,"Mobs = "+String.valueOf(game.mobs.size()), -MainGame.w/2+10, MainGame.h/2-45);
		font.draw(batch,"Areas = "+String.valueOf(game.areas.size()), -MainGame.w/2+10, MainGame.h/2-60);
		font.draw(batch,"PlayerBullets = "+String.valueOf(game.playerAmmos.size()), -MainGame.w/2+10,MainGame.h/2- 75);
		font.draw(batch,"MobBullts = "+String.valueOf(game.mobAmmos.size()), -MainGame.w/2+10, MainGame.h/2-90);
		font.draw(batch,"Items = "+String.valueOf(game.items.size()), -MainGame.w/2+10, MainGame.h/2- 105);
		font.draw(batch,"Rotation = "+String.valueOf(game.world_mask.getRotation()),-MainGame.w/2+ 10,MainGame.h/2- 120);
		font.draw(batch,"armAngle = "+String.valueOf(game.player.armAngle), -MainGame.w/2+10,MainGame.h/2- 135);
		font.draw(batch,"legAngle = "+String.valueOf(game.player.legAngle), -MainGame.w/2+10, MainGame.h/2-150);
		font.draw(batch,"armAdd = "+String.valueOf(game.player.armAdd), -MainGame.w/2+10, MainGame.h/2-165);
		font.draw(batch,"length of world = " + String.valueOf((int)(Math.PI*game.world.radius/50)), -MainGame.w/2+10, MainGame.h/2-180);
	}
	
}