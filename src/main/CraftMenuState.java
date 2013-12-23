package main;

import java.util.Vector;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CraftMenuState extends BasicGameState {

	private int id;
	Image background;
	Item[][] player = new Item[5][5];
	Vector<Item> workbench = new Vector<Item>();
	Item inHand = null;

	Polygon mouse = new Polygon();
    Rectangle craft = new Rectangle(64,33,284,331);

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
				player[i % 5][i / 5] = functions.createItem(
						wrld.playerInventory[i][0], wrld.playerInventory[i][1],
						container, 0);

		mouse.addPoint(0, 0);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		background = ((Main) game).container.getImage("inventory");
		gc.setMouseCursor(((Main) game).container.getImage("cursor"), 0, 0);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(new Color(58, 39, 34));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		background.draw(gc.getWidth() / 1 - 800, gc.getHeight() / 1 - 600);

		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				if (player[i][j] != null) {
					player[i][j].img.draw(435 + 52 * i, 50 + 52 * j);
					g.setColor(Color.white);
					g.drawString(String.valueOf(player[i][j].Stack),
							435 + 52 * i, 80 + 52 * j);
				}
        for(Item item:workbench)
            item.draw(g,item.x,item.y);

        if (inHand != null)
                inHand.draw(g, inHand.x, inHand.y);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ESCAPE)
				|| input.isKeyPressed(Input.KEY_I)) {
			((Main) game).buf.playerInventory = new Integer[25][2];
			for (int i = 0; i <= 4; i++)
				for (int j = 0; j <= 4; j++)
					if (player[j][i] != null) {
						((Main) game).buf.playerInventory[j + i * 5][0] = player[j][i].id;
						((Main) game).buf.playerInventory[j + i * 5][1] = player[j][i].Stack;
					}
			game.enterState(Main.GAMEPLAYSTATE);
		}

		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				if (player[i][j] != null) {
					player[i][j].rect = new Polygon();
					player[i][j].rect.addPoint(435 + 52 * i, 50 + 52 * j);
					player[i][j].rect.addPoint(485 + 52 * i, 50 + 52 * j);
					player[i][j].rect.addPoint(485 + 52 * i, 100 + 52 * j);
					player[i][j].rect.addPoint(435 + 52 * i, 100 + 52 * j);
				}
	}

    @Override
	public void mouseMoved(int arg0, int arg1, int x, int y) {
		if (inHand != null) {
			inHand.x = x- inHand.img.getWidth() / 2;
			inHand.y = y- inHand.img.getHeight() / 2;
		}
	}
    @Override
	public void mouseDragged(int arg0, int arg1, int x, int y) {
		mouseMoved(arg0,arg1,x,y);
	}

	@Override
	public void mousePressed(int button, int x, int y) {

		if (button == Input.MOUSE_LEFT_BUTTON) {
			if (inHand == null) {
				mouse = new Polygon();
				mouse.addPoint(x, y);
				mouse.addPoint(x + 1, y);
				mouse.addPoint(x + 1, y + 1);
				mouse.addPoint(x, y + 1);
				for (int i = 0; i <= 4; i++)
					for (int j = 0; j <= 4; j++)
						if (player[i][j] != null)
							if (player[i][j].rect.contains(mouse)) {
								inHand = player[i][j];
								inHand.x = x;
								inHand.y = y;
								player[i][j] = null;
								return;
							}
                for(int i = 0; i<workbench.size();i++)
                    if(workbench.get(i).rect.intersects(mouse)||workbench.get(i).rect.contains(mouse)){
                        inHand = workbench.get(i);
                        workbench.remove(i);
                    }
			} else {
				if (inHand != null) {
					inHand.rect = new Polygon();
					inHand.rect.addPoint(x - 20, y - 20);
					inHand.rect.addPoint(x + 20, y - 20);
					inHand.rect.addPoint(x + 20, y + 20);
					inHand.rect.addPoint(x - 20, y + 20);
                    if(craft.contains(inHand.rect)){
                        workbench.add(inHand);
                        inHand = null;

                    }else
					for (int i = 0; i <= 4; i++)
						for (int j = 0; j <= 4; j++)
							if (player[j][i] != null)
								if (inHand.rect.intersects(player[j][i].rect)
										|| inHand.rect
												.contains(player[j][i].rect)) {
									Item buf = inHand;
									inHand = player[j][i];
									player[j][i] = buf;
                                    inHand.x = x;
                                    inHand.y = y;
									return;
								}
					for (int i = 0; i <= 4; i++)
						for (int j = 0; j <= 4; j++)
							if (player[j][i] == null) {
								player[j][i] = inHand;
								inHand = null;
								return;
							}
				}
			}
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
