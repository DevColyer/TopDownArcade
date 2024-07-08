package com.mygame.topdown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygame.topdown.characters.Player;

public class GameWindow implements Screen {
    private SpriteBatch batch;
    private Texture playerTexture;
    private Player player;

    @Override
    public void show() {
        batch = new SpriteBatch();try {
            playerTexture = new Texture("assets/Top_Down_Survivor/handgun/move/survivor-move_handgun_0.png");
        } catch (Exception e) {
            Gdx.app.log("Texture Load Error", "Could not load texture: " + e.getMessage());
        }
        player = new Player(50, 200.f, playerTexture);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float deltaTime = Gdx.graphics.getDeltaTime();

        player.update(deltaTime);

        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        playerTexture.dispose();
    }
}
