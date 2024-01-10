package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Paddle {

    int width;
    int height;
    int x;
    int y;
    public Texture paddleTexture;


    public Paddle(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void updateLocation() {
        this.x =  Gdx.input.getX() - width / 2;
    }

    public void initPaddleTexture() {
        paddleTexture = new Texture("paddle_sprite.png");
    }


    public void update(Rectangle rectangleListener, SpriteBatch batch) {
        updateLocation();
        rectangleListener.set(x, y, width, height);
        batch.draw(paddleTexture, x, y);
    }
}
