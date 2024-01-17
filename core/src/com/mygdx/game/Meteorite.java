package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.GameInputs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Meteorite {

    Random random;
    public static HashMap<Meteorite, Rectangle> meteoriteHashMap = new HashMap<>();
    Rectangle meteoriteListener = new Rectangle();
    int x, y;
    Integer[] spawnXLocation = {80, 160, 240, 320, 400, 480, 560, 640};

    public Meteorite() {
    }

    public Meteorite(int x) {
        this.x = x;
        this.y = Gdx.graphics.getHeight();
    }

    public void spawnMeteorite(SpriteBatch batch, Paddle paddle, Player player, Ball ball, GameInputs gameInputs) {
        Texture meteoriteTexture = new Texture("meteorite.png");
        random = new Random();
        int meteoritePosition = random.nextInt(0, 8);
        int randomRoll = random.nextInt(0, 500) + 1;
        if (randomRoll == 1 && meteoriteHashMap.isEmpty()) { //only allow a meteorite to be called if there isn't one on screen already
            meteoriteHashMap.put(new Meteorite(spawnXLocation[meteoritePosition]), new Rectangle(spawnXLocation[meteoritePosition], Gdx.graphics.getHeight(), 40, 60));
        }
        for (Rectangle meteorite : meteoriteHashMap.values()) {
            meteorite.y -= 5;
            batch.draw(meteoriteTexture, meteorite.x, meteorite.y, 40, 60);
            meteoriteListener.set(meteorite.x, meteorite.y, 40, 40);
        }
        //Intellij recommends this:
        // meteoriteHashMap.values().removeIf(meteoriteListener -> meteoriteListener.y <= -100);
        // reduces all code from line 44 to 48 with that, @TODO look into it and understand it (pretty sure its lambda?)
        Iterator<Rectangle> meteoriteIterator = meteoriteHashMap.values().iterator();
        while (meteoriteIterator.hasNext()) {
            Rectangle meteoriteListener = meteoriteIterator.next();
            if (Intersector.overlaps(meteoriteListener, paddle.paddleListener)) {
                if (!gameInputs.rightclicked) {
                    player.lives -= 1;
                    ball.serveBall = true;
                    meteoriteIterator.remove();
                } else {
                    meteoriteIterator.remove();
                    player.score += 50;
                }
            }
            if (meteoriteListener.y <= -100) { // when meteorite is off-screen we remove it from the hashmap
                meteoriteIterator.remove();
            }
        }
    }


}
