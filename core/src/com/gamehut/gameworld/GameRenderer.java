package com.gamehut.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gamehut.gameobjects.Pipe;
import com.gamehut.gameobjects.ScrollHandler;
import com.gamehut.gameobjects.Scrollable;
import com.gamehut.gameobjects.Ship;
import com.gamehut.gameobjects.Wall;
import com.gamehut.sdhelpers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch batcher;
	
	private int midPointX;
	private int gameWidth;
	private int gameHeight;
	
	// Game Objects
	private Ship ship;
	private ScrollHandler scroller;
	private Wall topWallLeft, topWallRight, bottomWallLeft, bottomWallRight;
	private Pipe pipe1, pipe2, pipe3;
	private Scrollable bg1, bg2, bg3;
	
	// Game Assets
	private TextureRegion bg, wallRight, wallLeft;
	private Animation explosionAnimation;
	private TextureRegion shipTexture, explosion1, explosion2, explosion3, explosion4, explosion5;
	private TextureRegion pillarTopRight, pillarTopLeft, pipe;
	
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointX){
			myWorld = world;
			
			this.gameHeight = gameHeight;
			this.midPointX = midPointX;
			this.gameWidth = 2*midPointX;

			cam = new OrthographicCamera();
			cam.setToOrtho(true, 136, 204);
			
			batcher = new SpriteBatch();
			// Attach batcher to camera
			batcher.setProjectionMatrix(cam.combined);
			
			shapeRenderer = new ShapeRenderer();
			shapeRenderer.setProjectionMatrix(cam.combined);
			
			// Call helper methods to initialize instance variables
			initGameObjects();
			initAssets();
	}
	
	private void initGameObjects(){
		ship = myWorld.getShip();
		scroller = myWorld.getScroller();
		topWallLeft = scroller.getTopWallLeft();
		topWallRight = scroller.getTopWallRight();
		bottomWallLeft = scroller.getBottomWallLeft();
		bottomWallRight = scroller.getBottomWallRight();
		pipe1 = scroller.getPipe1();
		pipe2 = scroller.getPipe2();
		pipe3 = scroller.getPipe3();
		bg1 = scroller.getBg1();
		bg2 = scroller.getBg2();
		bg3 = scroller.getBg3();
	}
	
	private void initAssets(){
		bg = AssetLoader.bg;
		wallLeft = AssetLoader.wallLeft;
		wallRight = AssetLoader.wallRight;
		explosionAnimation = AssetLoader.explosionAnimation;
		shipTexture = AssetLoader.ship;
		explosion1 = AssetLoader.explosion1;
		explosion2 = AssetLoader.explosion2;
		explosion3 = AssetLoader.explosion3;
		explosion4 = AssetLoader.explosion4;
		explosion5 = AssetLoader.explosion5;
		pillarTopRight = AssetLoader.pillarTopRight;
		pillarTopLeft = AssetLoader.pillarTopLeft;
		pipe = AssetLoader.pipe;
	}
	
	private void drawWalls(){
		batcher.draw(wallLeft, topWallLeft.getX(), topWallLeft.getY(),
				topWallLeft.getWidth(), topWallLeft.getHeight());
		batcher.draw(wallLeft, bottomWallLeft.getX(), bottomWallLeft.getY(),
				bottomWallLeft.getWidth(), bottomWallLeft.getHeight());
		batcher.draw(wallRight, topWallRight.getX(), topWallRight.getY(),
				topWallRight.getWidth(), topWallRight.getHeight());
		batcher.draw(wallRight, bottomWallRight.getX(), bottomWallRight.getY(),
				bottomWallRight.getWidth(), bottomWallRight.getHeight());

	}
	
	private void drawPipes(){
		// Temporary code
		batcher.draw(pipe, pipe1.getX(), pipe1.getY(), pipe1.getWidth(), pipe1.getHeight());
		batcher.draw(pipe, pipe1.getX() + pipe1.getWidth() + 45, pipe1.getY(), 
				gameWidth - (pipe1.getWidth() + 45), pipe1.getHeight());
		batcher.draw(pipe, pipe2.getX(), pipe2.getY(), pipe2.getWidth(), pipe2.getHeight());
		batcher.draw(pipe, pipe2.getX() + pipe2.getWidth() + 45, pipe2.getY(), 
				gameWidth - (pipe2.getWidth() + 45), pipe2.getHeight());
		batcher.draw(pipe, pipe3.getX(), pipe3.getY(), pipe3.getWidth(), pipe3.getHeight());
		batcher.draw(pipe, pipe3.getX() + pipe3.getWidth() + 45, pipe3.getY(), 
				gameWidth - (pipe3.getWidth() + 45), pipe3.getHeight());

	}
	
	private void drawSkulls(){
		batcher.draw(pillarTopLeft, pipe1.getX()+pipe1.getWidth(), pipe1.getY()-1, 14, 24);
		batcher.draw(pillarTopRight, pipe1.getX()+pipe1.getWidth()+45, pipe1.getY()-1, 14, 24);
		batcher.draw(pillarTopLeft, pipe2.getX()+pipe2.getWidth(), pipe2.getY()-1, 14, 24);
		batcher.draw(pillarTopRight, pipe2.getX()+pipe2.getWidth()+45, pipe2.getY()-1, 14, 24);
		batcher.draw(pillarTopLeft, pipe3.getX()+pipe3.getWidth(), pipe3.getY()-1, 14, 24);
		batcher.draw(pillarTopRight, pipe3.getX()+pipe3.getWidth()+45, pipe3.getY()-1, 14, 24);
	}
	
	public void render(float runTime) {
		
		// Fill the entire screen with black to prevent flickering
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Begin SpriteBatch
        batcher.begin();
        // Disable transparency for performance
        batcher.disableBlending();
        
        // Draw background
        batcher.draw(bg, bg1.getX(), bg1.getY(), bg1.getWidth(), bg1.getHeight());
        batcher.draw(bg, bg2.getX(), bg2.getY(), bg2.getWidth(), bg2.getHeight());
        batcher.draw(bg, bg3.getX(), bg3.getY(), bg3.getWidth(), bg3.getHeight());
        
        // Enable transparency to draw ship
        batcher.enableBlending();
        
        // 1 Draw Pipes
        drawPipes();
        
        // 2 Draw Skulls
        drawSkulls();
        
        // 3 Draw Walls
        drawWalls();
        
        // 4 Draw Ship
        batcher.draw(shipTexture, ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
        batcher.end();
	}

}
