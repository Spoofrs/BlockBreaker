package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;
import java.util.Random;

public class Meteorite {

    Random random;
    public static HashMap<Meteorite, Rectangle> meteoriteHashMap = new HashMap<>();
    int x, y;
    Integer[] spawnXLocation = { 80, 160, 240, 320, 400, 480, 560, 640 };

    public Meteorite() {

    }

    public Meteorite(int x) {
        this.x = x;
        this.y = Gdx.graphics.getHeight();
    }

    public void spawnMeteorite(SpriteBatch batch, Rectangle rectangleListener) {
        Texture meteoriteTexture = new Texture("meteorite.png");
        random = new Random();
        int meteoritePosition = random.nextInt(0, 8);
        int randomRoll = random.nextInt(0, 500) + 1; //@TODO balance this so its not overwhelming
        if (randomRoll == 1) {
            meteoriteHashMap.put(new Meteorite(spawnXLocation[meteoritePosition]), new Rectangle(spawnXLocation[meteoritePosition], Gdx.graphics.getHeight(), 40, 60));
        }
        for (Meteorite meteorite : meteoriteHashMap.keySet()) {
            batch.draw(meteoriteTexture, meteorite.x, meteorite.y, 40, 60);
            meteorite.y -= 5;
            //@TODO add iterator to remove gfx / listener when off screen
        }


    }


}
