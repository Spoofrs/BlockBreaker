package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


public class Paddle {

    int width;
    int height;
    int x;
    int y;


    public Paddle(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void update() {
        this.x =  Gdx.input.getX() - width / 2;
        //this.y = Gdx.graphics.getHeight() - Gdx.input.getY(); Was used to debug ball to paddle collision
    }

    public void drawPaddle(ShapeRenderer shape, Rectangle rectangleListener) {
        shape.rect(x, y, width, height);
        rectangleListener.set(x, y, width, height);

    }
}
