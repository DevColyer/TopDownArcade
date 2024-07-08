package com.mygame.topdown.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygame.topdown.MyTopDownGame;

import java.util.logging.Logger;

public abstract class Character {
    private final static Logger LOGGER = Logger.getLogger(MyTopDownGame.class.getName());

    protected Texture sprite;
    protected Vector2 position;
    protected Vector2 direction;

    private int hitPoints;
    private float moveSpeed;

    public Character(int hitPoints, float moveSpeed, Texture sprite) {
        this.hitPoints = hitPoints;
        this.moveSpeed = moveSpeed;
        this.sprite = sprite;
        this.position = new Vector2(Gdx.graphics.getWidth() / 2.f - sprite.getWidth() / 2.f, Gdx.graphics.getHeight() / 2.f - sprite.getHeight() / 2.f);
    }

    public Character(int hitPoints, float moveSpeed, Texture sprite, Vector2 position) {
        this(hitPoints, moveSpeed, sprite);
        this.position = position;
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public Vector2 getPosition() {
        return position;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void heal(int hitPoints) {
        this.hitPoints += hitPoints;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void dispose() {
        if (sprite != null) sprite.dispose();
    }
}
