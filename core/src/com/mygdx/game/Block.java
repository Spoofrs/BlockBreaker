package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Iterator;

public class Block {

    public static final HashMap<Block, Rectangle> blocks = new HashMap<>();
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
        Iterator<Rectangle> iterator = blocks.values().iterator();
        while (iterator.hasNext()) {
            Rectangle blockListener = iterator.next();
            if (Intersector.overlaps(circleListener, blockListener)) {
                iterator.remove();
                Explosion.explosionlist.add(new Explosion((int) blockListener.x, (int) blockListener.y));
                ball.changeYDirection();
            }
        }
    }

    public void placeBlocks() {
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += 40 + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += 40 + 10) {
                blocks.put(new Block(40, 40, x, y), new Rectangle(x, y, 40, 40));
            }
        }
    }

    public void draw(SpriteBatch batch) {
        Texture blockTexture = new Texture("block_sprite.png");
        for (Block block : blocks.keySet()) {
            batch.draw(blockTexture, block.x, block.y);
        }
    }
}
