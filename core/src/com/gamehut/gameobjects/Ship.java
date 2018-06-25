package com.gamehut.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ship {
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private float currentAccel;
	private float lastAccel;
	
	private float width;
	private float height;
	private float originalY;
	
	private boolean isAlive;
	
	private Circle boundingCircle;
	
	public Ship(float x, float y, float width, float height){
		originalY = y;
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		boundingCircle = new Circle();
		isAlive = true;
		currentAccel = 0;
		lastAccel = 0;
	}
	
	public void update(float delta){
		//Gdx.app.log("ship", velocity+"");
		// velocity.add(acceleration.cpy());;
		// position.add(velocity.cpy().scl(delta));
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
		float alpha = 0.25f;
		if(isAlive)
			currentAccel = -Gdx.input.getAccelerometerX();
		currentAccel = currentAccel * alpha + lastAccel*(1-alpha);
		lastAccel = currentAccel;
		position.x += currentAccel*150*delta;
		boundingCircle.set((position.x+7.6f), position.y+8, 7.5f);
		if(!isAlive)
			velocity.y = -180;

	}
	
	public void onRestart(float x){
		position.x = x;
		position.y = originalY;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 0;
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
	
	public void stop(int keycode){
		if (keycode == Keys.LEFT || keycode == Keys.RIGHT)
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

}
