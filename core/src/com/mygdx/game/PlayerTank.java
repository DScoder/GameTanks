package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Ilya on 19.03.2016.
 */
public class PlayerTank extends BaseTank {
    public TheGun gun1;

    public PlayerTank(Vector2 position) {
        super(position);
        ams = new ArrayList<Ammo>();
        gun1 = new TheGun(new Vector2(new Vector2(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 3 + 12)));
        myTexture = new Texture("MainTank30x40.png");
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(myTexture, position.x, position.y, myTexture.getWidth() / 2, myTexture.getHeight() / 2,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight(), false, false);
        gun1.draw(batch);
        if (Gdx.input.justTouched()) {
            shoot();
        }
        for (int i = 0; i < ams.size(); i++) {
            ams.get(i).draw(batch);
        }
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            angle = 0;
            if (position.y < 600 - myTexture.getHeight()) {
                position.y++;
                gun1.position.y++;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            angle = 180.0f;
            if (position.y > 0) {
                position.y--;
                gun1.position.y--;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle = 270.0f;
            if (position.x < 1000 - (myTexture.getHeight() - 5)) {
                position.x++;
                gun1.position.x++;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle = 90.0f;
            if (position.x > 0) {
                position.x--;
                gun1.position.x--;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle = 315.0f;
            position.x -= 0.25f;
            gun1.position.x -= 0.25f;
            position.y -= 0.25f;
            gun1.position.y -= 0.25f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle = 45.0f;
            position.x += 0.25f;
            gun1.position.x += 0.25f;
            position.y -= 0.25f;
            gun1.position.y -= 0.25f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle = 225.0f;
            position.x -= 0.25f;
            gun1.position.x -= 0.25f;
            position.y += 0.25f;
            gun1.position.y += 0.25f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle = 135.0f;
            position.x += 0.25f;
            gun1.position.x += 0.25f;
            position.y += 0.25f;
            gun1.position.y += 0.25f;
        }
        gun1.update();
        for (int i = 0; i < ams.size(); i++) {
            if (ams.get(i).position.x > 1000 - 16 || ams.get(i).position.y > 600 - 16 || ams.get(i).position.x < 0 || ams.get(i).position.y < 0) {
                ams.remove(i);
                i--;
            } else
                ams.get(i).update();
        }
    }

    public void shoot() {
        ams.add(new Ammo(new Vector2(gun1.position.x + 10, gun1.position.y), gun1.angle));
    }
}
