package com.mygame.topdown;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.logging.Logger;

public class MyTopDownGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    PlayerCharacter player;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        player = new PlayerCharacter();
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        ScreenUtils.clear(new Color(0.3671875f, 0.87890625f, 0.91796875f, 1.f));

        camera.update();
    }

    private void update(float deltaTime) {
        player.update(deltaTime);
        handleInput();
        updateCamera();
    }

    private void handleInput(){
        player.handleInput();
    }

    private void updateCamera(){
        camera.position.set(player.getPosition(), 0);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (player != null) player.dispose();
        if (batch != null) batch.dispose();
    }
}
