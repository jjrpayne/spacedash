package com.gamehut.gameobjects;

import com.gamehut.gameworld.GameWorld;
import com.gamehut.sdhelpers.AssetLoader;

public class ScrollHandler {
	
    // ScrollHandler will create all five objects that we need.
    private Scrollable wall1, wall2, wall3, wall4, wall5, wall6;
    private Pipe pipe1, pipe2, pipe3;
    private Scrollable bg1, bg2, bg3;
    private GameWorld gameWorld;

    // ScrollHandler will use the constants below to determine
    // how fast we need to scroll and also determine
    // the size of the gap between each pair of pipes.

    // Capital letters are used by convention when naming constants.
    public static final int SCROLL_SPEED = 180;
    public static final int BG_SPEED = 18;
    public static final int PIPE_GAP = 85;

    // Constructor receives a float that tells us where we need to create our
    // Wall and Pipe objects.
    public ScrollHandler(float xPos1, float xPos2, float gameHeight, float gameWidth, GameWorld gameWorld) {
    	wall1 = new Scrollable(xPos1, gameHeight-210, 10, 210, SCROLL_SPEED, gameHeight);
    	wall2 = new Scrollable(xPos1, wall1.getTailY(), 10, 210, SCROLL_SPEED, gameHeight);
    	wall3 = new Scrollable(xPos1, wall2.getTailY(),10, 210, SCROLL_SPEED,gameHeight);
    	wall4 = new Scrollable(xPos2, gameHeight-210, 10, 210, SCROLL_SPEED, gameHeight);
    	wall5 = new Scrollable(xPos2, wall4.getTailY(), 10, 210, SCROLL_SPEED, gameHeight);
		wall6 = new Scrollable(xPos2, wall5.getTailY(), 10, 210, SCROLL_SPEED, gameHeight);
    	this.gameWorld = gameWorld;

    	pipe1 = new Pipe(0, -23, 60, 22, SCROLL_SPEED, gameHeight, gameWidth);
    	pipe2 = new Pipe(0, pipe1.getTailY() - PIPE_GAP, 70, 22, SCROLL_SPEED, gameHeight, gameWidth);
    	pipe3 = new Pipe(0, pipe2.getTailY() - PIPE_GAP, 60, 22, SCROLL_SPEED, gameHeight, gameWidth);
    	
    	bg1 = new Scrollable(0,gameHeight-144,136,144,BG_SPEED, gameHeight);
    	bg2 = new Scrollable(0,bg1.getTailY(),136,144,BG_SPEED, gameHeight);
    	bg3 = new Scrollable(0,bg2.getTailY(),136,144,BG_SPEED, gameHeight);

    }

    public void update(float delta) {
    	
    	wall1.update(delta);
    	wall2.update(delta);
    	wall3.update(delta);
    	wall4.update(delta);
		wall5.update(delta);
		wall6.update(delta);
    	bg1.update(delta);
    	bg2.update(delta);
    	bg3.update(delta);
    	
    	if(bg1.isScrolledDown()){
    		bg1.reset(bg3.getTailY());
    	} else if (bg2.isScrolledDown()){
    		bg2.reset(bg1.getTailY());
    	} else if (bg3.isScrolledDown()){
    		bg3.reset(bg2.getTailY());
    	}
    	

        // Same with walls
        if (wall1.isScrolledDown()) {
            wall1.reset(wall3.getTailY());
        } else if (wall2.isScrolledDown()) {
        	wall2.reset(wall1.getTailY());
		} else if (wall3.isScrolledDown()) {
			wall3.reset(wall2.getTailY());
		}

        if (wall4.isScrolledDown()) {
            wall4.reset(wall6.getTailY());
        } else if (wall5.isScrolledDown()) {
            wall5.reset(wall4.getTailY());
		} else if (wall6.isScrolledDown()) {
			wall6.reset(wall5.getTailY());
		}
    }
    
    public void updateRunning(float delta){
    	update(delta);
    	pipe1.update(delta);
    	pipe2.update(delta);
    	pipe3.update(delta);
    	if (pipe1.isScrolledDown()) {
            pipe1.reset(pipe3.getTailY() - PIPE_GAP);
        } else if (pipe2.isScrolledDown()) {
            pipe2.reset(pipe1.getTailY() - PIPE_GAP);

        } else if (pipe3.isScrolledDown()) {
            pipe3.reset(pipe2.getTailY() - PIPE_GAP);
        }
    }
    
    private void addScore(int increment){
    	gameWorld.addScore(increment);
    }
    
    public void stop(){
    	wall1.stop();
    	wall2.stop();
    	wall3.stop();
    	wall4.stop();
    	wall5.stop();
    	wall6.stop();
    	pipe1.stop();
    	pipe2.stop();
    	pipe3.stop();
    	bg1.stop();
    	bg2.stop();
    	bg3.stop();
    }
    
    public boolean collides(Ship ship, boolean muted){
    	if(!pipe1.isScored() && pipe1.getY() + (pipe1.getHeight()/2) > ship.getY()){
    		addScore(1);
    		pipe1.setScored(true);
    		if(!muted)
    			AssetLoader.coin.play();
    	} else if (!pipe2.isScored() && pipe2.getY() + (pipe2.getHeight()/2) > ship.getY()){
    		addScore(1);
    		pipe2.setScored(true);
    		if(!muted)
    			AssetLoader.coin.play();
    	} else if (!pipe3.isScored() && pipe3.getY() + (pipe3.getHeight()/2) > ship.getY()){
    		addScore(1);
    		pipe3.setScored(true);
    		if(!muted)
    			AssetLoader.coin.play();
    	}
    	
    	return(pipe1.collides(ship) || pipe2.collides(ship) || pipe3.collides(ship));
    }
    
    public void onRestart(){
    	wall1.onRestart();
    	wall2.onRestart();
    	wall3.onRestart();
    	wall4.onRestart();
    	wall5.onRestart();
    	wall6.onRestart();
    	pipe1.onRestart(-24, SCROLL_SPEED);
    	pipe2.onRestart(pipe1.getTailY() - PIPE_GAP, SCROLL_SPEED);
    	pipe3.onRestart(pipe2.getTailY() - PIPE_GAP, SCROLL_SPEED);
    	bg1.onRestart();
    	bg2.onRestart();
    	bg3.onRestart();
    }

    // The getters for our five instance variables
    public Scrollable getWall1() {
        return wall1;
    }

    public Scrollable getWall2() {
        return wall2;
    }
    
    public Scrollable getWall3(){
    	return wall3;
    }
    
    public Scrollable getWall4(){
    	return wall4;
    }

    public Scrollable getWall5(){
    	return wall5;
	}

	public Scrollable getWall6(){
    	return wall6;
	}

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }
    
    public Scrollable getBg1(){
    	return bg1;
    }
    
    public Scrollable getBg2(){
    	return bg2;
    }
    
    public Scrollable getBg3(){
    	return bg3;
    }
    
}