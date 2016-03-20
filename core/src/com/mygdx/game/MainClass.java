package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class MainClass extends ApplicationAdapter {
	SpriteBatch batch;
	BaseTank[] tanks = new BaseTank[10];
	Random rand = new Random();
	TheGun gun1;


	@Override
	public void create () {
		batch = new SpriteBatch();
		tanks[0] = new PlayerTank(new Vector2(Gdx.graphics.getHeight()/2,Gdx.graphics.getWidth()/3));
		for (int i = 1; i < tanks.length; i++) {
			tanks[i] = new BotWacher(new Vector2(rand.nextInt(800),rand.nextInt(600)));
		}
		gun1 = new TheGun(new Vector2(300,300));
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i = 0; i < tanks.length; i++) {
			if(tanks[i] == null)
				break;
			tanks[i].draw(batch);
		}
		gun1.draw(batch);


		batch.end();
	}

	public void update(){

		for (int i = 0; i < tanks.length; i++) {
			if(tanks[i] == null) break;
			tanks[i].update();
		}
		gun1.update();
	}
}
