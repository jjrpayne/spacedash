package com.gamehut.spacedash.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.gamehut.spacedash.SDGame;
import com.google.gwt.user.client.Window;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                //return new GwtApplicationConfiguration(true);
                // Fixed size application:
                return new GwtApplicationConfiguration(Window.getClientHeight()/2, Window.getClientHeight() - 10);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new SDGame();
        }
}