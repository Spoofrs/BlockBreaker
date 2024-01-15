package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.GameInputs;
import com.mygdx.game.util.Meteorite;
import com.mygdx.game.util.SoundHandler;


public class BlockBreakerGame extends ApplicationAdapter {

    public static float gameclock;
    Meteorite meteorite;
    Player player = new Player();
    BitmapFont text;
    GameInputs gameInputs;
    SpriteBatch batch;
    ShapeRenderer renderer;
    Ball ball;
    Explosion explosion = new Explosion();
    Block block = new Block();
    Paddle paddle = new Paddle(130, 30, 50, 20);
    Laser laser = new Laser();
    Circle circleListener;
    Rectangle rectangleListener;
    SoundHandler game_music;

    @Override
    public void create() {
        meteorite = new Meteorite();
        text = new BitmapFont();
        gameInputs = new GameInputs();
        block.initBlockSfx();
        Gdx.input.setInputProcessor(gameInputs);
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        circleListener = new Circle();
        rectangleListener = new Rectangle();
        game_music = new SoundHandler("glitch.ogg");
        block.placeBlocks();
        paddle.initPaddleTexture();
        ball = new Ball(10, 3, 4);
        game_music.loopSound(game_music.sound, .75f);
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

        //Player info
        text.draw(batch, "Lives: "+player.lives, 10, 40);
        text.draw(batch, "Score: "+player.score, 10, 20);

        //Laser handling
        laser.shootLaser(ball, gameInputs, paddle);

        laser.drawLaser(renderer, batch);

        //Explosion animation
        explosion.handleExplosionList(batch);

        //Blocks
        block.draw(batch);
        block.checkCollision(circleListener, ball, player);

        //Meteorite spawning
        meteorite.spawnMeteorite(batch, rectangleListener);

        //Paddle
        paddle.update(rectangleListener, batch);

        //Ball
        ball.updateBall(batch, paddle, gameInputs, circleListener, rectangleListener, player);

        //End Shape renderer / SpriteBatch
        batch.end();
        renderer.end();
    }

}
