package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TimeMask {

	float CurrentTime = 12f;
	Image day_mask;
	Image night_mask;
	public int time_speed = 10000;
	
	public TimeMask(ImageContainer container) throws SlickException{
		day_mask = container.getImage("time/day");
		night_mask = container.getImage("time/night");
	}
	public void render(Graphics g, GameContainer gc){
		night_mask.draw(0, 0, gc.getWidth(), gc.getHeight());
		day_mask.draw(0, 0, gc.getWidth(), gc.getHeight());
	}
	
	
	public int update(float delta){
		
		int result = 0;
		
		CurrentTime+=(delta/time_speed);
		if(CurrentTime>24){
			CurrentTime = 0.001f;
			result = 1;
		}
		if(CurrentTime>=12f&&CurrentTime<22f)
			day_mask.setAlpha((float) (1-(CurrentTime-12)/10));
		else
			if(CurrentTime>=2f&&CurrentTime<12f)
				day_mask.setAlpha((float) (CurrentTime-2)/10);
			else if((CurrentTime>=22f&&CurrentTime<24f)||(CurrentTime>=0f&&CurrentTime<2f))
				day_mask.setAlpha(0);
		return result;
	}
	
}
