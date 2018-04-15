package com.gamehut.gameobjects;

public class ScrollHandler {
	
    // ScrollHandler will create all five objects that we need.
    private Wall topWallLeft, topWallRight, bottomWallLeft, bottomWallRight;
    private Pipe pipe1, pipe2, pipe3;
    private Scrollable bg1, bg2;

    // ScrollHandler will use the constants below to determine
    // how fast we need to scroll and also determine
    // the size of the gap between each pair of pipes.

    // Capital letters are used by convention when naming constants.
    public static final int SCROLL_SPEED = 118;
    public static final int BG_SPEED = 15;
    public static final int PIPE_GAP = 49;

    // Constructor receives a float that tells us where we need to create our
    // Wall and Pipe objects.
    public ScrollHandler(float xPos1, float xPos2) {
    	topWallLeft = new Wall(xPos1, 0, 10, 210, SCROLL_SPEED);
    	bottomWallLeft = new Wall(xPos1, topWallLeft.getTailY(), 10, 210, SCROLL_SPEED);
    	topWallRight = new Wall(xPos2, 0, 10, 210, SCROLL_SPEED);
    	bottomWallRight = new Wall(xPos2, topWallRight.getTailY(), 10, 210, SCROLL_SPEED);
    	
    	pipe1 = new Pipe(0, 0, 60, 15, SCROLL_SPEED);
    	pipe2 = new Pipe(0, pipe1.getTailY() + PIPE_GAP, 70, 15, SCROLL_SPEED);
    	pipe3 = new Pipe(0, pipe2.getTailY() + PIPE_GAP, 60, 15, SCROLL_SPEED);
    	
    	bg1 = new Scrollable(0,0,136,144,BG_SPEED);
    	bg2 = new Scrollable(0,bg1.getTailY(),136,144,BG_SPEED);

    }

    public void update(float delta) {
    	
    	topWallLeft.update(delta);
    	topWallRight.update(delta);
    	bottomWallLeft.update(delta);
    	bottomWallRight.update(delta);
    	pipe1.update(delta);
    	pipe2.update(delta);
    	pipe3.update(delta);
    	bg1.update(delta);
    	bg2.update(delta);
    	
    	if(bg1.isScrolledDown()){
    		bg1.reset(bg2.getTailY());
    	} else if (bg2.isScrolledDown()){
    		bg2.reset(bg1.getTailY());
    	}
    	
        // Check if any of the pipes are scrolled down,
        // and reset accordingly
        if (pipe1.isScrolledDown()) {
            pipe1.reset(pipe3.getTailY() + PIPE_GAP);
        } else if (pipe2.isScrolledDown()) {
            pipe2.reset(pipe1.getTailY() + PIPE_GAP);

        } else if (pipe3.isScrolledDown()) {
            pipe3.reset(pipe2.getTailY() + PIPE_GAP);
        }

        // Same with walls
        if (topWallLeft.isScrolledDown()) {
            topWallLeft.reset(bottomWallLeft.getTailY());

        } else if (bottomWallLeft.isScrolledDown()) {
            bottomWallLeft.reset(topWallLeft.getTailY());

        }
        	if (topWallRight.isScrolledDown()) {
            topWallRight.reset(bottomWallRight.getTailY());

        } else if (bottomWallRight.isScrolledDown()) {
            bottomWallRight.reset(topWallRight.getTailY());

        }

    }

    // The getters for our five instance variables
    public Wall getTopWallLeft() {
        return topWallLeft;
    }

    public Wall getTopWallRight() {
        return topWallRight;
    }
    
    public Wall getBottomWallLeft(){
    	return bottomWallLeft;
    }
    
    public Wall getBottomWallRight(){
    	return bottomWallRight;
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

}