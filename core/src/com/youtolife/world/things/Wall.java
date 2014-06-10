package com.youtolife.world.things;

import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.youtolife.world.AbstractAmmo;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.Functions;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.MainGame;
import com.youtolife.world.Mob;
import com.youtolife.world.StateBasedGame;
import com.youtolife.world.Thing;
import com.youtolife.world.mobs.NeutralMob;
import com.youtolife.world.states.GamePlayState;

public class Wall extends Thing {

	public Wall(ImageContainer container) {
		super(container);
		id = 5;
		hp = 200;
		Sprite img = new Sprite(container.getImage("things/stoneWall"));
		this.width = img.getWidth()/4;
		this.anim = new AnimatedSprite(0,0,img.getWidth(),img.getHeight(),img,0);
	}

	@Override
	public void drop(Vector<Item> items, ImageContainer container) {
		items.add(Functions.createItem(9, 2, container, (int) this.angle));
	}

	@Override
	public void update(GamePlayState game, StateBasedGame g) {
		Iterator<AbstractAmmo> ammo = game.playerAmmos.iterator();
		while(ammo.hasNext()){
			AbstractAmmo a = ammo.next();
			if(Intersector.overlapConvexPolygons(a.rect,this.rect)){
				a.drop(((MainGame)g).container, game.items);
				ammo.remove();
			}
		}
		ammo = game.mobAmmos.iterator();
		while(ammo.hasNext()){
			AbstractAmmo a = ammo.next();
			if(Intersector.overlapConvexPolygons(a.rect,this.rect)){
				a.drop(((MainGame)g).container, game.items);
				ammo.remove();
			}
		}
		
		Iterator<Mob> mobIt = game.mobs.iterator();
		while(mobIt.hasNext()){
			Mob mob = mobIt.next();
			if(mob instanceof NeutralMob)
				if(Intersector.overlapConvexPolygons(mob.rect,this.rect)){
					NeutralMob a = (NeutralMob)mob;
					a.revert();
				}
		}
		this.createRect(game.world, game.world_mask.getOriginY());
	}

}
