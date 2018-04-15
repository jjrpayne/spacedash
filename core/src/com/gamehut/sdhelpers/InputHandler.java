package com.gamehut.sdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.gamehut.gameobjects.Ship;

public class InputHandler implements InputProcessor {
	private Ship myShip;
	
	public InputHandler(Ship ship){
		myShip = ship;
	}
	
	public boolean keyDown(int keycode) {
		myShip.move(keycode);
		Gdx.app.log("InputHandler", "keyDown " + keycode);
		return true;
	}

	public boolean keyUp(int keycode) {
		myShip.stop(keycode);
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
		myShip.fire();
		return true; // return true to say we handled the touch
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
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

}
