package com.mygame.topdown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.Logger;

public class PlayerCharacter {
    private final static Logger LOGGER = Logger.getLogger(MyTopDownGame.class.getName());
    private Texture sprite;

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 shootDirection;

    private int health;
    private float speed;

    public PlayerCharacter() {
        this(100.f);
    }

    public PlayerCharacter(float speed) {
        this.health = 100;
        this.speed = speed;
        this.velocity = new Vector2();
        this.position = new Vector2();;
        this.shootDirection = new Vector2();
    }

    public PlayerCharacter(float speed, Texture sprite, Vector2 position) {
        this(speed);

        this.sprite = sprite;
        this.position = position;
    }

    public void update(float deltaTime) {
        position.add(velocity.cpy().scl(deltaTime));

    }

    public void handleInput() {
        handleMovement();
        handleShooting();

    }

    private void handleShooting() {
        shootDirection.set(0.f, 0.f);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) shootDirection.y = 1.f;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) shootDirection.y = -1.f;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) shootDirection.x = -1.f;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) shootDirection.x = 1.f;

        LOGGER.info("shootDirection: " + shootDirection.toString());
    }

    private void handleMovement() {
        velocity.set(0.f, 0.f);
        Vector2 moveInput = new Vector2();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) moveInput.y = 1.f;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) moveInput.y = -1.f;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) moveInput.x = -1.f;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) moveInput.x = 1.f;

        moveInput.nor();
        LOGGER.finest("moveInput: " + moveInput.toString());
        velocity.set(moveInput.scl(speed));
    }

    public void shoot() {

    }

    public void render(SpriteBatch batch) {

    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        if (sprite != null) sprite.dispose();
    }
}
