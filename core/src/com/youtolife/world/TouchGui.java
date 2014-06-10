package com.youtolife.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtolife.world.states.GamePlayState.ButtonTouch;

public class TouchGui {

	/**
	 */
	Sprite back;
	/**
	 */
	Sprite left;
	/**
	 */
	Sprite right;
	/**
	 */
	Sprite use;
	
	public TouchGui(ImageContainer container) {
		back = new Sprite(container.getImage("GUI/back"));
		back.setSize(100, 100);
		back.setPosition(300, 200);
		left = new Sprite(container.getImage("GUI/left"));
		left.setSize(100, 100);
		left.setPosition(-390, -200);
		right = new Sprite(container.getImage("GUI/right"));
		right.setSize(100, 100);
		right.setPosition(-280, -200);
		use = new Sprite(container.getImage("GUI/use"));
		use.setSize(100, 100);
		use.setPosition(290, -200);
	}

	public void draw(SpriteBatch batch){
		use.draw(batch);
		left.draw(batch);
		right.draw(batch);
		back.draw(batch);
	}
	
	public ButtonTouch[] update(Player player){
		ButtonTouch bt[] = new ButtonTouch[2];
		bt[0] = ButtonTouch.None;
		bt[1] = ButtonTouch.None;
		Input input = Gdx.input;
		for(int i = 0;i<=1;i++)
		if(input.isTouched(i)){
			int w = Gdx.graphics.getWidth();
			int h = Gdx.graphics.getHeight();
			if(input.getY(i)<h*5/6&&input.getY(i)>h*2/3){
				if(input.getX(i)>w/80&&input.getX(i)<w*11/80)
					bt[i] = ButtonTouch.Left;
				if(input.getX(i)>w*3/20&&input.getX(i)<w*11/40)
					bt[i] = ButtonTouch.Right;
				if(input.getX(i)>w*69/80&&input.getX(i)<w*79/80)
					bt[i] = ButtonTouch.Use;
			}
			if(input.getY(i)<h/6&&input.getY(i)>0&&input.getX(i)>w*7f/8f)
				bt[i] = ButtonTouch.Back;
			
			if(input.getY(i)>0&&input.getY(i)<h/16){
				int x = input.getX(i);
				for(int j = 0; j <= 6;j++){
					if(x>312f/800f*w+54f/800f*w*j&&x<312f/800f*w+54f/800*w*(j)+w/16f)
						player.current = j;
				}
			}
		}
		return bt;
	}
	
}
