package com.youtolife.world.states;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.youtolife.world.AbstractAmmo;
import com.youtolife.world.AreaEffect;
import com.youtolife.world.BufferedWorld;
import com.youtolife.world.Functions;
import com.youtolife.world.GUI;
import com.youtolife.world.GameState;
import com.youtolife.world.ImageContainer;
import com.youtolife.world.Item;
import com.youtolife.world.MainGame;
import com.youtolife.world.Mob;
import com.youtolife.world.MobSpawner;
import com.youtolife.world.Player;
import com.youtolife.world.StateBasedGame;
import com.youtolife.world.Thing;
import com.youtolife.world.TimeMask;
import com.youtolife.world.TouchGui;
import com.youtolife.world.ammos.boom;
import com.youtolife.world.things.Nothing;

public class GamePlayState extends GameState {


	public MainGame game;
	Thread spawner;
	MobSpawner spwn;
	
	public Vector<Thing> objects;
	public Vector<AreaEffect> areas;
	public Vector<AbstractAmmo> playerAmmos = null;
	public Vector<AbstractAmmo> mobAmmos = null;
	public Vector<Mob> mobs = null;
	public Vector<Item> items = null;
	
	public Player player = null;
	String name = "NoName";
	
	public Sprite world_mask = null;
	public Circle world = null;
	TimeMask time = null;
	TouchGui tgui;
	GUI gui = null;
	
	public boolean debug = false;
	public float worldAngle = 0;
	public ImageContainer container;
	public BitmapFont font;
	float key_delay = 0.2f;
	public int Score = 0;

	@Override
	public void enter(StateBasedGame game) {
		MainGame g = (MainGame) game;
		BufferedWorld wrld = g.buf;
		container = g.container;

		this.Score = wrld.Score;
		playerAmmos = new Vector<AbstractAmmo>();
		mobAmmos = new Vector<AbstractAmmo>();
		mobs = new Vector<Mob>();
		areas = new Vector<AreaEffect>();
		world = new Circle(MainGame.w / 2, MainGame.h / 2,
				(float) (wrld.ids.length * 50 / Math.PI));
		world_mask.setRotation(wrld.rotation);
		worldAngle = wrld.rotation;
		name = wrld.name;
		player = new Player(container, this);

		for (int i = 0; i <= 24; i++) {
			if (wrld.playerInventory[i][0] != null)
				player.inventory[i] = Functions.createItem(
						wrld.playerInventory[i][0], wrld.playerInventory[i][1],
						container, 0);
		}

		for (int i = 0; i < wrld.mobs.length; i++)
			mobs.add(Functions.createMobById(wrld.mobs[i][0], wrld.mobs[i][1],
					container, mobAmmos));

		player.current = 0;
		player.hp = wrld.hp;
		player.angle = wrld.playerAngle;
		objects = new Vector<Thing>();

		for (int i = 0; i <= wrld.ids.length - 1; i++)
			objects.add(Functions.createThingById(g.container, wrld.ids[i]));
		for (int i = 0; i < objects.size(); i++)
			objects.get(i).setAngle(360 / (float) objects.size() * i);

		items = new Vector<Item>();
		if (wrld.worldInventory != null)
			for (int i = 0; i < wrld.worldInventory.length; i++)
				items.add(Functions.createItem(wrld.worldInventory[i][0],
						wrld.worldInventory[i][1], container,
						wrld.worldInventory[i][2]));

		time.CurrentTime = wrld.time;
		gui = new GUI(player, g.container);
		spwn = new MobSpawner(mobs, mobAmmos, time, g.container);
		spawner = new Thread(spwn);
		spawner.setName("MobSpawner");
		spawner.start();
	}

	public GamePlayState(int id, MainGame game) {
		super(id, game);
	}

	@Override
	public void init(StateBasedGame game) {
		time = new TimeMask(((MainGame) game).container);
		world_mask = new Sprite(((MainGame) game).container.getImage(("world")));
		tgui = new TouchGui(((MainGame) game).container);
		font = new BitmapFont(
				Gdx.files.internal("data/font.fnt"),
				new TextureRegion(((MainGame) game).container.getImage("font")),
				false);
		this.game = (MainGame) game;
	}

