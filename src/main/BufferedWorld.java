package main;

import java.io.Serializable;
import java.util.Vector;

public class BufferedWorld implements Serializable {

	private static final long serialVersionUID = 1L;
	int[] ids;
	String name;
	Integer[][] playerInventory= new Integer[25][2];
	int[][] worldInventory = null;
	int[][] mobs = null;
	int hp;
	float time;
	float rotation;
	float playerAngle;
	int Score = 0;

	public BufferedWorld(Vector<Thing> objects, Vector<Mob> mobs, Player player, String name,
			float time, float rotation,Vector<Item> items, int Score,float playerAngle) {

		this.time = time;
		this.name = name;
		this.rotation = rotation;
		this.playerAngle = playerAngle;
		this.Score = Score;
		Item[] buf;
		hp = player.hp;
		buf = player.inventory;
		for(int i = 0;i<=24;i++)
			if(buf[i]!=null){
				playerInventory[i][0] = buf[i].id;
				playerInventory[i][1] = buf[i].Stack;
			}
		ids = new int[objects.size()];
		for (int i = 0; i <= objects.size() - 1; i++) 
			ids[i] = objects.get(i).id;
		worldInventory = new int[items.size()][3];
		for(int i = 0;i<items.size();i++){
			worldInventory[i][0] = items.get(i).id;
			worldInventory[i][1] = items.get(i).Stack;
			worldInventory[i][2] = (int) items.get(i).getAngle();
		}
		this.mobs = new int[mobs.size()][2];
		for(int i = 0;i<mobs.size();i++){
			this.mobs[i][0] = mobs.get(i).id;
			this.mobs[i][1] = (int) mobs.get(i).angle;
		}
	}

}
