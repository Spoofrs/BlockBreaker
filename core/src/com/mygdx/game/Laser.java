package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Laser {

    public void checkCollision() {

    }

    public void shootLaser(ShapeRenderer renderer, Ball ball, GameInputs gameInputs, Paddle paddle) {
        if(gameInputs.leftclicked && ball.canShoot) {
            renderer.setColor(Color.GREEN);
            renderer.rect(paddle.x + (float) paddle.width / 2, 50, 10, 30); // y doesnt matter atm
            ball.canShoot = false;
        }
    }
}