	@Override
	public void draw(SpriteBatch batch) {
		time.render(batch);

		for (int i = 0; i <= objects.size() - 1; i++)
			objects.get(i).draw(batch, world_mask, this);
		world_mask.draw(batch);
		if (items.size() != 0)
			for (int i = 0; i <= items.size() - 1; i++)
				items.get(i).draw(batch, world_mask, this);
		if (mobs.size() != 0)
			for (int i = 0; i <= mobs.size() - 1; i++)
				mobs.get(i).draw(batch, world_mask, this);
		player.draw(batch, world_mask, this);
		if (playerAmmos.size() != 0)
			for (int i = 0; i <= playerAmmos.size() - 1; i++)
				playerAmmos.get(i).draw(batch, world_mask, this);
		if (mobAmmos.size() != 0)
			for (int i = 0; i <= mobAmmos.size() - 1; i++)
				mobAmmos.get(i).draw(batch, world_mask, this);
		if (areas.size() != 0)
			for (int i = 0; i <= areas.size() - 1; i++)
				areas.get(i).draw(batch, world_mask, this);
		gui.draw(batch, font);
		if (Gdx.input.isPeripheralAvailable(Input.Peripheral.MultitouchScreen))
			tgui.draw(batch);
		font.setScale(0.45f);
		font.draw(
				batch,
				"Time :"
						+ (String.valueOf(time.CurrentTime) + "  ").substring(
								0, 5), -MainGame.w / 2 + 18,
				MainGame.h / 2 - 48);
		if (debug)
			Functions.drawDebug(batch, this, font);
	}

	public enum ButtonTouch {
		Left, Right, Back, Use, None
	}

