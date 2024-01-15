package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.GameInputs;
import com.mygdx.game.util.SoundHandler;

import java.awt.event.KeyListener;


public class Ball {
    int x, y, size, xSpeed, ySpeed;
    boolean canShoot, serveBall;
    SoundHandler ball_paddle_collision_sfx = new SoundHandler("paddle_hit.ogg");

    public Ball(int size, int xSpeed, int ySpeed) {
        serveBall = true;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void checkScreenBoundaries(Player player) {
        x += xSpeed;
        y += ySpeed;

        //Handles the boundaries of the game
        if (x < size || x > Gdx.graphics.getWidth() - 20) {
            xSpeed = -xSpeed;
        }
        if (y > Gdx.graphics.getHeight() - size) {
            changeYDirection();
        }
        if (y < size) {
            serveBall = true;
            canShoot = false;
            player.setLives(player.getLives() - 1);
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
    }

    public void updateBall(SpriteBatch batch, Paddle paddle, GameInputs gameInputs, Circle circleListener, Rectangle rectangleListener, Player player) {
        if (gameInputs.leftclicked)
            serveBall = false;
        if (serveBall) {
            Texture ballTexture = new Texture("ball.png");
            x = paddle.x + paddle.width / 2 - 15;
            y = paddle.height * 2;
            batch.draw(ballTexture, x, y, 20, 20);
            canShoot = false;
        } else {
            Texture ballTexture = new Texture("ball.png");
            checkScreenBoundaries(player);
            circleListener.set(x, y, size);
            checkCollision(circleListener, rectangleListener);
            batch.draw(ballTexture, x, y, 20, 20);
        }
    }

}
