package com.mygame.topdown;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration gameConfig = new Lwjgl3ApplicationConfiguration();
        gameConfig.setTitle("MyTopDownGame");
        gameConfig.setWindowedMode(1280, 720);
        gameConfig.useVsync(true);
        gameConfig.setForegroundFPS(60);

        new Lwjgl3Application(new MyTopDownGame(), gameConfig);
    }
}
