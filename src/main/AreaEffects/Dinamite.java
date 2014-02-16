package main.AreaEffects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import main.AreaEffect;
import main.Functions;
import main.GamePlayState;
import main.ImageContainer;
import main.Main;
import main.Sprite;

public class Dinamite extends AreaEffect {

	int fireTime = 1000;
	int damage = 5;
	Image dinam;

	public Dinamite(ImageContainer container, float angle) {
		super(2200, angle);
		this.anim = new Sprite(0, 0, 150, 150, false,
				container.getImage("/area/burst"), angle);
		this.anim.setPreferedDelta(50);
		dinam = container.getImage("items/TNT");
		this.width = 100;
		this.height = 100;
	}

	@Override
	public void draw(Graphics g, Image world_mask, GamePlayState game, int width) {
		if (fireTime > 0)
			this.img = dinam;
		super.draw(g, world_mask, game, width);
		this.img = null;
	}

	@Override
	public boolean update(GamePlayState game, float delta) {
		if (fireTime > 0)
			fireTime -= delta;
		else
			anim.update(delta);

		return super.update(game, delta);
	}

	@Override
	public void interract(GamePlayState game, StateBasedGame main) throws SlickException {
		if (fireTime <= 0) {
			if (rect.intersects(game.player.rect))
				game.player.hp -= damage;
			for (int i = 0; i <= game.mobs.size() - 1; i++) {
				if (game.mobs.get(i).rect.intersects(rect)) {
					game.mobs.get(i).hp -= damage;
					if (game.mobs.get(i).hp < 1) {
						game.mobs.get(i).drop(game.items,
								((Main) main).container);
						game.mobs.remove(i);
						game.Score += 1;
						i--;
					}
				}
			}
			for (int i = 0; i <= game.objects.size() - 1; i++) {
				if (game.objects.get(i).rect.intersects(rect)) {
					game.objects.get(i).hp -= damage;
					if (game.objects.get(i).hp < 1) {
						float angle = game.objects.get(i).angle;
						game.objects.get(i).drop(game.items,
								((Main) main).container);
						game.objects.set(i, Functions.createThingById(((Main)main).container,-1));
						game.objects.get(i).angle = angle;
					}
				}
			}
		}
	}
}
