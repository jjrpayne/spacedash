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

    public static final int EXPLOSION_SIZE = 32;
    public static Texture texture;
    public static TextureRegion bg, wallRight, wallLeft;
    
    public static Sound dead, coin, hiscore, mute;

    public static Animation explosionAnimation;
    public static Animation ship;
    public static TextureRegion ship1, ship2, explosion1, explosion2, explosion3,
            explosion4, explosion5, explosion6, explosion7, explosion8,
            explosion9, explosion10, explosion11, explosion12;
    public static TextureRegion pillarTopRight, pillarTopLeft, pipe;
    public static TextureRegion spaceDashLogo, arrowLeft, arrowRight;
    public static TextureRegion playUp, playDown, soundOnUp, soundOnDown;
    public static TextureRegion soundOffUp, soundOffDown, exitUp, exitDown;
    
    public static BitmapFont font, dosfont;
    
    public static Preferences prefs;

    public static void load() {
    	
    	dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
    	coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        hiscore = Gdx.audio.newSound(Gdx.files.internal("data/hiscore.wav"));
        mute = Gdx.audio.newSound(Gdx.files.internal("data/coin1.wav"));

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 128, 136);
        bg.flip(false, true);

        wallRight = new TextureRegion(texture, 289, 24, 10, 210);
        wallRight.flip(false, true);
        
        wallLeft = new TextureRegion(texture, 289, 24, 10, 210);
        wallLeft.flip(true, true);

        ship1 = new TextureRegion(texture, 370, 222, 16, 24);
        ship1.flip(false, true);
        ship2 = new TextureRegion(texture, 370, 246, 16, 24);
        ship2.flip(false, true);

        TextureRegion[] ships = {ship1, ship2};
        ship = new Animation(0.1f, ships);
        ship.setPlayMode(Animation.PlayMode.LOOP);

        explosion1 = new TextureRegion(texture, 0, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion1.flip(false, true);
        explosion2 = new TextureRegion(texture, 1*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion2.flip(false, true);
        explosion3 = new TextureRegion(texture, 2*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion3.flip(false, true);
        explosion4 = new TextureRegion(texture, 3*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion4.flip(false, true);
        explosion5 = new TextureRegion(texture, 4*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion5.flip(false, true);
        explosion6 = new TextureRegion(texture, 5*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion6.flip(false, true);
        explosion7 = new TextureRegion(texture, 6*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion7.flip(false, true);
        explosion8 = new TextureRegion(texture, 7*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion8.flip(false, true);
        explosion9 = new TextureRegion(texture, 8*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion9.flip(false, true);
        explosion10 = new TextureRegion(texture, 9*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion10.flip(false, true);
        explosion11 = new TextureRegion(texture, 10*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion11.flip(false, true);
        explosion12 = new TextureRegion(texture, 11*EXPLOSION_SIZE, 272, EXPLOSION_SIZE, EXPLOSION_SIZE);
        explosion12.flip(false, true);

        spaceDashLogo = new TextureRegion(texture, 301, 0, 109, 86);
        spaceDashLogo.flip(false, true);
        playUp = new TextureRegion(texture,303, 88, 32, 8);
        playUp.flip(false, true);
        playDown = new TextureRegion(texture, 303, 99, 32, 8);
        playDown.flip(false, true);
        soundOnUp = new TextureRegion(texture, 303, 174, 15, 15);
        soundOnUp.flip(false, true);
        soundOnDown = new TextureRegion(texture, 319, 174, 15, 15);
        soundOnDown.flip(false, true);
        soundOffUp = new TextureRegion(texture, 303, 191, 15, 15);
        soundOffUp.flip(false, true);
        soundOffDown = new TextureRegion(texture, 319, 191, 15, 15);
        soundOffDown.flip(false, true);
        exitUp = new TextureRegion(texture, 303, 210, 15, 15);
        exitUp.flip(false, true);
        exitDown = new TextureRegion(texture, 320, 210, 15, 15);
        exitDown.flip(false, true);

        TextureRegion[] explosion = {explosion1, explosion2, explosion3, explosion4, explosion5, explosion6,
        explosion7, explosion8, explosion9, explosion10, explosion11, explosion12};
        explosionAnimation = new Animation(0.06f, explosion);
        explosionAnimation.setPlayMode(Animation.PlayMode.LOOP);

        pillarTopRight = new TextureRegion(texture, 277, 0, 14, 24);
        pillarTopRight.flip(false,true);
        // Create by flipping existing pillarTopRight
        pillarTopLeft = new TextureRegion(pillarTopRight);
        pillarTopLeft.flip(true, false);

        pipe = new TextureRegion(texture, 293, 1, 3, 22);
        pipe.flip(false, true);

        arrowRight = new TextureRegion(texture, 334, 152, 10,10);
        arrowRight.flip(false, true);
        arrowLeft = new TextureRegion(texture,334, 152, 10, 10);
        arrowLeft.flip(true, true);
        
        font = new BitmapFont(Gdx.files.internal("data/pixel.fnt"), true);
        dosfont = new BitmapFont(Gdx.files.internal("data/dos.fnt"), true);
        
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