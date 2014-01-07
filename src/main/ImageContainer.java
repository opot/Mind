package main;

import java.util.Vector;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class ImageContainer {

	Vector<Image> images = new Vector<Image>();
	public Music nyan = null;
	public Music end = null;
	public Music back = null;
	
	public ImageContainer() throws SlickException{
		try {
			images.add(new Image("res/mobs/nyan.png"));
			images.add(new Image("res/mobs/blackman.png")); 
			images.add(new Image("res/mobs/santa.png")); 
			images.add(new Image("res/weapons/range/present.png"));
			images.add(new Image("res/weapons/range/sphere.png"));
		} catch (SlickException e) {e.printStackTrace();}
		images.get(0).setName("nyan");
		images.get(1).setName("blackman");
		images.get(2).setName("santa");
		images.get(3).setName("present");
		images.get(4).setName("sphere");
		//nyan = new Music("res/nyan.ogg");
		//end = new Music("res/end.ogg");
		//back = new Music("res/back.ogg");
	}
	
	public Image getImage(String name){
		for(Image img:images)
			if(img.getName().equals(name))
				return img.copy();
		Image img = null;
		try {
			img = new Image("res/"+name+".png");
			img.setName(name);
		} catch (SlickException e) {e.printStackTrace();}
		images.add(img);
		return img.copy();
		
	}
	
}
