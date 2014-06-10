package com.youtolife.world;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSprite {

	/**
	 */
	private float x;
	/**
	 */
	private float y;
	/**
	 */
	private float angle = 0;
	/**
	 */
	private float width;
	/**
	 */
	private float height;
	/**
	 */
	private Sprite[] img = null;

	/**
	 */
	private boolean AnimActive = true;
	/**
	 */
	private int AnimStart = 0;
	/**
	 */
	private int AnimStop = 0;
	/**
	 */
	private int AnimNow = 0;
	/**
	 */
	private int AnimCount = 0;
	/**
	 */
	private float AnimDelta = 0;
	/**
	 */
	private float PreferedDelta = 200;
	/**
	 */
	private boolean isFlipped = false;
	
	public void draw(SpriteBatch batch) {
		img[AnimNow].setPosition(x, y);
		img[AnimNow].draw(batch);
	}

	private void CutImage(Sprite src) {
		for (int i = 0; i <= src.getWidth() / width - 1; i++)
			img[i] = new Sprite(new TextureRegion(src, (int) (i * width), 0,
					(int) width, (int) height));
	}

	public AnimatedSprite(float x, float y, float width, float height,
			Sprite img, float angle) {
		this.img = new Sprite[(int) (img.getWidth() / width)];
		this.width = width;
		this.height = height;
		AnimStart = 0;
		AnimStop = this.img.length - 1;
		AnimCount = (int) (img.getWidth() / width);
		this.x = x;
		this.y = y;
		CutImage(img);
		setRotation(angle);
		if (this.img.length == 1)
			AnimActive = false;
	}

	public void update(float delta) {
		if (AnimActive) {
			AnimDelta += delta;
			if (AnimDelta >= PreferedDelta) {
				AnimDelta -= PreferedDelta;
				AnimNow++;
				if (AnimNow > AnimStop)
					AnimNow = AnimStart;
			}
		}
	}

	/**
	 * @return
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param  x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @param  y
	 */
	public void setY(float y) {
		this.y = y;
	}

	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setRotation(float angle) {
		for (int i = 0; i <= img.length - 1; i++) {
			img[i].setOrigin(img[i].getWidth() / 2, 0);
			img[i].setRotation(angle);
		}
	}

	/**
	 * @return
	 */
	public boolean isAnimActive() {
		return AnimActive;
	}

	/**
	 * @param  AnimActive
	 */
	public void setAnimActive(boolean AnimActive) {
		this.AnimActive = AnimActive;
	}

	public float getRotation() {
		return angle;
	}

	public float getHeigth() {
		return height;
	}

	/**
	 * @return
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @return
	 */
	public int getAnimStop() {
		return AnimStop;
	}

	/**
	 * @return
	 */
	public int getAnimStart() {
		return AnimStart;
	}

	/**
	 * @param  AnimStart
	 */
	public void setAnimStart(int AnimStart) {
		if (this.AnimStart != AnimStart) {
			this.AnimStart = AnimStart;
			AnimNow = AnimStart;
		}
	}

	/**
	 * @param  AnimStop
	 */
	public void setAnimStop(int AnimStop) {
		this.AnimStop = AnimStop;
	}

	/**
	 * @return
	 */
	public int getAnimNow() {
		return AnimNow;
	}

	/**
	 * @param  delta
	 */
	public void setPreferedDelta(float delta) {
		PreferedDelta = delta;
	}

	/**
	 * @return
	 */
	public float getPreferedDelta() {
		return PreferedDelta;
	}

	public void setFlipped(boolean state){
		if(state!=isFlipped){
			isFlipped = state;
			for(Sprite a:img)
				a.flip(true, false);
		}
	}
	
	public boolean getFlipped(){
		return isFlipped;
	}
	
	public void setSize(float width, float height){
		for(Sprite a:img)
			a.setSize(width, height);
	}
	
	/**
	 * @return
	 */
	public int getAnimCount() {
		return AnimCount;
	}
}
