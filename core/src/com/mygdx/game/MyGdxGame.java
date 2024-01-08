package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {

    ShapeRenderer shape;
    Ball ball;
    Block block = new Block();
    Paddle paddle;
    Circle circleListener;
    Rectangle rectangleListener;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        circleListener = new Circle();
        rectangleListener = new Rectangle();
        ball = new Ball(200, 200, 25, 5, 5);
        paddle = new Paddle(150, 15, 50, 20);
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += 20 + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += 63 + 10) {
                Constants.blocks.put(new Block(20, 63, x, y), new Rectangle(x, y, 63, 20));
            }
        }
        for (Rectangle blockListener : Constants.blocks.values()) {
            System.out.println("HOLDS: " + blockListener);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Block block : Constants.blocks.keySet()) {
                block.draw(shape);
        }
        paddle.update();
        paddle.drawPaddle(shape, rectangleListener);
        ball.checkCollision(circleListener, rectangleListener);
        ball.update();
        if (block != null)
            block.checkCollision(circleListener, ball);
        ball.draw(shape, circleListener);
        shape.end();
    }
}
