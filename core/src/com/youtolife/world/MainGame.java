package com.youtolife.world;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.youtolife.world.states.CraftMenuState;
import com.youtolife.world.states.EndOfGameState;
import com.youtolife.world.states.GamePlayState;
import com.youtolife.world.states.MainMenuState;
import com.youtolife.world.states.TitlesState;

public class MainGame extends StateBasedGame implements ApplicationListener {
	/**
	 */
	public OrthographicCamera camera;
	/**
	 */
	private SpriteBatch batch;
	
	/**
	 */
	public ImageContainer container;
	public static final int GAMEPLAYSTATE = 1;
	public static final int MAINMENUSTATE = 2;
	public static final int ENDOFGAMESTATE = 3;
	public static final int TITLESTATE = 4;
	public static final int CRAFTMENUSTATE = 5;
	public static float w = 800;
	public static float h = 600;
	
	/**
	 */
	public BufferedWorld buf;
	/**
	 */
	BitmapFont font;
	
	@Override
	public void create() {	
		Gdx.input.setInputProcessor(new GestureDetector(new TouchListener(this)));
		Gdx.graphics.setVSync(true);
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		container = new ImageContainer();
		
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"),
				new TextureRegion(container.getImage("font")), false);
		
		this.addState(new MainMenuState(MAINMENUSTATE, this));
		this.addState(new GamePlayState(GAMEPLAYSTATE, this));
		this.addState(new EndOfGameState(ENDOFGAMESTATE, this));
		this.addState(new TitlesState(TITLESTATE, this));
		this.addState(new CraftMenuState(CRAFTMENUSTATE, this));
		this.enterState(MAINMENUSTATE);
		this.init();
	}

	@Override
	public void dispose() {
		container.dispose();
		font.dispose();
		batch.dispose();
	}

	@Override
	public void render() {
		super.update();
		local_update();
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		this.render(batch);
		font.setScale(0.5f);
		font.draw(batch, "FPS: "+String.valueOf(Gdx.graphics.getFramesPerSecond()), -w/2+5, h/2-5);
		font.setScale(1);
		batch.end();
	}

	private void local_update(){
		if(Gdx.input.isKeyPressed(Input.Keys.F5))
			Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, !Gdx.graphics.isFullscreen());
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
