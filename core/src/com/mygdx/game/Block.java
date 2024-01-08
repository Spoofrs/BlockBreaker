package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Block {

    int height, width, x, y;

    public Block() {

    }

    public Block(int height, int width, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public void checkCollision(Circle circleListener, Ball ball) {
        if (!Constants.blocks.isEmpty()) {
            for (Rectangle blockListener : Constants.blocks.values()) {
                if (Intersector.overlaps(circleListener, blockListener)) {
                    System.out.println("BLOCK TO DELETE: " + blockListener);
                    Constants.blocks.remove(blockListener);
                    ball.changeYDirection();
                }
            }
        }
    }


    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }
}
