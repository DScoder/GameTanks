package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class MainClass extends ApplicationAdapter {
	SpriteBatch batch;
	ArrayList<BaseTank> tanks = new ArrayList<BaseTank>();
	int countOfTanks = 20;
	Texture mainBackground;
	Texture secondBackground;
	Random rand = new Random();

	@Override
	public void create () {
		batch = new SpriteBatch();
		mainBackground = new Texture("font2.png");
		secondBackground = new Texture("font.png");
		tanks.add(0, new PlayerTank(new Vector2(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2 )));
		for (int i = 0; i < countOfTanks; i++) {
			tanks.add(new BotWatcher(new Vector2(rand.nextInt(1000 - tanks.get(0).myTexture.getHeight()),rand.nextInt(600 - tanks.get(0).myTexture.getHeight()))));
		}
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(mainBackground,0,0,1000,600);
		for (int i = 0; i < 600; i+=100)
			batch.draw(secondBackground,1000,i,100,100);
		for (int i = 0; i < tanks.size(); i++) {
			if(tanks.get(i).isAlive())
				tanks.get(i).draw(batch);
		}
		batch.end();
	}

	public void update(){
		tanks.get(0).update();
		for (int i = 1; i < tanks.size(); i++) {
			if(tanks.get(i).isAlive()) {
				tanks.get(i).update();
				float lenTanks = (float)Math.sqrt(Math.pow(tanks.get(0).position.x - tanks.get(i).position.x, 2) +
						Math.pow(tanks.get(0).position.y - tanks.get(i).position.y, 2));
				if(lenTanks < 35){
					tanks.get(i).destroy();
				}
				for (int j = 0; j < tanks.get(0).ams.size(); j++) {
					float lenBullet = (float)Math.sqrt(Math.pow(tanks.get(0).ams.get(j).position.x - tanks.get(i).position.x, 2) +
							Math.pow(tanks.get(0).ams.get(j).position.y - tanks.get(i).position.y, 2));
					if(lenBullet < 30){
						tanks.get(i).destroy();
						tanks.get(0).ams.remove(j);
					}
				}
			}
		}
	}
}
