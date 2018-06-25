package com.gamehut.sdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.gamehut.gameobjects.Ship;
import com.gamehut.gameworld.GameWorld;
import com.gamehut.ui.SimpleButton;

public class InputHandler implements InputProcessor {
	private GameWorld myWorld;
	private Ship myShip;

	private SimpleButton muteButton;
	private SimpleButton exitButton;

	private float scaleFactorX;
	private float scaleFactorY;
	private float gameWidth;
	private float gameHeight;
	
	public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY,
						float gameWidth, float gameHeight){
		this.myWorld = myWorld;
		myShip = myWorld.getShip();

		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;

		muteButton = new SimpleButton(1,1, 15, 15,
				AssetLoader.soundOnUp, AssetLoader.soundOnDown);
		exitButton = new SimpleButton(gameWidth -1 -15, 1, 15, 15,
				AssetLoader.exitUp, AssetLoader.exitDown);
	}


	public boolean keyDown(int keycode) {
		//myShip.move(keycode);
		Gdx.app.log("InputHandler", "keyDown " + keycode);
		return true;
	}

	public boolean keyUp(int keycode) {
		//myShip.stop(keycode);
		Gdx.app.log("InputHandler", "keyUp");
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleX(screenY);

	    if(myWorld.isReady()){
			if(muteButton.isTouchDown(screenX, screenY) ||
					exitButton.isTouchDown(screenX, screenY))
				AssetLoader.mute.play();
			else
				myWorld.start();
		}

		myShip.fire();
		
		if (myWorld.isGameOver() || myWorld.isHighScore()){
			myWorld.restart();
		}
		return true; // return true to say we handled the touch
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if(myWorld.isReady()){
			if(muteButton.isTouchUp(screenX, screenY)){
				if(myWorld.isMuted()){
					myWorld.setMuted(false);
					muteButton.changeTexture(AssetLoader.soundOnUp,
							AssetLoader.soundOnDown);
				} else {
					myWorld.setMuted(true);
					muteButton.changeTexture(AssetLoader.soundOffUp,
							AssetLoader.soundOffDown);
				}
			} else if (exitButton.isTouchUp(screenX, screenY)){
				Gdx.app.exit();
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	private int scaleX(int screenX) {
		return(int)(screenX/scaleFactorX);
	}

	private int scaleY(int screenY){
		return(int)(screenY/scaleFactorY);
	}

	public SimpleButton getMuteButton() {
		return muteButton;
	}

	public SimpleButton getExitButton() {
		return exitButton;
	}
}
