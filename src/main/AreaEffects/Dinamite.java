package main.AreaEffects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

import main.AreaEffect;
import main.GamePlayState;
import main.ImageContainer;
import main.Sprite;

public class Dinamite extends AreaEffect {

	int fireTime = 1000;
	Image dinam;

	public Dinamite(ImageContainer container, float angle) {
		super(2200, angle);
		this.anim = new Sprite(0, 0, 150, 150, false,
				container.getImage("/area/burst"), angle);
		this.anim.setPreferedDelta(50);
		dinam = container.getImage("items/TNT");
	}

	@Override
	public void draw(Graphics g, Image world_mask, Circle world, int width) {
		if (fireTime > 0)
			this.img = dinam;
		super.draw(g, world_mask, world, width);
		this.img = null;
	}

	@Override
	public boolean update(GamePlayState game, float delta) {
		if(fireTime>0)
			fireTime-=delta;
		else
			anim.update(delta);
		
		return super.update(game, delta);
	}

}
