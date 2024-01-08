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

        //Handles the boundaries of the game
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

    public void checkCollision(Circle ball, Rectangle rectangleListener) { //check if the ball collides with the paddle
        if (Intersector.overlaps(ball, rectangleListener)) {
            changeYDirection();
            changeXDirection(ball, rectangleListener);
        } else {
            color = Color.WHITE;
        }
    }

    public void changeXDirection(Circle ball, Rectangle rectangleListener) {
        // if (ball.x > rectangleListener.x + rectangleListener.width / 2) {
        //     System.out.println("HIT THIS SIDE, BALL X: "+ball.x+" PADDLE X: "+rectangleListener.x
        //     );
        // @TODO figure out how you want to create the paddle logic
        xSpeed = -xSpeed;
        //  }

    }

    public void draw(ShapeRenderer shape, Circle circleListener) {
        shape.setColor(color);
        shape.circle(x, y, size);
        circleListener.set(x, y, size);
    }
}
