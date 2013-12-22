package main;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitlesState extends BasicGameState {

	private int id;
	private float Scrolling = -300;
	AngelCodeFont font;
	Image background;
	
	public TitlesState(int id){
		this.id = id;
	}

	public void enter(GameContainer gc, StateBasedGame game)
			throws SlickException {
		Scrolling = -300;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame game)
			throws SlickException {
		font = new AngelCodeFont("res/font.fnt", new Image("res/font.png"));
		background = ((Main)game).container.getImage("time/night");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		background.draw(0, 0, gc.getWidth(), gc.getHeight());
		font.drawString(gc.getWidth()/2-font.getWidth("Created by YouToLife Comapny")/2, Scrolling,"Created by YouToLife Comapny");
		font.drawString(gc.getWidth()/2-font.getWidth("	Game programmer		-	OPOT")/2, Scrolling+50,"	Game programmer		-	OPOT");
		font.drawString(gc.getWidth()/2-font.getWidth("	Mob programmer		-	OPOT")/2, Scrolling+100,"	Mob programmer		-	OPOT");
		font.drawString(gc.getWidth()/2-font.getWidth("	Project manager		-	OPOT")/2, Scrolling+150,"	Mob programmer		-	OPOT");
		font.drawString(gc.getWidth()/2-font.getWidth("	Creative producer		-	OPOT")/2, Scrolling+200,"	Mob programmer		-	OPOT");
		font.drawString(gc.getWidth()/2-font.getWidth("	Game designer		-	MinerVan")/2, Scrolling+250,"	Game designer		-	MinerVan");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)||Scrolling>gc.getHeight())
			game.enterState(Main.MAINMENUSTATE);
		
		Scrolling+=(float)delta/40;
		
		if(gc.getInput().isKeyDown(Input.KEY_DOWN))
			Scrolling+=(float)delta/10;
	}

	@Override
	public int getID() {
		return id;
	}

}
