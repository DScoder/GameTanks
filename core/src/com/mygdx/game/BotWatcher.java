package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by DScoder on 19.03.2016.
 */
public class BotWatcher extends BaseTank {

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
        super.update();
        time -= Gdx.graphics.getDeltaTime();
        if (time <= 0) {
            angle = MainClass.rand.nextInt(360);
            velocityY = (float) Math.cos((angle) * Math.PI / 180) * 1.5f;
            velocityX = (float) Math.sin((angle + 180) * Math.PI / 180) * 1.5f;
            time = MainClass.rand.nextFloat() + 1;
        }
        position.x += velocityX;
        position.y += velocityY;
    }
}

