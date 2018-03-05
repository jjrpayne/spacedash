package com.gamehut.spacedash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gamehut.screens.GameScreen;

public class SDGame extends Game{

	@Override
	public void create() {
		Gdx.app.log("SDGame", "created");
		setScreen(new GameScreen());
	}
	
}