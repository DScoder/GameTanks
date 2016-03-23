package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ilya on 21.03.2016.
 */
public class Ammo {
    protected Vector2 position;
    protected float angle;
    protected static Texture myTexture;
    protected float velocityX;
    protected float velocityY;

    public Ammo(Vector2 position, float angle) {
        this.position = position;
        this.angle = angle;
        myTexture = new Texture("Bullet16x16.png");
        velocityY = 0;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(myTexture, position.x, position.y, myTexture.getWidth() / 2, myTexture.getHeight() / 2,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight(), false, false);
    }

    public void update() {
        velocityY = (float) Math.cos((angle) * Math.PI / 180);
        velocityX = (float) Math.sin((angle + 180) * Math.PI / 180);
        position.x += velocityX * 5;
        position.y += velocityY * 5;
    }
}
