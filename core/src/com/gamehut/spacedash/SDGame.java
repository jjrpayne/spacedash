package com.gamehut.spacedash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gamehut.AdsController;
import com.gamehut.screens.GameScreen;
import com.gamehut.sdhelpers.AssetLoader;

public class SDGame extends Game{
	private AdsController adsController;

	public SDGame(AdsController adsController){
		this.adsController = adsController;
	}

	@Override
	public void create() {
		Gdx.app.log("SDGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
		if(adsController.isWifiConnected())
			adsController.showBannerAd();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
}