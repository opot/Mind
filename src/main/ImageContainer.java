package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class ImageContainer {

	Image[] images;
	public Music nyan = null;
	public Music end = null;
	
	public ImageContainer() throws SlickException{
		images = new Image[2];
		try {
			images[0] = new Image("res/mobs/nyan.png");
			images[1] = new Image("res/mobs/blackman.png"); 
		} catch (SlickException e) {e.printStackTrace();}
		images[0].setName("nyan");
		images[1].setName("blackman");
		//nyan = new Music("res/nyan.ogg");
		//end = new Music("res/end.ogg");
		
		//Video splash;
	}
	
	public Image getImage(String name){
		for(Image img:images)
			if(img!=null)
				if(img.getName().equals(name))
					return img.copy();
		Image img = null;
		try {
			img = new Image("res/"+name+".png");
			img.setName(name);
		} catch (SlickException e) {e.printStackTrace();}
		Image[] buf = images;
		images = new Image[images.length+1];
		for(int i = 0;i<buf.length;i++)
			images[i] = buf[i];
		images[images.length-1] = img;
		return img.copy();
		
	}
	
}
