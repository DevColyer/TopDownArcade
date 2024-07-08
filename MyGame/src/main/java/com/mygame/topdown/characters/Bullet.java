package com.mygame.topdown.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Character parent;
    private Texture sprite;
    private Vector2 position;
    private Vector2 direction;

    private int damage;
    private float speed;

    public Bullet(Character parent, Vector2 position, Texture sprite) {
        this.parent = parent;
        this.position = position;
        this.sprite = sprite;
        this.direction = parent.getDirection();
        damage = 10;
        speed = 100.f;
    }

    public Bullet(Character parent, Vector2 position, Texture sprite, int damage, float speed) {
        this(parent, position, sprite);
        this.damage = damage;
        this.speed = speed;
    }


    public void update(float deltaTime) {
        position.add(direction.scl(speed * deltaTime));
    }

    public void render(SpriteBatch batch) {
        batch.draw(sprite, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        sprite.dispose();
    }
}
