package com.gamehut.gameworld;

import com.badlogic.gdx.Gdx;
import com.gamehut.gameobjects.Ship;

public class GameWorld {
	private Ship ship;
	
	public GameWorld(int midPointX){
		// Initialize ship here
		ship = new Ship(midPointX, 33, 17, 12);
	}
	
	public void update(float delta){
		Gdx.app.log("Gameworld", "update");
	}
	
	public Ship getShip(){
		return ship;
	}

}
