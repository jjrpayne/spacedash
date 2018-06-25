package com.gamehut.spacedash.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gamehut.spacedash.SDGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Space Dash";
        config.width = 272;
        config.height = 408;
        new LwjglApplication(new SDGame(null), config);
    }
}