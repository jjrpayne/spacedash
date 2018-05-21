package com.gamehut.gameworld;

import com.badlogic.gdx.Gdx;
import com.gamehut.gameobjects.ScrollHandler;
import com.gamehut.gameobjects.Ship;
import com.gamehut.sdhelpers.AssetLoader;

public class GameWorld {
	private Ship ship;
	private ScrollHandler scroller;
	private int score = 0;
	private int midPointX;
	//private int gameHeight;
	//private int gameWidth;
	
	private GameState currentState;
	
	public enum GameState{
		READY, RUNNING, GAMEOVER, HIGHSCORE
	}
	
	public GameWorld(int midPointX, int gameHeight, int gameWidth){
		currentState = GameState.READY;
		// Initialize ship here
		this.midPointX = midPointX;
//		this.gameHeight = gameHeight;
//		this.gameWidth = gameWidth;
		ship = new Ship(midPointX, gameHeight-33, 15, 14);
		scroller = new ScrollHandler(0, midPointX + midPointX -10, gameHeight, gameWidth, this);
	}
	
	public void update(float delta) {
		switch(currentState){
		case READY:
			updateReady(delta);
			break;
		
		case RUNNING:
		default:
			updateRunning(delta);
			break;
		}
	}
	
	private void updateReady(float delta){
		scroller.update(delta);
		
	}
	
	public void updateRunning(float delta){
		Gdx.app.log("Gameworld", "update");
		ship.update(delta);
		scroller.updateRunning(delta);
		
		if(ship.isAlive() && scroller.collides(ship)){
			// Clean up on game over
			scroller.stop();
			ship.die();
			AssetLoader.dead.play();
			currentState = GameState.GAMEOVER;
			
			if(score > AssetLoader.getHighScore()){
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}
	}
	
	public void start(){
		currentState = GameState.RUNNING;
	}
	
	public void restart(){
		currentState = GameState.READY;
		score = 0;
		ship.onRestart(midPointX);
		scroller.onRestart();
		currentState = GameState.READY;
	}
	
	public void addScore(int increment){
		score += increment;
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public ScrollHandler getScroller(){
		return scroller;
	}
	
	public int getScore(){
		return score;
	}
	
	public boolean isReady(){
		return currentState == GameState.READY;
	}
	
	public boolean isRunning(){
		return currentState == GameState.RUNNING;
	}
	
	public boolean isGameOver(){
		return currentState == GameState.GAMEOVER;
	}
	
	public boolean isHighScore(){
		return currentState == GameState.HIGHSCORE;
	}

}
