package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

	private int StateID = -1;
	Image world_mask;
	String[][] menu = new String[4][1];
	Thing[][] objects;
	int Step = 0;
	int CurPos = 0;
	AngelCodeFont font;
	Circle world;
	TimeMask time;

	public MainMenuState(int StateID) {
		this.StateID = StateID;
	}

    @Override
	public void enter(GameContainer gc, StateBasedGame game)
			throws SlickException {
		super.enter(gc, game);
		Step = 0;
		CurPos = 0;
		menu[0] = new String[4];
		menu[0][0] = "Play";
		menu[0][1] = "Exit";
		menu[0][2] = "Settings";
		menu[0][3] = "About";
		menu[1] = new File("res/Saves").list();

		String[] buf = new String[menu[1].length + 1];
		buf[0] = "New World";
		for (int i = 1; i <= menu[1].length; i++)
			buf[i] = menu[1][i - 1];
		menu[1] = buf;
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
				objects[i][j] = functions.createRandomThing(((Main)game).container);
		}
		world = new Circle(gc.getWidth() / 2,  gc.getHeight() / 2,
				(float) (gc.getHeight() * 0.3));
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {

		world_mask = ((Main) game).container.getImage("world");
		font = new AngelCodeFont("res/font.fnt", new Image("res/font.png"));
		time = new TimeMask(((Main) game).container);
		time.time_speed = time.time_speed / 20;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {

		time.render(g, gc);

		world.setRadius((float) ((menu[Step].length * 200)/2/Math.PI));
		world.setX(gc.getWidth() / 2 - world.radius);
		world.setY(gc.getHeight() / 2);
		world_mask.setCenterOfRotation(world.radius, world.radius);
		for (int i = 0; i <= menu[Step].length - 1; i++) {
			Image o = objects[Step][i].img;
			o.setCenterOfRotation(o.getWidth()/2, o.getHeight());
			o.setRotation(world_mask.getRotation() + 360f / (menu[Step].length)
					* i);
			o.draw((float) (world.getCenterX() - o.getWidth()/2 + (world.radius - 5)
					* Math.sin(Math.toRadians(o.getRotation()))),
					(float) (world.getCenterY() - o.getHeight() - (world.radius - 5)
							* Math.cos(Math.toRadians(o.getRotation()))),
					o.getWidth(), o.getHeight());
		}
		world_mask.draw(world.getX(), world.getY(), world.radius * 2,
				world.radius * 2);
		font.drawString(gc.getWidth() / 2 - font.getWidth(menu[Step][CurPos])
				/ 2, gc.getHeight() / 2 - 16, menu[Step][CurPos]);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();

		time.update(delta);

		if (input.isKeyPressed(Input.KEY_RIGHT))
			CurPos++;
		if (input.isKeyPressed(Input.KEY_LEFT))
			CurPos--;

		if (input.isKeyPressed(Input.KEY_ENTER))
			check_enter(game);

		if (input.isKeyPressed(Input.KEY_ESCAPE)){
			if(Step == 1||Step == 2)
				Step = 0;
			if(Step == 3)
				Step = 2;
		}

		if (CurPos > menu[Step].length - 1)
			CurPos = 0;
		if (CurPos < 0)
			CurPos = menu[Step].length - 1;

		world_mask.setCenterOfRotation(world.radius, world.radius);

		if ((int) world_mask.getRotation() < (int) (-360f / (menu[Step].length) * CurPos))
			world_mask.rotate(delta / 2);
		if ((int) world_mask.getRotation() > (int) (-360f / (menu[Step].length) * CurPos))
			world_mask.rotate(-delta / 2);

	}

	private void check_enter(StateBasedGame game) throws SlickException {
		if (Step == 0) {
			if (CurPos == 0)
				Step++;
			if (CurPos == 1)
				System.exit(1);
			if (CurPos == 2)
				Step += 2;
			if(CurPos == 3)
				game.enterState(Main.TITLESTATE);
			return;
		}

		if (Step == 1) {
			BufferedWorld wrld = null;
			if (CurPos != 0) {
				try {
					FileInputStream fo = new FileInputStream("res/Saves/"
							+ menu[Step][CurPos]);
					ObjectInputStream stream = new ObjectInputStream(fo);
					wrld = (BufferedWorld) stream.readObject();
					stream.close();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				Vector<Thing> objects = new Vector<Thing>();
				for (int i = 0; i <= 10; i++)
					objects.add(functions.createRandomThing(((Main)game).container));
				Player player = new Player(100, 100,
						((Main) game).container);
				player.inventory[0] = functions.createItem(2, 5, ((Main) game).container, 0);//DELETE IT DONT FORGET
				player.inventory[1] = functions.createItem(4, 1, ((Main) game).container, 0);
				wrld = new BufferedWorld(objects,new Vector<Mob>(), player,
						String.valueOf(menu[1].length - 1) + ".wrld", 12f, 0f,
						new Vector<Item>(),0,0);
			}
			((Main) game).buf = wrld;
			Step = 0;
			game.enterState(Main.GAMEPLAYSTATE);
			return;
		}
		if (Step == 2) {
			if (CurPos == 1)
				game.getContainer().setFullscreen(
						!game.getContainer().isFullscreen());
			if (CurPos == 2)
				game.getContainer()
						.setMusicOn(!game.getContainer().isMusicOn());
			if(CurPos == 3)
				Step++;
			return;
		}
		if(Step == 3){
			int w = 0,h = 0;
			if(CurPos == 0){
				w = 800;
				h = 600;
			}
			if(CurPos == 1){
				w = 1280;
				h = 800;
			}
			if(CurPos == 2){
				w = 1366;
				h = 768;
			}
			if(CurPos == 3){
				w = 1400;
				h = 900;
			}
			if(CurPos == 4){
				w = 1680;
				h = 1050;
			}
			if(CurPos == 5){
				w = 1920;
				h = 1080;
			}
			((Main)game).app.setDisplayMode(w, h, ((Main)game).app.isFullscreen());
		}
		CurPos = 0;
		world_mask.setRotation(0);
	}

	@Override
	public int getID() {
		return StateID;
	}

}
