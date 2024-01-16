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
    MainMenu mainMenu;
    Meteorite meteorite;
    Player player;
    BitmapFont text;
    GameInputs gameInputs;
    SpriteBatch batch;
    ShapeRenderer renderer;
    Ball ball;
    Explosion explosion;
    Block block;
    Paddle paddle;
    Laser laser;
    Circle circleListener;
    Rectangle rectangleListener;
    SoundHandler game_music;

    private boolean startGame;

    @Override
    public void create() {
        explosion = new Explosion();
        laser = new Laser();
        paddle = new Paddle(130, 30, 50, 20);
        player = new Player();
        mainMenu = new MainMenu();
        block = new Block();
        meteorite = new Meteorite();
        text = new BitmapFont(Gdx.files.internal("game-font-white.fnt"));
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
        //Init Shape renderer / init spritebatch
        Gdx.gl.glClearColor(13/255f, 8/255f, 28/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();


        if (startGame)
            renderGame();
        else
            mainMenu.drawMenu(batch);

        //End Shape renderer / SpriteBatch
        batch.end();
        renderer.end();

    }


    private void renderGame() {
        //Game clock
        gameclock += Gdx.graphics.getDeltaTime();


        //Player info
        text.getData().setScale(0.5f);
        text.draw(batch, "Lives: " + player.lives, 10, 40);
        text.draw(batch, "Score: " + player.score, 10, 20);

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

    }

}