	@Override
	public void update(StateBasedGame game) {
		Input input = Gdx.input;
		MainGame g = (MainGame) game;

		ButtonTouch bt[] = new ButtonTouch[2];
		bt[0] = ButtonTouch.None;
		bt[1] = ButtonTouch.None;
		if (Gdx.input.isPeripheralAvailable(Input.Peripheral.MultitouchScreen))
			bt = tgui.update(player);

		if (player.hp <= 0) {
			spwn.isAlive = false;
			g.buf = new BufferedWorld(objects, mobs, player, name,
					time.CurrentTime, world_mask.getRotation(), items, Score,
					player.angle);
			game.enterState(MainGame.ENDOFGAMESTATE);
		}

		if (worldAngle > 360)
			worldAngle -= 360;
		if (worldAngle < -360)
			worldAngle += 360;
		if (worldAngle < 0)
			worldAngle += 360;

		if (time.update() == 1) {
			world.setRadius(world.radius + 100);
			while (objects.size() <= (int) (2 * Math.PI * world.radius / 100))
				objects.add(Functions.createRandomThing(g.container));
			for (int i = 0; i < objects.size(); i++)
				objects.get(i).setAngle(360f / (float) objects.size() * i);
		}

		if (input.isKeyPressed(Input.Keys.ESCAPE) || bt[0] == ButtonTouch.Back
				|| bt[1] == ButtonTouch.Back) {
			save(g);
			spwn.isAlive = false;
			game.enterState(MainGame.MAINMENUSTATE);
		}

		if (input.isKeyPressed(Input.Keys.NUM_1))
			player.current = 0;
		if (input.isKeyPressed(Input.Keys.NUM_2))
			player.current = 1;
		if (input.isKeyPressed(Input.Keys.NUM_3))
			player.current = 2;
		if (input.isKeyPressed(Input.Keys.NUM_4))
			player.current = 3;
		if (input.isKeyPressed(Input.Keys.NUM_5))
			player.current = 4;
		if (input.isKeyPressed(Input.Keys.NUM_6))
			player.current = 5;
		if (input.isKeyPressed(Input.Keys.NUM_7))
			player.current = 6;

		key_delay -= Gdx.graphics.getDeltaTime();
		if (key_delay < 0)
			key_delay = 0;
		if (key_delay == 0) {
			if (input.isKeyPressed(Input.Keys.F3)) {
				debug = !debug;
				key_delay = 0.2f;
			}
			if (input.isKeyPressed(Input.Keys.I)) {
				spwn.isAlive = false;
				spwn.isSpawning = false;
				save(g);
				key_delay = 0.2f;
				game.enterState(MainGame.CRAFTMENUSTATE);
				if (input.isKeyPressed(Input.Keys.SPACE)
						&& player.inventory[player.current] != null) {
					player.inventory[player.current].use(g.container, this);
					if (player.inventory[player.current].Stack == 0)
						player.inventory[player.current] = null;
				}
			}
			if ((input.isKeyPressed(Input.Keys.SPACE)
					|| bt[0] == ButtonTouch.Use || bt[1] == ButtonTouch.Use)
					&& player.inventory[player.current] != null) {
				key_delay = 0.2f;
				player.inventory[player.current].use(g.container, this);
				if (player.inventory[player.current].Stack == 0)
					player.inventory[player.current] = null;
			}
		}
		float sum = worldAngle + player.angle;
		sum = sum - (int) (sum / 360) * 360;
		if (sum < 180) {
			if (Math.abs(2 * world.radius * Math.PI * sum / 180) > 300)
				worldAngle -= 20 * Gdx.graphics.getDeltaTime() * 1000
						/ world.radius;
		}
		if (sum < 360 && sum > 180) {
			if (Math.abs(2 * world.radius * Math.PI * (360 - sum) / 180) > 300)
				worldAngle += 20 * Gdx.graphics.getDeltaTime() * 1000
						/ world.radius;
		}

		world_mask.setRotation(worldAngle);

		world.setX(-world.radius);
		world.setY(-world.radius * 2);
		world_mask.setOrigin(world.radius, world.radius);
		world_mask.setPosition(world.x, world.y);
		world_mask.setSize(world.radius * 2, world.radius * 2);
		world_mask.setColor(1, 1, 1, 1);
		player.update(this, bt, world_mask.getRotation());

		Iterator<AbstractAmmo> ammoIt = playerAmmos.iterator();
		while (ammoIt.hasNext()) {
			AbstractAmmo a = ammoIt.next();
			if (a.update(this)) {
				a.drop(g.container, items);
				ammoIt.remove();
			}
		}

		synchronized (mobs) {
			Iterator<Mob> it = mobs.iterator();
			while (it.hasNext()) {
				Mob a = it.next();
				if (a.hp < 1) {
					a.drop(items, g.container);
					it.remove();
					Score += 1;
				} else {
					a.update(world_mask.getRotation(), world.radius, world, sum);
					ammoIt = playerAmmos.iterator();
					while (ammoIt.hasNext()) {
						AbstractAmmo b = ammoIt.next();
						boolean sh = false;
						if (Intersector.overlapConvexPolygons(b.rect, a.rect)) {
							b.drop(g.container, items);
							a.hp -= b.damage;
							ammoIt.remove();
							sh = !sh;
						}
					}
				}
			}
		}
		for (int i = 0; i <= mobAmmos.size() - 1; i++) {
			if (mobAmmos.get(i).update(this)) {
				mobAmmos.get(i).drop(g.container, items);
				mobAmmos.remove(i);
				continue;
			}
			boolean sh = false;
			for (int j = 0; j < mobAmmos.size() && !sh; j++)
				if (Intersector.overlapConvexPolygons(mobAmmos.get(i).rect,
						player.rect)) {
					player.hp -= mobAmmos.get(i).damage;
					if (input.isPeripheralAvailable(Input.Peripheral.Vibrator))
						input.vibrate(100);

					if (player.hp < 1)
						player.hp = 0;
					mobAmmos.remove(i);
					sh = !sh;
				}

		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update(world, world_mask.getRotation());
			if (Intersector.overlapConvexPolygons(items.get(i).rect,
					player.rect)) {
				for (int j = 0; j <= 24; j++)
					if (player.inventory[j] != null)
						if (player.inventory[j].id.equals(items.get(i).id)) {
							player.inventory[j].Stack += items.get(i).Stack;
							items.remove(i);
							return;
						}
				for (int j = 0; j <= 24; j++)
					if (player.inventory[j] == null) {
						items.get(i).anim.setRotation(0);
						player.inventory[j] = items.get(i);
						items.remove(i);
						return;
					}
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).createRect(world, world_mask.getRotation());
			objects.get(i).update(this, game);
			if (objects.get(i).hp <= 0) {
				objects.get(i).drop(items, container);
				Nothing n = new Nothing(container);
				n.setAngle(objects.get(i).angle);
				objects.set(i, n);
			}
		}
		if (areas.size() != 0)
			for (int i = 0; i <= areas.size() - 1; i++)
				if (areas.get(i).update(this)) {
					areas.remove(i);
					i--;
				} else {
					areas.get(i).interract(this, game);
				}
		if (player.melee != null)
			player.melee.interract(this);
	}

	private void save(MainGame game) {
		if (playerAmmos.size() != 0)
			for (AbstractAmmo a : playerAmmos)
				if (a instanceof boom)
					items.add(Functions.createItem(2, 1, new ImageContainer(),
							a.angle));
		BufferedWorld buf = new BufferedWorld(objects, mobs, player, name,
				time.CurrentTime, world_mask.getRotation(), items, Score,
				player.angle);
		game.buf = buf;

		try {
			FileOutputStream fo = new FileOutputStream(Gdx.files.local(
					"Saves/" + name).file());
			ObjectOutputStream stream = new ObjectOutputStream(fo);
			stream.writeObject(buf);
			stream.flush();
			stream.close();
			System.out.println("Succesfully saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
