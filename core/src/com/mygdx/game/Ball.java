package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


public class Ball {
    int x, y, size, xSpeed, ySpeed;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;

        if (x < size || x > Gdx.graphics.getWidth() - size) {
            xSpeed = -xSpeed;
        }
        if (y < size || y > Gdx.graphics.getHeight() - size) {
            changeYDirection();
        }
    }

    public void changeYDirection() {
        ySpeed = -ySpeed;
    }

    public void checkCollision(Circle ball, Rectangle blockListener) {
        if(Intersector.overlaps(ball, blockListener)) {
            changeYDirection();
        }
        else {
            color = Color.WHITE;
        }
    }

    public void draw(ShapeRenderer shape, Circle ballListener) {
        shape.setColor(color);
        shape.circle(x, y, size);
        ballListener.set(x, y, size);
    }
}
