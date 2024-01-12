package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.SoundHandler;


public class Ball {
    int x, y, size, xSpeed, ySpeed;
    boolean canShoot;
    SoundHandler ball_paddle_collision_sfx = new SoundHandler("paddle_hit.ogg");

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void checkScreenBoundaries() {
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
            changeXDirection(ball, rectangleListener, false);
            canShoot = true;
            ball_paddle_collision_sfx.playSound(ball_paddle_collision_sfx.sound, 1.0f);
        }
    }

    public void changeXDirection(Circle ball, Rectangle rectangleListener, boolean blockCollision) {
        if (blockCollision)
            return;
        int paddlecenter = (int) (rectangleListener.x + rectangleListener.width / 2);
        if ((int) (ball.x - paddlecenter) / 5 == 0) {
            return;
        }
        xSpeed = (int) (ball.x - paddlecenter) / 5;
        System.out.println("xspeed = " + xSpeed);
    }

    public void update(Circle circleListener, SpriteBatch batch, Rectangle rectangleListener) {
        Texture ballTexture = new Texture("ball_sprite.png");
        checkScreenBoundaries();
        circleListener.set(x, y, size);
        checkCollision(circleListener, rectangleListener);
        batch.draw(ballTexture, x - size, y - size);
    }
}
