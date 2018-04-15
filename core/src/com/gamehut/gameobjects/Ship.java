package com.gamehut.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Ship {
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private int width;
	private int height;
	
	public Ship(float x, float y, int width, int height){
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
	}
	
	public void update(float delta){
		
		acceleration.x = Gdx.input.getAccelerometerX();
		
		velocity.add(acceleration.cpy().scl(delta));
		if(velocity.y > 200){
			velocity.y = 200;
		}
		
		position.add(velocity.cpy().scl(delta));
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
