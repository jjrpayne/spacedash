package com.gamehut.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gamehut.gameobjects.Pipe;
import com.gamehut.gameobjects.ScrollHandler;
import com.gamehut.gameobjects.Scrollable;
import com.gamehut.gameobjects.Ship;
import com.gamehut.gameobjects.Wall;
import com.gamehut.sdhelpers.AssetLoader;
import com.gamehut.sdhelpers.InputHandler;
import com.gamehut.ui.SimpleButton;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private SpriteBatch batcher;
	
	private float midPointX;
	private float gameWidth;
	private float gameHeight;
	private float gameTime;
	
	// Game Objects
	private Ship ship;
	private ScrollHandler scroller;
	private Scrollable wall1, wall2, wall3, wall4, wall5, wall6;
	private Pipe pipe1, pipe2, pipe3;
	private Scrollable bg1, bg2, bg3;
	
	// Game Assets
	private TextureRegion bg, wallRight, wallLeft, spaceDashLogo;
	private Animation explosionAnimation, shipAnimation;
	private TextureRegion pillarTopRight, pillarTopLeft, pipe;
	private TextureRegion arrowLeft, arrowRight;

	private SimpleButton muteButton = ((InputHandler) Gdx.input.getInputProcessor()).getMuteButton();
	private SimpleButton exitButton = ((InputHandler) Gdx.input.getInputProcessor()).getExitButton();

	public GameRenderer(GameWorld world, float gameHeight, float gameWidth, float midPointX){
			myWorld = world;
			gameTime = 0;
			
			this.gameHeight = gameHeight;
			this.midPointX = midPointX;
			this.gameWidth = gameWidth;

			cam = new OrthographicCamera();
			cam.setToOrtho(true, 136, gameHeight);
			
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
		wall1 = scroller.getWall1();
		wall2 = scroller.getWall2();
		wall3 = scroller.getWall3();
		wall4 = scroller.getWall4();
		wall5 = scroller.getWall5();
		wall6 = scroller.getWall6();
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
		spaceDashLogo = AssetLoader.spaceDashLogo;
		shipAnimation = AssetLoader.ship;
		pillarTopRight = AssetLoader.pillarTopRight;
		pillarTopLeft = AssetLoader.pillarTopLeft;
		pipe = AssetLoader.pipe;
		arrowLeft = AssetLoader.arrowLeft;
		arrowRight = AssetLoader.arrowRight;
	}
	
	private void drawWalls(){
		batcher.draw(wallLeft, wall1.getX(), wall1.getY(),
				wall1.getWidth(), wall1.getHeight());
		batcher.draw(wallLeft, wall2.getX(), wall2.getY(),
				wall2.getWidth(), wall2.getHeight());
		batcher.draw(wallLeft, wall3.getX(), wall3.getY(),
				wall3.getWidth(), wall3.getHeight());
		batcher.draw(wallRight, wall4.getX(), wall4.getY(),
				wall4.getWidth(), wall4.getHeight());
		batcher.draw(wallRight, wall5.getX(), wall5.getY(),
				wall5.getWidth(), wall5.getHeight());
		batcher.draw(wallRight, wall6.getX(), wall6.getY(),
				wall6.getWidth(), wall6.getHeight());

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
	
	public void render(float runTime, float animationTime) {
		
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
        if(ship.isAlive())
        	batcher.draw((TextureRegion)shipAnimation.getKeyFrame(runTime), ship.getX(), ship.getY(), 16, 24);
        if(!ship.isAlive() && !explosionAnimation.isAnimationFinished(animationTime)){
        	batcher.draw((TextureRegion) explosionAnimation.getKeyFrame(animationTime), ship.getX()-ship.getWidth()/2, ship.getY()-ship.getHeight()/2,
        			2*ship.getWidth(), 2*ship.getHeight());
        	animationTime += Gdx.graphics.getDeltaTime();
        }
        
        

        if (myWorld.isReady()) {
        	AssetLoader.dosfont.draw(batcher, "Tap anywhere\n to start!", (gameWidth/2)-
					(8f*12f)/2f, gameHeight - 75);
        	batcher.draw(spaceDashLogo, midPointX - (109/2), 20, 109, 86);
			muteButton.draw(batcher);
			exitButton.draw(batcher);
        } else {
        	if(myWorld.isGameOver() || myWorld.isHighScore()){
        		if(myWorld.isGameOver()){
        			AssetLoader.dosfont.draw(batcher, "Game Over :(", (gameWidth/2)-((12*8)/2), (gameHeight/2)-4);
        			String highScore ="High Score: " + AssetLoader.getHighScore();
        			AssetLoader.dosfont.draw(batcher, highScore, (gameWidth/2)-((highScore.length()*8)/2), (gameHeight/2)+4);
        		} else {
        			AssetLoader.dosfont.draw(batcher, "High Score!", (gameWidth/2)-(8*11)/2, (gameHeight/2)-4);
        		}
        	}
        	if(gameTime < 2){
        		gameTime += Gdx.graphics.getDeltaTime();
        		if(AssetLoader.getHighScore() == 0) {
					AssetLoader.dosfont.draw(batcher, "Tilt!", (gameWidth / 2) - (5 * 8 / 2), gameHeight - 75);
					batcher.draw(arrowLeft, (gameWidth / 2) - (5 * 8 / 2) - 10, gameHeight - 75, 10, 10);
					batcher.draw(arrowRight, (gameWidth / 2) + (5 * 8 / 2), gameHeight - 75, 10, 10);
				}
			}
        	// convert integer into String
        	String score = myWorld.getScore()+"";
        
        	// draw text
       		AssetLoader.dosfont.draw(batcher, score, gameWidth - 2-8*score.length(), 2);

        }

        batcher.end();
		/* test bounding circle
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(ship.getBoundingCircle().x,
				ship.getBoundingCircle().y, ship.getBoundingCircle().radius);
		shapeRenderer.end();
		*/


	}

}
