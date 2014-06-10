package com.youtolife.world.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.youtolife.world.GameState;
import com.youtolife.world.MainGame;
import com.youtolife.world.StateBasedGame;

public class TitlesState extends GameState {

	public TitlesState(int id, MainGame game) {
		super(id,game);
	}

	/**
	 */
	private float Scrolling = 600;
	/**
	 */
	BitmapFont font;
	/**
	 */
	Sprite background;

	public void enter(StateBasedGame game) {
		Scrolling = 300;
	}
	
	@Override
	public void init(StateBasedGame game){
		MainGame g = (MainGame) game;
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"),
				new TextureRegion(g.container.getImage("font")), false);
		background = new Sprite(g.container.getImage("time/night"));
		background.setSize(MainGame.w, MainGame.h);
		background.setPosition(-MainGame.w/2, -MainGame.h/2);
	}

	@Override
	public void draw(SpriteBatch batch) {
		background.draw(batch);
		font.draw(batch,"Created by YouToLife Company",-font.getBounds("Created by YouToLife Comapny").width / 2,Scrolling);
		font.draw(batch,"	Game programmer		-	OPOT",-font.getBounds("	Game programmer		-	OPOT").width / 2,Scrolling+50); 
		font.draw(batch,"	AI programmer		-	OPOT",-font.getBounds("	Mob programmer		-	OPOT").width / 2,Scrolling+100); 
		font.draw(batch,"	Project manager		-	OPOT",-font.getBounds("	Project manager		-	OPOT").width / 2,Scrolling+150); 
		font.draw(batch,"	Creative producer		-	OPOT",-font.getBounds("	Creative producer		-	OPOT").width / 2,Scrolling+200);
		font.draw(batch,"	Game designer		-	MinerVan",-font.getBounds("	Game designer		-	MinerVan").width / 2,Scrolling+250);
	}                                                        

	@Override
	public void update(StateBasedGame game){
		float delta = Gdx.graphics.getDeltaTime()*10;
		Input input = Gdx.input;
		if(input.isKeyPressed(Input.Keys.ESCAPE)||Scrolling<-MainGame.h)
			game.enterState(MainGame.MAINMENUSTATE);
		
		Scrolling-=delta;
		
		if(input.isKeyPressed(Input.Keys.DOWN)||input.isTouched())
			Scrolling-=delta*5;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
