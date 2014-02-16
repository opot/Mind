package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ControllerSetupState extends BasicGameState {

	int StateID;
	
	public ControllerSetupState(int StateID){
		this.StateID = StateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		System.out.print(button);
		System.out.println();
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame game, int arg2)
			throws SlickException {
	}

	@Override
	public int getID() {
		return StateID;
	}

}
