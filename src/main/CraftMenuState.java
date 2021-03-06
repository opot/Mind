package main;

import java.util.Vector;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CraftMenuState extends BasicGameState {

	private int id;
	Image craftPlace, inv, craftBut;
	Image cur;
	Item[][] player = new Item[5][5];
	Vector<Item> workbench = new Vector<Item>();
	Item inHand = null;

	Rectangle rect[][] = new Rectangle[5][5];

	Polygon mouse = new Polygon();
	Rectangle craft = new Rectangle(64, 33, 284, 331);
	Rectangle make = new Rectangle(75, 433, 283, 167);

	int CurX = 0, CurY = 0;
	int sx = 40, sy = 54;

	public CraftMenuState(int id) {
		this.id = id;
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame game)
			throws SlickException {
		super.enter(gc, game);

		BufferedWorld wrld = ((Main) game).buf;
		ImageContainer container = ((Main) game).container;
		workbench = new Vector<Item>();

		player = new Item[5][5];
		for (int i = 0; i <= 24; i++)
			if (wrld.playerInventory[i][0] != null)
				player[i % 5][i / 5] = Functions.createItem(
						wrld.playerInventory[i][0], wrld.playerInventory[i][1],
						container, 0);

		mouse.addPoint(0, 0);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		craftBut = ((Main) game).container.getImage("gui/craftButton");
		inv = ((Main) game).container.getImage("gui/inv");
		craftPlace = ((Main) game).container.getImage("gui/craftPlace");
		cur = ((Main) game).container.getImage("gui/curitem");

		gc.setMouseCursor(((Main) game).container.getImage("cursor"), 0, 0);

		craft = new Rectangle(50, 55, craftPlace.getWidth() - 50,
				craftPlace.getHeight() - 50);
		make = new Rectangle(50, gc.getHeight() - craftBut.getHeight(),
				craftBut.getWidth(), craftBut.getHeight());

		sx += 25 + craftPlace.getWidth() + 30;
		sy += 30;

		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				rect[i][j] = new Rectangle(52 * i + sx, 52 * j + sy, 40, 40);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(new Color(58, 39, 34));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

		craftPlace.draw(25, 25);
		inv.draw(25 + craftPlace.getWidth() + 30, 30);
		craftBut.draw(50, 25 + craftPlace.getHeight() + 20);

		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				if (player[i][j] != null) {
					player[i][j].img.draw(sx + 52 * i - 5, sy + 52 * j - 5);
					g.setColor(Color.white);
					g.drawString(String.valueOf(player[i][j].Stack), sx + 52
							* i + 30, sy + 52 * j + 29);
				}

		for (Item item : workbench)
			item.draw(g, item.x, item.y);
		cur.draw(rect[CurX][CurY].getX() - 5, rect[CurX][CurY].getY() - 5);

		/**
		 * g.fill(craft); g.fill(make); for (int i = 0; i <= 4; i++) for (int j
		 * = 0; j <= 4; j++) g.fill(rect[i][j]);
		 **/

		if (inHand != null)
			inHand.draw(g, inHand.x, inHand.y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)
				|| input.isKeyPressed(Input.KEY_I))
			exit(gc,game,delta);

		if(input.isKeyPressed(Input.KEY_SPACE))
			workTInv();
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (make.contains(mouse))
				workbench = Functions.craft(workbench, ((Main) game).container);
		}
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int x, int y) {
		if (inHand != null) {
			inHand.x = x - inHand.img.getWidth() / 2;
			inHand.y = y - inHand.img.getHeight() / 2;
		}

		mouse = new Polygon();
		mouse.addPoint(x, y);
		mouse.addPoint(x + 1, y);
		mouse.addPoint(x + 1, y + 1);
		mouse.addPoint(x, y + 1);

		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				if (rect[i][j].contains(mouse)) {
					CurX = i;
					CurY = j;
					return;
				}

	}

	@Override
	public void mouseDragged(int arg0, int arg1, int x, int y) {
		mouseMoved(arg0, arg1, x, y);
	}

	@Override
	public void mousePressed(int button, int x, int y) {

		if (button == Input.MOUSE_LEFT_BUTTON) {
			mouse = new Polygon();
			mouse.addPoint(x, y);
			mouse.addPoint(x + 1, y);
			mouse.addPoint(x + 1, y + 1);
			mouse.addPoint(x, y + 1);
			if (inHand == null) {
				if (rect[CurX][CurY].contains(mouse)
						&& player[CurX][CurY] != null) {
					inHand = player[CurX][CurY];
					inHand.x = (int) (x - inHand.width / 2);
					inHand.y = (int) (y - inHand.height / 2);
					player[CurX][CurY] = null;
					return;
				}

				for (int i = 0; i < workbench.size(); i++)
					if (workbench.get(i).rect.intersects(mouse)
							|| workbench.get(i).rect.contains(mouse)) {
						inHand = workbench.get(i);
						workbench.remove(i);
					}
			} else {
				if (craft.contains(mouse)) {
					inHand.rect = new Polygon();
					inHand.rect.addPoint(inHand.x, inHand.y);
					inHand.rect.addPoint(inHand.x + 50, inHand.y);
					inHand.rect.addPoint(inHand.x + 50, inHand.y + 50);
					inHand.rect.addPoint(inHand.x, inHand.y + 50);
					workbench.add(inHand);
					inHand = null;
				}

				if (player[CurX][CurY] != null) {
					if (inHand.id == player[CurX][CurY].id) {
						player[CurX][CurY].Stack += inHand.Stack;
						inHand = null;
						return;
					} else {
						Item buf = inHand;
						inHand = player[CurX][CurY];
						player[CurX][CurY] = buf;
						inHand.x = x;
						inHand.y = y;
						return;
					}
				} else {
					player[CurX][CurY] = inHand;
					inHand = null;
					return;
				}
			}
		}
	}

	private void exit(GameContainer gc, StateBasedGame game, int delta){
		workbench.add(inHand);
		inHand = null;
		workTInv();
		
		((Main) game).buf.playerInventory = new Integer[25][2];
		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				if (player[j][i] != null) {
					((Main) game).buf.playerInventory[j + i * 5][0] = player[j][i].id;
					((Main) game).buf.playerInventory[j + i * 5][1] = player[j][i].Stack;
				}
		game.enterState(Main.GAMEPLAYSTATE);
	}
	
	private void workTInv(){
		for (int i = 0; i <= 4 && !workbench.isEmpty(); i++)
			for (int j = 0; j <= 4 && !workbench.isEmpty(); j++)
				if (player[j][i] == null) {
					player[j][i] = workbench.get(workbench.size() - 1);
					workbench.remove(workbench.size() - 1);
				}
	}
	
	@Override
	public int getID() {
		return id;
	}

}
