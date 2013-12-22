package main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class GUI {

	Player player;
	Image gui;
	Image hpBar;
	Image curItem;
	
	public GUI(Player player, ImageContainer container){
		hpBar = container.getImage("hp");
		gui = container.getImage("gui");
		curItem = container.getImage("curItem");
		this.player = player;
	}
	
	public void draw(Graphics g){
		gui.draw(0, 0);
		hpBar.draw(86+100-player.hp,14,player.hp*2,25);
		for(int i = 0; i <= 6;i++)
			if(player.inventory[i]!=null){
				player.inventory[i].img.draw(310+60*i, 0);
				g.drawString(String.valueOf(player.inventory[i].Stack), 312+60*i, 30);
			}
		curItem.draw(310+60*player.current,0);
	}
	
}
