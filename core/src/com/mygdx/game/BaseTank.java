package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by DScoder on 19.03.2016.
 */
public abstract class BaseTank {
    protected Vector2 position;
    protected float angle;
    protected Texture myTexture;
    protected ArrayList<Ammo> ams;
    protected float time;
    protected Random random = new Random();


    public BaseTank(Vector2 position) {
        this.position = position;
        this.angle = 0;
    }

    public abstract void draw(SpriteBatch batch);

    public void update(){}
    public void shoot(){}
}


