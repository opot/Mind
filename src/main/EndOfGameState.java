package main;

import java.io.File;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndOfGameState extends BasicGameState {

	Image img = null;
	Music music = null;
	AngelCodeFont font;
	private int id;

	public EndOfGameState(int id) {
		this.id = id;
	}

	public void enter(GameContainer gc, StateBasedGame game)
			throws SlickException {
		super.enter(gc, game);
		music.play();
		Main main = (Main) game;
		File f = new File("res/Saves/" + main.buf.name);
		System.out.println("res/Saves/" + main.buf.name);
		if(f.exists())
			f.delete();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		img = ((Main) game).container.getImage("end");
		music = ((Main) game).container.end;
		font = new AngelCodeFont("res/font.fnt", new Image("res/font.png"));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics arg2)
			throws SlickException {
		Main main = (Main) game;
		img.draw(0, 0, gc.getWidth(), gc.getHeight());
		font.drawString(
				gc.getWidth()
						/ 2
						- font.getWidth("Your Score is "
								+ String.valueOf(main.buf.Score))/2,
				(float) (gc.getHeight()*0.75),
				"You Score is " + String.valueOf(main.buf.Score));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int arg2)
			throws SlickException {
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			music.stop();
			game.enterState(Main.MAINMENUSTATE);
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
