package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;


public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    ShapeRenderer shape;
    Ball ball;
    Block block = new Block();
    Paddle paddle = new Paddle(120, 10, 50, 20);
    Circle circleListener;
    Rectangle rectangleListener;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        circleListener = new Circle();
        rectangleListener = new Rectangle();
        paddle.initPaddleTexture();
        ball = new Ball(100, 100, 10, 3, 4);
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += 40 + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += 40 + 10) {
                Constants.blocks.put(new Block(40, 40, x, y), new Rectangle(x, y, 40, 40));
            }
        }
    }

    @Override
    public void render() {

        //Init Shape renderer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        //Block
        batch.begin();
        for (Block block : Constants.blocks.keySet()) {
                batch.draw(Constants.blockTexture, block.x, block.y);
        }
        if (block != null) {
            block.checkCollision(circleListener, ball);
        }

        //Paddle
        paddle.update(rectangleListener, batch);;

        //Ball
        ball.update(circleListener, batch, rectangleListener);

        //End Shape renderer / SpriteBatch
        batch.end();
        shape.end();
    }


}
