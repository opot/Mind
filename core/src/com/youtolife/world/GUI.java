package com.youtolife.world;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GUI {

	/**
	 */
	Player player;
	/**
	 */
	Sprite gui;
	/**
	 */
	Sprite hpBar;
	/**
	 */
	Sprite curItem;
	
	public GUI(Player player, ImageContainer container){
		hpBar = new Sprite(container.getImage("GUI/hp"));
		gui = new Sprite(new TextureRegion(container.getImage("GUI/gui"),800,128));
		curItem = new Sprite(container.getImage("GUI/curItem"));
		curItem.setSize(50, 50);
		this.player = player;
		gui.setPosition(-400, 300-gui.getHeight());
		hpBar.setSize(player.hp*2,25);
		hpBar.setPosition(-400+80, 300-13-hpBar.getHeight());
	}
	
	public void draw(SpriteBatch batch,BitmapFont font){
		gui.draw(batch);
		hpBar.setSize(player.hp*2,25);
		hpBar.draw(batch);
		font.setScale(0.5f);
		for(int i = 0; i <= 6;i++)
			if(player.inventory[i]!=null){
				player.inventory[i].anim.setPosition(-90+60*i, 250);
				player.inventory[i].anim.draw(batch);
				font.draw(batch,String.valueOf(player.inventory[i].Stack), 312+60*i-400, 270);
			}
		curItem.setPosition(-90+60*player.current, 250);
		curItem.draw(batch);
	}
	
}
