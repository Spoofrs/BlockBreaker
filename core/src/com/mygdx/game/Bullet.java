package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.GameInputs;

import java.util.HashMap;

public class Bullet {

    int x, y, length, height;


    public static final HashMap<Bullet, Rectangle> laserList = new HashMap<>();

    public Bullet() {}

    public Bullet(int x, int y, int length, int height) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }

    public void shootLaser(Ball ball, GameInputs gameInputs, Paddle paddle) {
        if(gameInputs.leftclicked && ball.canShoot) {
            laserList.put(new Bullet(paddle.x + paddle.width / 2, 5, 50, 50), new Rectangle(paddle.x + ((float) paddle.width / 2) - 10, 20, 20, 20));
            ball.canShoot = false;
        }
    }

    public void drawLaser(ShapeRenderer renderer, SpriteBatch batch) {
        Texture bullet = new Texture("bullet.png");
        for (Rectangle laser : laserList.values()) {
            renderer.setColor(Color.RED);
            laser.y += 10;
            batch.draw(bullet, laser.x, laser.y, laser.width, laser.height);
        }
    }
}
