package com.mygame.topdown;

import com.badlogic.gdx.Game;

public class MyTopDownGame extends Game {

    @Override
    public void create() {
        this.setScreen(new GameWindow());
    }
}
