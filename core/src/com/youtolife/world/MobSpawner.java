package com.youtolife.world;

import java.util.Random;
import java.util.Vector;

import com.youtolife.world.mobs.Nyan;
import com.youtolife.world.mobs.Santa;
import com.youtolife.world.mobs.blackman;

public class MobSpawner implements Runnable {

	/**
	 */
	Vector<Mob> mobs = null;
	/**
	 */
	Vector<AbstractAmmo> ammos = null;
	/**
	 */
	TimeMask time = null;
	/**
	 */
	ImageContainer container;
	/**
	 */
	Random r = new Random();
	/**
	 */
	public boolean isSpawning = true;
	/**
	 */
	public boolean isAlive = true;

	public MobSpawner(Vector<Mob> mobs,Vector<AbstractAmmo> ammos , TimeMask time, ImageContainer container) {
		this.mobs = mobs;
		this.time = time;
		this.ammos = ammos;
		this.container = container;
	}

	@Override
	public void run() {
		while (isAlive) {
			if (isSpawning) {
				try {
					Thread.sleep(800 * (r.nextInt(40)));
					synchronized (mobs) {
						spawn();
						System.out.println(mobs.size());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void spawn() {
		int a = r.nextInt(2);
		if(a == 1){
		if (time.CurrentTime > 22 || time.CurrentTime < 6)
			synchronized(ammos){
				mobs.add(new blackman(r.nextInt(360), r.nextInt(360), container,ammos));
			}
		if (time.CurrentTime > 6 && time.CurrentTime < 22)
			mobs.add(new Nyan(r.nextInt(360), r.nextInt(360), container));
		}else
			mobs.add(new Santa(r.nextInt(360), r.nextInt(360), container,ammos));
			
	}

}
