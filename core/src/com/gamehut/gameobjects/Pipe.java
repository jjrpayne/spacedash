package com.gamehut.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable{
	
    private Random r;
    private Rectangle skullLeft, skullRight, barLeft, barRight;
    private int gameWidth;
    private boolean isScored = false;
    
    public static final int HORIZONTAL_GAP = 45;
    public static int SKULL_WIDTH = 11;
    public static int SKULL_HEIGHT = 24;

    // When Pipe's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Pipe(float x, float y, int width, int height, float scrollSpeed, 
    		int gameHeight, int gameWidth){
        super(x, y, width, height, scrollSpeed, gameHeight);
        // Initialize a Random object for Random number generation
        r = new Random();
        
        skullLeft = new Rectangle();
        skullRight = new Rectangle();
        barLeft = new Rectangle();
        barRight = new Rectangle();
        this.gameWidth = gameWidth;
    }

    @Override
    public void reset(float newY) {
        // Call the reset method in the superclass (Scrollable)
        super.reset(newY);
        // Change the width to a random number
        width = r.nextInt(55) + 15;
        isScored = false;
    }
    
    @Override
    public void update(float delta){
    	// call update method in superclass (Scrollable)
    	super.update(delta);
    	
    	barLeft.set(position.x, position.y, width+3, height);
    	barRight.set(position.x + width + HORIZONTAL_GAP, position.y, gameWidth - 
    			(position.x+width+HORIZONTAL_GAP), height);
    	skullLeft.set(position.x + width+3, position.y-1, SKULL_WIDTH, SKULL_HEIGHT);
    	skullRight.set(position.x + width + HORIZONTAL_GAP, position.y-1,
    		SKULL_WIDTH, SKULL_HEIGHT);
    }
    
    public boolean collides(Ship ship){
    	if(ship.getX() < 10 || ship.getX() + ship.getWidth() > gameWidth -10)
    		return true;
    	if(position.y + height > ship.getY() ){
    		return (Intersector.overlaps(ship.getBoundingCircle(), barLeft)
                    || Intersector.overlaps(ship.getBoundingCircle(), barRight)
                    || Intersector.overlaps(ship.getBoundingCircle(), skullLeft) || Intersector
                        .overlaps(ship.getBoundingCircle(), skullRight));	
    	}
    	return false;
    }
    
    public void onRestart(float y, float scrollSpeed){
    	velocity.y = scrollSpeed;
    	reset(y);
    }

	public Rectangle getSkullLeft() {
		return skullLeft;
	}

	public Rectangle getSkullRight() {
		return skullRight;
	}

	public Rectangle getBarLeft() {
		return barLeft;
	}

	public Rectangle getBarRight() {
		return barRight;
	}
	
	public boolean isScored(){
		return isScored;
	}
	
	public void setScored(boolean b){
		isScored = b;
	}

}
