package com.youtolife.world.states;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.youtolife.world.AnimatedSprite;
import com.youtolife.world.BufferedWorld;
import com.youtolife.world.Functions;
import com.youtolife.world.GameState;
import com.youtolife.world.Item;
import com.youtolife.world.MainGame;
import com.youtolife.world.Mob;
import com.youtolife.world.Player;
import com.youtolife.world.StateBasedGame;
import com.youtolife.world.Thing;
import com.youtolife.world.TimeMask;

public class MainMenuState extends GameState {

	/**
	 */
	Sprite world_mask;
	/**
	 */
	Sprite back;
	/**
	 */
	String[][] menu = new String[4][1];
	/**
	 */
	Thing[][] objects;
	/**
	 */
	int Step = 0;
	/**
	 */
	int CurPos = 0;
	/**
	 */
	BitmapFont font;
	/**
	 */
	Circle world;
	/**
	 */
	TimeMask time;
	/**
	 */
	MainGame game;

	/**
	 */
	float switch_time = 0.2f;

	public MainMenuState(int id, MainGame game) {
		super(id,game);
	}

	@Override
	public void enter(StateBasedGame game) {
		Step = 0;
		CurPos = 0;
		menu[0] = new String[4];
		menu[0][0] = "Play";
		menu[0][1] = "Exit";
		menu[0][2] = "Settings";
		menu[0][3] = "About";
		// try{
		// menu[1] = Gdx.files.internal("saves").file().list();
		// String[] buf = new String[menu[1].length + 1];
		// buf[0] = "New World";
		// for (int i = 1; i <= menu[1].length; i++)
		// buf[i] = menu[1][i - 1];
		// menu[1] = buf;
		// }catch(NullPointerException e){
		// e.printStackTrace();
		// }

		menu[1] = new String[1];
		menu[1][0] = "New World";
		menu[2] = new String[4];
		menu[2][0] = "Difficulty";
		menu[2][1] = "Fullscreen";
		menu[2][2] = "Sounds";
		menu[2][3] = "Resolution";
		menu[3] = new String[6];
		menu[3][0] = "600x800";
		menu[3][1] = "1280x800";
		menu[3][2] = "1366x768";
		menu[3][3] = "1400x900";
		menu[3][4] = "1680x1050";
		menu[3][5] = "1920x1080";

		objects = new Thing[4][1];
		for (int i = 0; i <= 3; i++) {
			objects[i] = new Thing[menu[i].length];
			for (int j = 0; j <= objects[i].length - 1; j++)
				objects[i][j] = Functions
						.createRandomThing(((MainGame) game).container);
		}
		world = new Circle(MainGame.w / 2, MainGame.h / 2,
				(float) (MainGame.h * 0.3));
	}

