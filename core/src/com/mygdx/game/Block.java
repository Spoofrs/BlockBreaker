package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.SoundHandler;

import java.util.HashMap;
import java.util.Iterator;

public class Block {

    SoundHandler block_death_sfx;
    public static final HashMap<Block, Rectangle> blocks = new HashMap<>();
    int height, width, x, y;

    public Block() {
    }

    public void initBlockSfx() {
        block_death_sfx = new SoundHandler("explosion_sfx.ogg");
    }

    public Block(int height, int width, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public void checkCollision(Circle circleListener, Ball ball, Player player, Paddle paddle) {
        Iterator<Rectangle> blockIterator = blocks.values().iterator();
        while (blockIterator.hasNext()) {
            Rectangle blockListener = blockIterator.next();
            Iterator<Rectangle> laserIterator = Bullet.laserList.values().iterator();
            while (laserIterator.hasNext()) {
                Rectangle laserListener = laserIterator.next();
                if (Intersector.overlaps(blockListener, laserListener)) {
                    block_death_sfx.playSound(block_death_sfx.sound,0.5f);
                    Explosion.explosionlist.add(new Explosion((int) blockListener.x, (int) blockListener.y));
                    blockIterator.remove();
                    laserIterator.remove();
                    player.setScore(player.getScore() + 10);

                }
                if (laserListener.y > Gdx.graphics.getHeight()) {
                    laserIterator.remove();
                }
            }
            if (Intersector.overlaps(circleListener, blockListener)) {
                block_death_sfx.playSound(block_death_sfx.sound,0.5f);
                player.setScore(player.getScore() + 10);
                blockIterator.remove();
                Explosion.explosionlist.add(new Explosion((int) blockListener.x, (int) blockListener.y));
                ball.changeYDirection();
                ball.changeXDirection(circleListener, paddle, true);
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
        Texture blockTexture = new Texture("brick.png");
        for (Block block : blocks.keySet()) {
            batch.draw(blockTexture, block.x, block.y, 40, 40);
        }
    }
}
