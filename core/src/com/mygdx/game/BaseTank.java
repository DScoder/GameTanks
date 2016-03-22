package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Ilya on 19.03.2016.
 */
public abstract class BaseTank {
    protected Vector2 position;
    protected float angle;
    protected Texture myTexture;
    protected boolean alive;
    ArrayList<Ammo> ams;

    public BaseTank(Vector2 position) {
        this.position = position;
        this.angle = 0;
        alive = true;
        ams = new ArrayList<Ammo>();
    }
    public void rotate(int howFast){
        angle += howFast;
    }

    public abstract void draw(SpriteBatch batch);

    public void update(){}

    public void destroy(){
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }
}


