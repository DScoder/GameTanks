package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class MainClass extends ApplicationAdapter {
	SpriteBatch batch;
	public BaseTank[] tanks = new BaseTank[15];
	Texture font;
	Random rand = new Random();

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new Texture("font2.png");
		tanks[0] = new PlayerTank(new Vector2(Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/2 ));
		for (int i = 1; i < tanks.length; i++) {
			tanks[i] = new BotWacher(new Vector2(rand.nextInt(1000 - tanks[0].myTexture.getHeight()),rand.nextInt(600 - tanks[0].myTexture.getHeight())));
		}
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(font,0,0,1000,600);
		for (int i = 0; i < tanks.length; i++) {
			if(tanks[i].isAlive()){
				tanks[i].draw(batch);
			}
		}
		batch.end();
	}

	public void update(){
		tanks[0].update();
		for (int i = 1; i < tanks.length; i++) {
			if(tanks[i].isAlive()) {
				tanks[i].update();
				float lenTanks = (float)Math.sqrt(Math.pow(tanks[0].position.x - tanks[i].position.x, 2) +
						Math.pow(tanks[0].position.y - tanks[i].position.y, 2));
				if(lenTanks < 35){
					tanks[i].destroy();
				}
				for (int j = 0; j < tanks[0].ams.size(); j++) {
					float lenBullet = (float)Math.sqrt(Math.pow(tanks[0].ams.get(j).position.x - tanks[i].position.x, 2) +
							Math.pow(tanks[0].ams.get(j).position.y - tanks[i].position.y, 2));
					if(lenBullet < 30){
						tanks[i].destroy();
						tanks[0].ams.remove(j);
					}
				}
			}
		}
	}
}
