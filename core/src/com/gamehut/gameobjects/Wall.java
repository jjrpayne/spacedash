package com.gamehut.gameobjects;

public class Wall extends Scrollable{
	
    // When Wall's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Wall(float x, float y, int width, int height, float scrollSpeed, int gameHeight) {
        super(x, y, width, height, scrollSpeed, gameHeight);

    }
    

}
