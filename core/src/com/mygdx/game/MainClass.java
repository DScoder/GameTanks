package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class MainClass extends ApplicationAdapter {
    SpriteBatch batch;
    ArrayList<BaseTank> tanks = new ArrayList<BaseTank>();
    private int countOfBotTanks = 20;
    Texture mainBackground;
    Texture bonusBackground;
    Music music;
    Sound destroyTankSound;
    Sound destroyBulletSound;
    Sound bonusSound;
    Sound shootSound;
    public static Random rand = new Random();
    private float rateOfFire = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        mainBackground = new Texture("MainBackground.png");
        bonusBackground = new Texture("BonusBackground.png");

        destroyTankSound = Gdx.audio.newSound(Gdx.files.internal("sounds/DestroyTank.wav"));
        destroyBulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/DestroyBullet.wav"));
        bonusSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Bonus.wav"));
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Shoot.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/BrainWave.mp3"));
        music.setLooping(true);
        music.play();

        tanks.add(0, new PlayerTank(new Vector2(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 3)));
        tanks.add(new BotWatcher(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(mainBackground, 0, 0, 1000, 600);
        for (int i = 0; i < 600; i += 100)
            batch.draw(bonusBackground, 1000, i, 100, 100);
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).draw(batch);
        }
        batch.end();
    }

    public void update() {
        if (tanks.size() < 2) {
            bonusSound.play();
//            tanks.get(0).ams.clear();
            for (int i = 0; i < countOfBotTanks; i++) {
                tanks.add(new BotWatcher(new Vector2(rand.nextInt(1000 - tanks.get(0).myTexture.getHeight()), rand.nextInt(600 - tanks.get(0).myTexture.getHeight()))));
            }
        }
        tanks.get(0).update();
//        if (Gdx.input.justTouched()) {
//            tanks.get(0).shoot();
//        }
        rateOfFire += Gdx.graphics.getDeltaTime();
        if (Gdx.input.isTouched() && rateOfFire > 0.2f) {
            tanks.get(0).shoot();
            shootSound.play();
            rateOfFire = 0;
        }
        //Bullets update
        for (int i = 0; i < tanks.get(0).ams.size(); i++) {
            tanks.get(0).ams.get(i).update();
        }
        //Tanks update and crush
        for (int i = 1; i < tanks.size(); i++) {
            tanks.get(i).update();
            float lenTanks = (float) Math.sqrt(Math.pow(tanks.get(0).position.x - tanks.get(i).position.x, 2) +
                    Math.pow(tanks.get(0).position.y - tanks.get(i).position.y, 2));
            if (lenTanks < 35) {
                tanks.remove(i);
                destroyTankSound.play();
                break;
            }

            //Bullets crash
            for (int j = 0; j < tanks.get(0).ams.size(); j++) {
                if (tanks.get(0).ams.get(j).position.x > 1000 - 16 || tanks.get(0).ams.get(j).position.y > 600 - 16 || tanks.get(0).ams.get(j).position.x < 0 || tanks.get(0).ams.get(j).position.y < 0) {
                    synchronized (tanks.get(0).ams) {
                        tanks.get(0).ams.remove(j);
                        destroyBulletSound.play();
                        j--;
                        break;
                    }
                }
                float lenBullet = (float) Math.sqrt(Math.pow(tanks.get(0).ams.get(j).position.x - tanks.get(i).position.x, 2) +
                        Math.pow(tanks.get(0).ams.get(j).position.y - tanks.get(i).position.y, 2));
                if (lenBullet < 30) {
                    tanks.remove(i);
                    destroyTankSound.play();
                    i--;
                    synchronized (tanks.get(0).ams) {
                        tanks.get(0).ams.remove(j);
                        break;
                    }
                }
            }
        }
    }
}
