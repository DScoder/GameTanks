package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ilya on 19.03.2016.
 */
public class BotWacher extends BaseTank {


    public BotWacher(Vector2 position) {
        super(position);
        if(myTexture == null){
            myTexture = new Texture("BotWacher30x40.png");
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(myTexture, position.x  , position.y, myTexture.getWidth()/2, myTexture.getHeight()/2,
                myTexture.getWidth(), myTexture.getHeight(), 1.0f, 1.0f, angle, 0, 0, myTexture.getWidth(),
                myTexture.getHeight() ,false, false );
    }

    @Override
    public void update(){
        rotate(1);
    }
}

