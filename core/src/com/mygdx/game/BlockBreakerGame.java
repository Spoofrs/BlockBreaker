package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;


public class BlockBreakerGame extends ApplicationAdapter {

    public static float gameclock;
    SpriteBatch batch;
    ShapeRenderer shape;
    Ball ball;
    Explosion explosion = new Explosion();
    Block block = new Block();
    Paddle paddle = new Paddle(120, 10, 50, 20);
    Circle circleListener;
    Rectangle rectangleListener;

    @Override
    public void create() {
        //Init classes
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        circleListener = new Circle();
        rectangleListener = new Rectangle();

        block.placeBlocks();

        //Init objects
        paddle.initPaddleTexture();
        ball = new Ball(100, 100, 10, 3, 4);
    }

    @Override
    public void render() {
        //Game clock
        gameclock += Gdx.graphics.getDeltaTime();

        //Init Shape renderer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        //Start Spritebatch
        batch.begin();

        //Explosion animation
        explosion.handleExplosionList(batch);

        //Blocks
        block.draw(batch);
        block.checkCollision(circleListener, ball);

        //Paddle
        paddle.update(rectangleListener, batch);

        //Ball
        ball.update(circleListener, batch, rectangleListener);

        //End Shape renderer / SpriteBatch
        batch.end();
        shape.end();
    }

}
