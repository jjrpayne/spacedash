package com.gamehut.sdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, wallRight, wallLeft;
    
    public static Sound dead, coin;

    public static Animation explosionAnimation;
    public static TextureRegion ship, explosion1, explosion2, explosion3, explosion4, explosion5;
    public static TextureRegion pillarTopRight, pillarTopLeft, pipe;
    
    public static BitmapFont font;
    
    public static Preferences prefs;

    public static void load() {
    	
    	dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.mp3"));
    	coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.mp3"));

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 256, 272);
        bg.flip(false, true);

        wallRight = new TextureRegion(texture, 289, 24, 10, 210);
        wallRight.flip(false, true);
        
        wallLeft = new TextureRegion(texture, 289, 24, 10, 210);
        wallLeft.flip(true, true);

        ship = new TextureRegion(texture, 256, 226, 15, 14);
        ship.flip(false, true);

        explosion1 = new TextureRegion(texture, 256, 202, 25, 24);
        explosion1.flip(false, true);
        
        explosion2 = new TextureRegion(texture, 256, 173, 25, 24);
        explosion2.flip(false, true);
        
        explosion3 = new TextureRegion(texture, 256, 146, 25, 24);
        explosion3.flip(false, true);
        
        explosion4 = new TextureRegion(texture, 256, 119, 25, 24);
        explosion4.flip(false, true);

        explosion5 = new TextureRegion(texture, 256, 91, 25, 24);
        explosion5.flip(false, true);

        TextureRegion[] explosion = { ship, explosion1, explosion2, explosion3, explosion4, explosion5 };
        explosionAnimation = new Animation(0.1f, explosion);
        explosionAnimation.setPlayMode(Animation.PlayMode.LOOP);

        pillarTopRight = new TextureRegion(texture, 277, 0, 14, 24);
        pillarTopRight.flip(false,true);
        // Create by flipping existing pillarTopRight
        pillarTopLeft = new TextureRegion(pillarTopRight);
        pillarTopLeft.flip(true, false);

        pipe = new TextureRegion(texture, 293, 1, 3, 22);
        pipe.flip(false, true);
        
        font = new BitmapFont(Gdx.files.internal("data/pixel.fnt"), true);
        
        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("SpaceDash");
        
       // Provide default high score of 0
        if(!prefs.contains("highScore")){
        	prefs.putInteger("highScore", 0);
        }

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        dead.dispose();
        font.dispose();
    }
    
    public static void setHighScore(int val){
    	prefs.putInteger("highScore", val);
    	prefs.flush();
    }
    
    public static int getHighScore(){
    	return prefs.getInteger("highScore");
    }

}