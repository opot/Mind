package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {

	private float x, y;
	private float angle = 0;
	private float width, height;
	private Image[] img = null;
	private boolean isPhisical = true;

	private boolean AnimActive = true;
	private int AnimStart = 0;
	private int AnimStop = 0;
	private int AnimNow = 0;
	private int AnimCount = 0;
	private float AnimDelta = 0;
	private float PreferedDelta = 200;

	public void draw(Graphics g) {
		img[AnimNow].draw(x, y);
	}

	private void CutImage(Image src) {
		for (int i = 0; i <= src.getWidth() / width - 1; i++)
			img[i] = src.getSubImage((int) (i * width), 0, (int) width,
					(int) height);
	}

	public Sprite(float x, float y, float width, float height,
			boolean isPhisical, Image img, float angle) {
		this.img = new Image[(int) (img.getWidth() / width)];
		this.width = width;
		this.height = height;
		AnimStart = 0;
		AnimStop = this.img.length - 1;
		AnimCount = (int) (img.getWidth() / width);
		this.x = x;
		this.y = y;
		this.isPhisical = isPhisical;
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

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setRotation(float angle) {
		for (int i = 0; i <= img.length - 1; i++) {
			img[i].setCenterOfRotation(img[i].getWidth() / 2,
					img[i].getHeight()/2);
			img[i].setRotation(angle);
		}
	}

	public boolean isAnimActive() {
		return AnimActive;
	}

	public void setAnimActive(boolean AnimActive) {
		this.AnimActive = AnimActive;
	}

	public float getRotation() {
		return angle;
	}

	public boolean isPhisical() {
		return isPhisical;
	}

	public void setPhisical(boolean phis) {
		isPhisical = phis;
	}

	public float getHeigth() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public int getAnimStop() {
		return AnimStop;
	}

	public int getAnimStart() {
		return AnimStart;
	}

	public void setAnimStart(int AnimStart) {
		if(this.AnimStart != AnimStart){
			this.AnimStart = AnimStart;
			AnimNow = AnimStart;
		}
	}

	public void setAnimStop(int AnimStop) {
		this.AnimStop = AnimStop;
	}

	public int getAnimNow(){
		return AnimNow;
	}
	
	public void setPreferedDelta(float delta) {
		PreferedDelta = delta;
	}

	public float getPreferedDelta() {
		return PreferedDelta;
	}

	public int getAnimCount(){
		return AnimCount;
	}
}
