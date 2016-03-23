package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ilya on 19.03.2016.
 */
public class BotWatcher extends BaseTank {
    private double vx = 0;
    private double vy = 0;

    public BotWatcher(Vector2 position) {
        super(position);
        if (myTexture == null) {
            myTexture = new Texture("BotWatcher30x40.png");
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(myTexture, position.x, position.y, myTexture.getWidth() / 2, myTexture.getHeight() / 2,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight(), false, false);
    }

    @Override
    public void update() {
        time += Gdx.graphics.getDeltaTime();
        if (time > 10 * random.nextFloat() + 1) {
            angle = random.nextInt(360);
            vy = (float) Math.cos((angle) * Math.PI / 180);
            vx = (float) Math.sin((angle + 180) * Math.PI / 180);
            time = 0;
        }
        position.x += 1.5 * vx;
        position.y += 1.5 * vy;
        if (position.x > 965 && vx > 0)
            vx = 0;
        if (position.x < 0 && vx < 0)
            vx = 0;
        if (position.y > 565 && vy > 0)
            vy = 0;
        if (position.y < 0 && vy < 0)
            vy = 0;
    }
}

