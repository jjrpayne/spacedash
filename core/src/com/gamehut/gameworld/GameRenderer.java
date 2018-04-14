package com.gamehut.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gamehut.gameobjects.Ship;
import com.gamehut.sdhelpers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch batcher;
	
	private int midPointY;
	private int gameHeight;
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY){
			myWorld = world;
			
			this.gameHeight = gameHeight;
			this.midPointY = midPointY;

			cam = new OrthographicCamera();
			cam.setToOrtho(true, 136, 204);
			
			batcher = new SpriteBatch();
			// Attach batcher to camera
			batcher.setProjectionMatrix(cam.combined);
			
			shapeRenderer = new ShapeRenderer();
			shapeRenderer.setProjectionMatrix(cam.combined);
	}
	
	public void render(float runTime) {
		
		// We will move these outside of the loop for performance later.
		Ship ship = myWorld.getShip();

		// Fill the entire screen with black to prevent flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency for performance
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, 0, 136, 144);
        batcher.draw(AssetLoader.bg, 0, 144, 136, 144);
        
        // Enable transparency to draw ship
        batcher.enableBlending();
        batcher.draw(AssetLoader.ship, ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
        
        batcher.end();
	}

}
