package main;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import main.Functions;
import main.things.Nothing;
import main.things.Tree;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {

	private int StateID = -1;
	Main game;
	
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
	Image world_mask = null;
	public Circle world = null;
	TimeMask time = null;
	GUI gui = null;
	boolean isMap = false;
	boolean debug = false;
	public float worldAngle = 0;

	public int Score = 0;

	@Override
	public int getID() {
		return StateID;
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame game)
			throws SlickException {
		super.enter(gc, game);

		BufferedWorld wrld = ((Main) game).buf;
		ImageContainer container = ((Main) game).container;

		this.Score = wrld.Score;
		playerAmmos = new Vector<AbstractAmmo>();
		mobAmmos = new Vector<AbstractAmmo>();
		mobs = new Vector<Mob>();
		areas = new Vector<AreaEffect>();
		world = new Circle(gc.getWidth() / 2, gc.getHeight() / 2,
				(float) (wrld.ids.length * 50 / Math.PI));
		world_mask.setRotation(wrld.rotation);
		worldAngle = wrld.rotation;
		name = wrld.name;
		player = new Player(100, 100, container);

		for (int i = 0; i <= 24 && wrld.playerInventory[i][0] != null; i++) {
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
			objects.add(Functions.createThingById(((Main) game).container,
					wrld.ids[i]));
		for (int i = 0; i < objects.size(); i++)
			objects.get(i).setAngle(360 / (float) objects.size() * i);

		items = new Vector<Item>();
		if (wrld.worldInventory != null)
			for (int i = 0; i < wrld.worldInventory.length; i++)
				items.add(Functions.createItem(wrld.worldInventory[i][0],
						wrld.worldInventory[i][1], container,
						wrld.worldInventory[i][2]));

		time.CurrentTime = wrld.time;
		gui = new GUI(player, ((Main) game).container);
		spwn = new MobSpawner(mobs, mobAmmos, time, ((Main) game).container);
		spawner = new Thread(spwn);
		spawner.setName("MobSpawner");
		spawner.start();
	}

	public GamePlayState(int StateID) {
		this.StateID = StateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		time = new TimeMask(((Main) game).container);
		world_mask = new Image("res/world.png");
		this.game = (Main) game;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
		if (isMap)
			draw_map(gc);
		else {
			time.render(g, gc);

			for (int i = 0; i <= objects.size() - 1; i++)
				objects.get(i).draw(g, world_mask, this, gc.getWidth());
			world_mask.draw(world.getX(), world.getY(), world.radius * 2,
					world.radius * 2);

			if (items.size() != 0)
				for (int i = 0; i <= items.size() - 1; i++)
					items.get(i).draw(g, world_mask, this, gc.getWidth());
			if (mobs.size() != 0)
				for (int i = 0; i <= mobs.size() - 1; i++)
					mobs.get(i).draw(g, world_mask, this, gc.getWidth());
			player.draw(g, world_mask, this, gc.getWidth());
			if (playerAmmos.size() != 0)
				for (int i = 0; i <= playerAmmos.size() - 1; i++)
					playerAmmos.get(i)
							.draw(g, world_mask, this, gc.getWidth());
			if (mobAmmos.size() != 0)
				for (int i = 0; i <= mobAmmos.size() - 1; i++)
					mobAmmos.get(i).draw(g, world_mask, this, gc.getWidth());
		}
		if (areas.size() != 0)
			for (int i = 0; i <= areas.size() - 1; i++)
				areas.get(i).draw(g, world_mask, this, gc.getWidth());
		gui.draw(g);
		g.drawString("Time :"+ (String.valueOf(time.CurrentTime) + "  ").substring(
								0, 5), 20, 45);
		if(debug)
			Functions.drawDebug(g, this);
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		if (button == 9) {
			try {
				save((Main) game);
			} catch (SlickException e) {e.printStackTrace();}
			spwn.isAlive = false;
			game.enterState(Main.MAINMENUSTATE);
		}	
		if (button == 8)
			isMap = !isMap;
		if(button == 2 && player.inventory[player.current] != null) {
			try {
				player.inventory[player.current].use(((Main) game).container, this);
			} catch (SlickException e) {e.printStackTrace();}
			if (player.inventory[player.current].Stack == 0)
				player.inventory[player.current] = null;
		}
		if (button == 10) {
			spwn.isAlive = false;
			spwn.isSpawning = false;
			try {
				save((Main) game);
			} catch (SlickException e) {e.printStackTrace();}
			game.enterState(Main.CRAFTMENUSTATE);
		}
		if(button == 5){
			player.current-=1;
			if(player.current==-1)
				player.current = 6;
		}
		if(button == 6){
			player.current+=1;
			if(player.current==7)
				player.current = 0;
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {

		// if(!((Main) game).container.back.playing()&&!((Main)
		// game).container.nyan.playing())
		// ((Main) game).container.back.play();

		if (player.hp <= 0) {
			spwn.isAlive = false;
			((Main) (game)).buf = new BufferedWorld(objects, mobs, player,
					name, time.CurrentTime, world_mask.getRotation(), items,
					Score, player.angle);
			game.enterState(Main.ENDOFGAMESTATE);
		}

		if (worldAngle > 360)
			worldAngle -= 360;
		if (worldAngle < -360)
			worldAngle += 360;
		if (worldAngle < 0)
			worldAngle += 360;

		if (time.update(delta) == 1) {
			world.setRadius(world.getRadius() + 100);
			while (objects.size() <= (int) (2 * Math.PI * world.radius / 100))
				objects.add(Functions
						.createRandomThing(((Main) game).container));
			for (int i = 0; i < objects.size(); i++)
				objects.get(i).setAngle(360 / (float) objects.size() * i);
		}

		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			save((Main) game);
			spwn.isAlive = false;
			game.enterState(Main.MAINMENUSTATE);
		}

		if (input.isKeyPressed(Input.KEY_1))
			player.current = Input.KEY_1 - 2;
		if (input.isKeyPressed(Input.KEY_2))
			player.current = Input.KEY_2 - 2;
		if (input.isKeyPressed(Input.KEY_3))
			player.current = Input.KEY_3 - 2;
		if (input.isKeyPressed(Input.KEY_4))
			player.current = Input.KEY_4 - 2;
		if (input.isKeyPressed(Input.KEY_5))
			player.current = Input.KEY_5 - 2;
		if (input.isKeyPressed(Input.KEY_6))
			player.current = Input.KEY_6 - 2;
		if (input.isKeyPressed(Input.KEY_7))
			player.current = Input.KEY_7 - 2;

		if (input.isKeyPressed(Input.KEY_M))
			isMap = !isMap;
		if (input.isKeyPressed(Input.KEY_F3))
				debug = !debug;
		if (input.isKeyPressed(Input.KEY_I)) {
			spwn.isAlive = false;
			spwn.isSpawning = false;
			save((Main) game);
			game.enterState(Main.CRAFTMENUSTATE);
		}

		float sum = worldAngle + player.angle;
		sum = sum - (int) (sum / 360) * 360;
		if (sum < 180) {
			if (Math.abs(2 * world.radius * Math.PI * sum / 180) > 300)
				worldAngle -= 20 * (float) delta / world.radius;
		}
		if (sum < 360 && sum > 180) {
			if (Math.abs(2 * world.radius * Math.PI * (360 - sum) / 180) > 300)
				worldAngle += 20 * (float) delta / world.radius;
		}

		world_mask.setRotation(worldAngle);

		if ((input.isKeyPressed(Input.KEY_SPACE)||input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				&& player.inventory[player.current] != null) {
			player.inventory[player.current].use(((Main) game).container, this);
			if (player.inventory[player.current].Stack == 0)
				player.inventory[player.current] = null;
		}

		world.setX(gc.getWidth() / 2 - world.radius);
		world.setY(gc.getHeight() / 2);
		world_mask.setCenterOfRotation(world.radius, world.radius);
		player.update(delta, this, gc.getInput(), world_mask.getRotation());
		for (int i = 0; i <= mobs.size() - 1; i++) {
			mobs.get(i).update(delta, world_mask.getRotation(), world.radius,
					world, sum);
			if (player.melee != null)
				if (player.melee.active)
					if (mobs.get(i).rect.intersects(player.melee.rect)) {
						mobs.get(i).hp -= player.melee.damage;
						player.melee.active = false;
						if (mobs.get(i).hp < 1) {
							mobs.get(i).drop(items, ((Main) game).container);
							mobs.remove(i);
							Score += 1;
						}
					}
		}
		for (int i = 0; i <= playerAmmos.size() - 1; i++) {
			if (playerAmmos.get(i).update(delta, world_mask.getRotation(),
					world)) {
				playerAmmos.get(i).drop(((Main) game).container, items);
				playerAmmos.remove(i);
			}
			boolean sh = false;
			for (int j = 0; j < mobs.size() && !sh; j++)
				if (playerAmmos.get(i).rect.intersects(mobs.get(j).rect)) {
					playerAmmos.get(i).drop(((Main) game).container, items);
					mobs.get(j).hp -= playerAmmos.get(i).damage;
					if (mobs.get(j).hp < 1) {
						mobs.get(j).drop(items, ((Main) game).container);
						mobs.remove(j);
						Score += 1;
					}
					playerAmmos.remove(i);
					sh = !sh;
				}

		}
		for (int i = 0; i <= mobAmmos.size() - 1; i++) {
			if (mobAmmos.get(i).update(delta, world_mask.getRotation(), world)) {
				mobAmmos.get(i).drop(((Main) game).container, items);
				mobAmmos.remove(i);
				continue;
			}
			boolean sh = false;
			for (int j = 0; j < mobAmmos.size() && !sh; j++)
				if (mobAmmos.get(i).rect.intersects(player.rect)) {
					player.hp -= mobAmmos.get(i).damage;
					if (player.hp < 1)
						player.hp = 0;
					mobAmmos.remove(i);
					sh = !sh;
				}

		}
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update(world, world_mask.getRotation());
			if (items.get(i).rect.intersects(player.rect)) {
				for (int j = 0; j <= 24; j++)
					if (player.inventory[j] != null)
						if (player.inventory[j].id.equals(items.get(i).id)) {
							player.inventory[j].Stack += items.get(i).Stack;
							items.remove(i);
							return;
						}
				for (int j = 0; j <= 63; j++)
					if (player.inventory[j] == null) {
						items.get(i).img.setRotation(0);
						player.inventory[j] = items.get(i);
						items.remove(i);
						return;
					}
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).createRect(world, world_mask.getRotation());
			if (player.melee != null)
				if (player.melee.active)
					if (objects.get(i).rect.intersects(player.melee.rect)
							&& objects.get(i) instanceof Tree) {
						Tree b = (Tree) objects.get(i);
						player.melee.active = false;
						b.hp -= player.melee.damage;
						if (b.hp <= 0) {
							b.drop(items, ((Main) game).container);
							Nothing n = new Nothing(((Main) game).container);
							n.setAngle(b.angle);
							objects.set(i, n);
						}
					}
		}
		if (areas.size() != 0)
			for (int i = 0; i <= areas.size() - 1; i++)
				if (areas.get(i).update(this, delta)) {
					areas.remove(i);
					i--;
				} else {
					areas.get(i).interract(this, game);
				}
	}

	private void draw_map(GameContainer gc) throws SlickException {

		Circle world = new Circle(gc.getWidth() / 2, gc.getHeight() / 2,
				(float) (gc.getHeight() * 0.3));

		world.setRadius((float) (objects.size() * gc.getHeight() / (8 * Math.PI + 2 * objects
				.size())));
		world.setCenterX(gc.getWidth() / 2);
		world.setCenterY(gc.getHeight() / 2);
		float height = (int) (world.radius * 2 * Math.PI) / objects.size() * 2;

		world_mask.setCenterOfRotation(world.radius, world.radius);
		world_mask.draw(world.getX(), world.getY(), world.radius * 2,
				world.radius * 2);

		for (int i = 0; i <= objects.size() - 1; i++) {
			Image o = objects.get(i).img;
			o.setCenterOfRotation(height / 4, height);
			o.setRotation(world_mask.getRotation() + 360f / (objects.size())
					* i);
			o.draw((float) (world.getCenterX() - height / 4 + world.radius
					* Math.sin(Math.toRadians(o.getRotation()))),
					(float) (world.getCenterY() - height - world.radius
							* Math.cos(Math.toRadians(o.getRotation()))),
					height / 2, height);
		}
	}

	private void save(Main game) throws SlickException {
		if (playerAmmos.size() != 0)
			for (AbstractAmmo a : playerAmmos)
				if (a instanceof main.ammos.boom)
					items.add(Functions.createItem(2, 1, new ImageContainer(),
							(int) a.angle));
		BufferedWorld buf = new BufferedWorld(objects, mobs, player, name,
				time.CurrentTime, world_mask.getRotation(), items, Score,
				player.angle);
		game.buf = buf;

		try {
			FileOutputStream fo = new FileOutputStream("res/Saves/" + name);
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
	public void mouseWheelMoved(int change){
		player.current+=change/120;
		if(player.current==7)
			player.current = 0;
		if(player.current==-1)
			player.current = 6;
	}
}
