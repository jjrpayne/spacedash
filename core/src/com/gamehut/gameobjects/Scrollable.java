package com.gamehut.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    // Protected is similar to private, but allows inheritance by subclasses.
    protected Vector2 position;
    protected Vector2 velocity;
    protected float width;
    protected float height;
    protected float gameHeight;
    protected boolean isScrolledDown;
    private float scrollSpeed;

    public Scrollable(float x, float y, float width, float height, float scrollSpeed, float gameHeight) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, scrollSpeed);
        this.scrollSpeed = scrollSpeed;
        this.width = width;
        this.height = height;
        this.gameHeight = gameHeight;
        isScrolledDown = false;
    }

    public void update(float delta) {
        //position.add(velocity.cpy().scl(delta));
        position.y += velocity.y*delta;

        // If the Scrollable object is no longer visible:
        if (position.y > gameHeight) {
            isScrolledDown = true;
        }
    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newY) {
        position.y = newY;
        isScrolledDown = false;
    }
    
    public void onRestart(){
    	velocity.y = scrollSpeed;
    }
    
    
    public void stop(){
    	velocity.y = 0;
    }

    // Getters for instance variables
    public boolean isScrolledDown() {
        return isScrolledDown;
    }

    public float getTailY() {
        return position.y - height;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
