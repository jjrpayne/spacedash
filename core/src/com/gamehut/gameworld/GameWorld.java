package com.gamehut.gameworld;

import com.badlogic.gdx.Gdx;
import com.gamehut.gameobjects.ScrollHandler;
import com.gamehut.gameobjects.Ship;

public class GameWorld {
	private Ship ship;
	private ScrollHandler scroller;
	
	public GameWorld(int midPointX, int gameHeight){
		// Initialize ship here
		ship = new Ship(midPointX, gameHeight-33, 15, 14);
		scroller = new ScrollHandler(0, midPointX + midPointX -10);
	}
	
	public void update(float delta){
		Gdx.app.log("Gameworld", "update");
		ship.update(delta);
		scroller.update(delta);
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public ScrollHandler getScroller(){
		return scroller;
	}

}
