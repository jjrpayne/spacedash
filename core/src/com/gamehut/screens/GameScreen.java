package com.gamehut.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gamehut.gameworld.GameRenderer;
import com.gamehut.gameworld.GameWorld;
import com.gamehut.sdhelpers.InputHandler;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;
	private float animationTime = 0;
	
	public GameScreen(){
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		
		int midPointX = (int) (gameWidth / 2);

		world = new GameWorld(midPointX, gameHeight, gameWidth); // initialize world
        Gdx.input.setInputProcessor(new InputHandler(world, (screenWidth/gameWidth),
                (screenHeight/gameHeight), gameWidth, gameHeight));
        renderer = new GameRenderer(world, gameHeight, gameWidth, midPointX); // initialize renderer
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
		
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta); // GameWorld updates
		if(world.isReady())
			animationTime = 0;
		if(!world.getShip().isAlive())
			animationTime += delta;
		renderer.render(runTime, animationTime); // GameRenderer renders
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
		
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
		
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
		
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");
		
	}

	@Override
	public void dispose() {
		// Leave blank
		
	}

}
