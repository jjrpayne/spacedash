package com.gamehut.sdhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, wallRight, wallLeft;

    public static Animation explosionAnimation;
    public static TextureRegion ship, explosion1, explosion2, explosion3, explosion4, explosion5;

    public static TextureRegion pillarTopRight, pillarTopLeft, pipe;

    public static void load() {

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
        explosionAnimation = new Animation(0.06f, explosion);
        explosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);

        pillarTopRight = new TextureRegion(texture, 277, 0, 14, 24);
        pillarTopRight.flip(false,true);
        // Create by flipping existing pillarTopRight
        pillarTopLeft = new TextureRegion(pillarTopRight);
        pillarTopLeft.flip(true, false);

        pipe = new TextureRegion(texture, 293, 1, 3, 22);
        pipe.flip(false, true);

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
    }

}