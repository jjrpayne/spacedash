package com.gamehut.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ship {
	
	private Vector2 position;
	private Vector2 velocity;
	
	private float width;
	private float height;
	private float originalY;
	
	private boolean isAlive;
	
	private Circle boundingCircle;
	
	public Ship(float x, float y, float width, float height) {
		originalY = y;
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		boundingCircle = new Circle();
		isAlive = true;
	}

	
	public void update(float delta){

		position.x += velocity.x * delta;
		position.y += velocity.y * delta;

		boundingCircle.set((position.x+7.6f), position.y+8, 7.5f);
		if(!isAlive) {
			if(position.y < 0 - height)
				velocity.y = 0;
			else
				velocity.y = -180;
		}

	}
	
	public void onRestart(float x){
		position.x = x;
		position.y = originalY;
		velocity.x = 0;
		velocity.y = 0;
		isAlive = true;
	}
	
	public void fire(){
		
	}
	
	public void move(int keycode){
		if (keycode == Keys.LEFT)
			velocity.x = -140;
		if (keycode == Keys.RIGHT)
			velocity.x = 140;
		
	}
	
	public void stop(){
		velocity.x = 0;
	}
	
	public void die(){
		isAlive = false;
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
    
    public Circle getBoundingCircle(){
    	return boundingCircle;
    }
    
    public boolean isAlive(){
    	return isAlive;
    }

    public float getXVelocity(){
		return velocity.x;
	}

}
