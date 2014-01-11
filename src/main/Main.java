package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	public static final int GAMEPLAYSTATE = 1;
	public static final int MAINMENUSTATE = 2;
	public static final int ENDOFGAMESTATE = 3;
	public static final int TITLESTATE = 4;
	public static final int CRAFTMENUSTATE = 5;
	public static final int CONTROLLERSETUPSTATE = 6;

	public BufferedWorld buf;
	public ImageContainer container;
	AppGameContainer app;
	
	public Main() {
		super("NoName");
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.addState(new EndOfGameState(ENDOFGAMESTATE));
		this.addState(new TitlesState(TITLESTATE));
		this.addState(new CraftMenuState(CRAFTMENUSTATE));
		this.addState(new ControllerSetupState(CONTROLLERSETUPSTATE));
		this.enterState(MAINMENUSTATE);
	}

	public static void main(String[] args) throws SlickException {
		
		Main game = new Main();
		
		game.app = new AppGameContainer(game);

		game.app.setAlwaysRender(true);
		game.app.setMultiSample(1);
		game.app.setVSync(true);
		game.app.setDisplayMode(800, 600, false);
		game.app.setMusicOn(false);
		game.app.start();
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		container = new ImageContainer();
		this.getState(GAMEPLAYSTATE).init(gc, this);
		this.getState(MAINMENUSTATE).init(gc, this);
	}

}
