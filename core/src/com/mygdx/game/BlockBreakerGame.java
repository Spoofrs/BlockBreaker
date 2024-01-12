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
    GameInputs gameInputs;
    SpriteBatch batch;
    ShapeRenderer renderer;
    Ball ball;
    Explosion explosion = new Explosion();
    Block block = new Block();
    Paddle paddle = new Paddle(120, 10, 50, 20);
    Laser laser = new Laser();
    Circle circleListener;
    Rectangle rectangleListener;
    SoundHandler game_music;

    @Override
    public void create() {
        gameInputs = new GameInputs();
        Gdx.input.setInputProcessor(gameInputs);
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        circleListener = new Circle();
        rectangleListener = new Rectangle();
        game_music = new SoundHandler("glitch.ogg");

        block.placeBlocks();

        paddle.initPaddleTexture();
        ball = new Ball(100, 100, 10, 3, 4);
        game_music.loopSound(game_music.sound, 0.75f);
    }

    @Override
    public void render() {
        //Game clock
        gameclock += Gdx.graphics.getDeltaTime();

        //Init Shape renderer
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        //Start Spritebatch
        batch.begin();

        //Laser handling
        laser.shootLaser(renderer, ball, gameInputs, paddle);

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
        renderer.end();
    }

}