	@Override
	public void init(StateBasedGame game) {
		MainGame g = (MainGame) game;

		world_mask = new Sprite(g.container.getImage("world"));
		back = new Sprite(g.container.getImage("GUI/back"));
		back.setSize(100, 100);
		back.setPosition(300, -300);
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"),
				new TextureRegion(g.container.getImage("font")), false);
		time = new TimeMask(g.container);
		this.game = g;
	}

	public void draw(SpriteBatch batch) {

		time.render(batch);

		world.setRadius((float) ((menu[Step].length * 200) / 2 / Math.PI));
		world.setX(-world.radius);
		world.setY(-world.radius * 2);
		world_mask.setOrigin(world.radius, world.radius);
		for (int i = 0; i <= menu[Step].length - 1; i++) {
			AnimatedSprite o = objects[Step][i].anim;
			o.setRotation(world_mask.getRotation() + 360f / (menu[Step].length)
					* i);
			float x = (float) ((world.radius - 10) * Math.cos(Math
					.toRadians(world_mask.getRotation() + 360f
							/ (menu[Step].length) * i)))
					- o.getWidth() / 2;
			float y = (float) ((world.radius - 10) * Math.sin(Math
					.toRadians(world_mask.getRotation() + 360f
							/ (menu[Step].length) * i)))
					+ world.y / 2;
			o.setRotation(world_mask.getRotation() + 360f / (menu[Step].length)
					* i - 90);
			o.setPosition(x, y);
			o.draw(batch);
		}
		world_mask.setPosition(world.x, world.y);
		world_mask.setSize(world.radius * 2, world.radius * 2);
		world_mask.draw(batch);
		font.draw(batch, menu[Step][CurPos],
				-font.getBounds(menu[Step][CurPos]).width / 2, -16);

		if (Gdx.input.isPeripheralAvailable(Input.Peripheral.MultitouchScreen))
			back.draw(batch);
	}

	/**
	 * @author           Grigory
	 */
	public enum touchAction {
		/**
		 */
		Left, /**
		 */
		Right, /**
		 */
		Enter, /**
		 */
		Back
	}

	public void touchAction(touchAction a, StateBasedGame game) {
		if (a == touchAction.Left) {
			CurPos--;
		}
		if (a == touchAction.Right) {
			CurPos++;
		}
		if (switch_time == 0) {
			if (a == touchAction.Enter) {
				check_enter(game);
				switch_time = 0.2f;
			}
			if (a == touchAction.Back) {
				if (Step == 1 || Step == 2)
					Step = 0;
				if (Step == 3)
					Step = 2;
				switch_time = 0.2f;
			}
		}
	}

	@Override
	public void update(StateBasedGame game) {
		Input input = Gdx.input;

		time.update();

		switch_time -= Gdx.graphics.getDeltaTime();
		if (switch_time < 0)
			switch_time = 0;
		if (switch_time == 0) {
			if (input.isKeyPressed(Input.Keys.RIGHT)
					|| input.isKeyPressed(Input.Keys.D)) {
				CurPos--;
				switch_time = 0.2f;
			}
			if (input.isKeyPressed(Input.Keys.LEFT)
					|| input.isKeyPressed(Input.Keys.A)) {
				CurPos++;
				switch_time = 0.2f;
			}

			if (input.isKeyPressed(Input.Keys.ENTER)) {
				check_enter(game);
				switch_time = 0.2f;
			}

			if (input.isKeyPressed(Input.Keys.ESCAPE)) {
				if (Step == 1 || Step == 2)
					Step = 0;
				if (Step == 3)
					Step = 2;
				switch_time = 0.2f;
			}
		}
		if (CurPos > menu[Step].length - 1)
			CurPos = 0;
		if (CurPos < 0)
			CurPos = menu[Step].length - 1;

		world_mask.setOrigin(world.radius, world.radius);

		if ((int) world_mask.getRotation() < (int) (-360f / (menu[Step].length) * CurPos))
			world_mask.rotate(Gdx.graphics.getDeltaTime() * 300);
		if ((int) world_mask.getRotation() > (int) (-360f / (menu[Step].length) * CurPos))
			world_mask.rotate(-Gdx.graphics.getDeltaTime() * 300);

	}

	private void check_enter(StateBasedGame game) {
		if (Step == 0) {
			if (CurPos == 0)
				Step++;
			if (CurPos == 1) {
				game.dispose();
				System.exit(1);
			}
			if (CurPos == 2)
				Step += 2;
			if (CurPos == 3)
				game.enterState(MainGame.TITLESTATE);
			return;
		}

		if (Step == 1) {
			BufferedWorld wrld = null;
			if (CurPos != 0) {
				try {
					try {
						FileInputStream fo = new FileInputStream(Gdx.files
								.local("saves/" + menu[Step][CurPos]).file());
						ObjectInputStream stream = new ObjectInputStream(fo);
						wrld = (BufferedWorld) stream.readObject();
						stream.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Vector<Thing> objects = new Vector<Thing>();
				for (int i = 0; i <= 10; i++)
					objects.add(Functions
							.createRandomThing(((MainGame) game).container));
				Player player = new Player(((MainGame) game).container, (GamePlayState) game.getState(MainGame.GAMEPLAYSTATE));
				player.inventory[0] = Functions.createItem(2, 5,
						((MainGame) game).container, 0);// DELETE IT DON'T
														// FORGET
				player.inventory[1] = Functions.createItem(4, 1,
						((MainGame) game).container, 0);
				player.inventory[2] = Functions.createItem(10, 1,
						((MainGame) game).container, 0);
				player.inventory[2] = Functions.createItem(8, 1,
						((MainGame) game).container, 0);
				wrld = new BufferedWorld(objects, new Vector<Mob>(), player,
						String.valueOf(menu[1].length - 1) + ".wrld", 12f, 0f,
						new Vector<Item>(), 0, 0);
			}
			((MainGame) game).buf = wrld;
			Step = 0;
			game.enterState(MainGame.GAMEPLAYSTATE);
			return;
		}
		if (Step == 2) {
			if (CurPos == 1)
				Gdx.graphics.setDisplayMode(Gdx.graphics.getWidth(),
						Gdx.graphics.getHeight(), !Gdx.graphics.isFullscreen());
			if (CurPos == 2) {
			}
			if (CurPos == 3)
				Step++;
			return;
		}
		if (Step == 3) {
			int w = 0, h = 0;
			if (CurPos == 0) {
				w = 800;
				h = 600;
			}
			if (CurPos == 1) {
				w = 1280;
				h = 800;
			}
			if (CurPos == 2) {
				w = 1366;
				h = 768;
			}
			if (CurPos == 3) {
				w = 1400;
				h = 900;
			}
			if (CurPos == 4) {
				w = 1680;
				h = 1050;
			}
			if (CurPos == 5) {
				w = 1920;
				h = 1080;
			}
			Gdx.graphics.setDisplayMode(w, h, Gdx.graphics.isFullscreen());
		}
		CurPos = 0;
		world_mask.setRotation(0);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
