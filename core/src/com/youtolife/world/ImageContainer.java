package com.youtolife.world;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ImageContainer {

	/**
	 */
	Vector<Texture> images = new Vector<Texture>();
	/**
	 */
	Vector<String> names = new Vector<String>();

	public ImageContainer() {
		images.add(new Texture(Gdx.files.internal("data/mobs/nyan.png")));
		images.add(new Texture(Gdx.files.internal("data/mobs/blackman.png")));
		images.add(new Texture(Gdx.files.internal("data/mobs/santa.png")));
		images.add(new Texture(Gdx.files.internal("data/weapons/range/present.png")));
		images.add(new Texture(Gdx.files.internal("data/weapons/range/sphere.png")));
		names.add("nyan");
		names.add("blackman");
		names.add("santa");
		names.add("present");
		names.add("sphere");
	}

	public Texture getImage(String name) {
		for (int i = 0; i < names.size(); i++)
			if (names.get(i).equals(name))
				return images.get(i);
		Texture img = null;
		img = new Texture(Gdx.files.internal("data/" + name + ".png"));
		names.add((name));
		images.add(img);
		return img;

	}

	public void dispose() {
		for (Texture t : images)
			t.dispose();
	}
	
}
