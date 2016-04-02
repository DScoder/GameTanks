package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by DScoder on 19.03.2016.
 */

public class PlayerTank extends BaseTank {
    public TheGun gun1;
    private Music moveSound;

    public PlayerTank(Vector2 position) {
        super(position);
        ams = new ArrayList<Ammo>();
        gun1 = new TheGun(position.cpy().add(0, 12));
        myTexture = new Texture("MainTank30x40.png");
        moveSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/Move.wav"));
        moveSound.setLooping(true);
        moveSound.setVolume(.6f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(myTexture, position.x, position.y, myTexture.getWidth() / 2, myTexture.getHeight() / 2,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight(), false, false);
        gun1.position = position.cpy().add(0, 12);
        gun1.draw(batch);
        for (int i = 0; i < ams.size(); i++) {
            ams.get(i).draw(batch);
        }
    }

    @Override
    public void update() {
        inputKeyboard();
        canMove();
        position.x += velocityX;
        position.y += velocityY;
    }

    private void inputKeyboard() {
        velocityX = velocityY = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playMoveSound();
            angle = 0;
            velocityY = 1.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playMoveSound();
            angle = 180.0f;
            velocityY = -1.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playMoveSound();
            angle = 270.0f;
            velocityX = 1.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playMoveSound();
            angle = 90.0f;
            velocityX = -1.0f;
        } else {
            moveSound.stop();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle = 315.0f;
            velocityX = velocityY = 0.75f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle = 45.0f;
            velocityX = -0.75f;
            velocityY = 0.75f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle = 225.0f;
            velocityX = 0.75f;
            velocityY = -0.75f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle = 135.0f;
            velocityX = velocityY = -0.75f;
        }
    }

    private void canMove() {
        if (velocityX > 0 && position.x > Gdx.graphics.getWidth() - 100 - myTexture.getHeight() || velocityX < 0 && position.x <= 0)
            velocityX = 0;
        if (velocityY > 0 && position.y > Gdx.graphics.getHeight() - myTexture.getHeight() || velocityY < 0 && position.y <= 0)
            velocityY = 0;
    }

    public void shoot() {
        ams.add(new Ammo(new Vector2(gun1.position.x + 10, gun1.position.y), gun1.angle));
    }

    private void playMoveSound(){
        if(!moveSound.isPlaying()){
            moveSound.play();
        }
    }
}
