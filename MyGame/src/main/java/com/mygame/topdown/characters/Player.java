package com.mygame.topdown.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygame.topdown.GameUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends Character {
    private List<Bullet> bullets;
    private Texture bulletSprite;
    private Vector2 bulletSpawnPosition;
    private float shotTimer;
    private float pistolCooldown;

    public Player(int hitPoints, float moveSpeed, Texture sprite) {
        super(hitPoints, moveSpeed, sprite);
        initPlayer();
    }

    public Player(int hitPoints, float moveSpeed, Texture sprite, Vector2 position) {
        super(hitPoints, moveSpeed, sprite, position);
        initPlayer();
    }

    private void initPlayer() {
        bulletSprite = new Texture("assets/Laser Sprites/01.png");
        bullets = new ArrayList<>();
        shotTimer = 0f;
        pistolCooldown = 0.5f;
    }

    @Override
    public void update(float deltaTime) {
        handleInput(deltaTime);
        updateBullets(deltaTime);
    }

    private void handleInput(float deltaTime) {
        Vector2 inputVector = new Vector2(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            inputVector.y += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            inputVector.y -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            inputVector.x -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            inputVector.x += 1;
        }

        inputVector.nor();
        position.add(inputVector.scl(getMoveSpeed() * deltaTime));

        bulletSpawnPosition = new Vector2(position.x + (sprite.getWidth() / 2.f) - (bulletSprite.getWidth() / 2.f),
                position.y + (sprite.getHeight() / 2.f) - (bulletSprite.getHeight() / 2.f));

        Vector2 mousePosition = GameUtils.getMousePosition();
        direction = mousePosition.sub(bulletSpawnPosition).nor();

        shotTimer += deltaTime;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && shotTimer >= pistolCooldown) {
            shoot();
            shotTimer = 0;
        }
    }

    private void shoot() {
        bullets.add(new Bullet(this, bulletSpawnPosition, bulletSprite));
    }

    private void updateBullets(float deltaTime) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.update(deltaTime);

            boolean isBulletOffScreen = (bullet.getPosition().y > Gdx.graphics.getHeight()
                    || bullet.getPosition().y < 0
                    || bullet.getPosition().x > Gdx.graphics.getWidth()
                    || bullet.getPosition().x < 0
            );

            if (isBulletOffScreen) {
                iterator.remove();
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, position.x, position.y);
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        bulletSprite.dispose();
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
    }
}
