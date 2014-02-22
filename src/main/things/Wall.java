package main.things;

import java.util.Iterator;
import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.AbstractAmmo;
import main.Functions;
import main.GamePlayState;
import main.ImageContainer;
import main.Item;
import main.Mob;
import main.Thing;
import main.Main;
import main.mobs.NeutralMob;

public class Wall extends Thing {

	public Wall(ImageContainer container) throws SlickException {
		super(container);
		id = 5;
		hp = 200;
		img = container.getImage("things/stoneWall");
		this.width = img.getWidth();
	}

	@Override
	public void drop(Vector<Item> items, ImageContainer container) {
		items.add(Functions.createItem(9, 2, container, (int) this.angle));
	}

	@Override
	public void update(int delta, GamePlayState game, StateBasedGame gc) {
		Iterator<AbstractAmmo> ammo = game.playerAmmos.iterator();
		while(ammo.hasNext()){
			AbstractAmmo a = ammo.next();
			if(a.rect.intersects(this.rect)){
				a.drop(((Main)gc).container, game.items);
				ammo.remove();
			}
		}
		ammo = game.mobAmmos.iterator();
		while(ammo.hasNext()){
			AbstractAmmo a = ammo.next();
			if(a.rect.intersects(this.rect)){
				a.drop(((Main)gc).container, game.items);
				ammo.remove();
			}
		}
		
		Iterator<Mob> mobIt = game.mobs.iterator();
		while(mobIt.hasNext()){
			Mob mob = mobIt.next();
			if(mob instanceof NeutralMob)
				if(mob.rect.intersects(this.rect)){
					NeutralMob a = (NeutralMob)mob;
					a.revert();
				}
		}
		
	}

}
