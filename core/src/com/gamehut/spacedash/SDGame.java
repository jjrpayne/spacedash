package com.gamehut.spacedash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gamehut.screens.GameScreen;
import com.gamehut.sdhelpers.AssetLoader;

public class SDGame extends Game{

	@Override
	public void create() {
		Gdx.app.log("SDGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
}