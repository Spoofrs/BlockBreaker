package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.GameInputs;

import java.util.HashMap;

public class Laser {

    int x, y, length, height;


    public static final HashMap<Laser, Rectangle> laserList = new HashMap<>();

    public Laser() {}

    public Laser(int x, int y, int length, int height) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }

    public void shootLaser(Ball ball, GameInputs gameInputs, Paddle paddle) {
        if(gameInputs.leftclicked && ball.canShoot) {
            laserList.put(new Laser(paddle.x + paddle.width / 2, 5, 50, 50), new Rectangle(paddle.x + ((float) paddle.width / 2) - 10, 20, 20, 20));
            ball.canShoot = false;
        }
    }

    public void drawLaser(ShapeRenderer renderer) {
        for (Rectangle laser : laserList.values()) { //@TODO add texture
            laser.y += 10;
            renderer.rect(laser.x, laser.y, laser.width, laser.height);
        }
    }
}
