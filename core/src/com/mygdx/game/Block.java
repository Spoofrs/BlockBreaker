package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

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
        Iterator<Rectangle> iterator = Constants.blocks.values().iterator();
        while (iterator.hasNext()) {
            Rectangle blockListener = iterator.next();
            if (Intersector.overlaps(circleListener, blockListener)) {
                iterator.remove();
                ball.changeYDirection();
            }
        }
    }


    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }
}
