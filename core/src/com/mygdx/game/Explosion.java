package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Iterator;

public class Explosion {

    ArrayList<Explosion> explosionlist = new ArrayList<>();

    int x, y;
    Texture img;
    TextureRegion[] animationFrames;
    Animation animation;
    float deltatime;

    public Explosion() {

    }

    public Explosion(int x, int y, float gameclock) {
        this.x = x;
        this.y = y;
        this.deltatime = gameclock;
    }

    public void init() {
        img = new Texture("explosion_sheet.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(img, 96, 96);

        animationFrames = new TextureRegion[12];
        int index = 0;

        for (int i = 0; i < 12; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }

        animation = new Animation<>(0.025f, animationFrames);
    }

    public void getExplosion(int x, int y, float deltatime, SpriteBatch batch) {
        TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(deltatime, true);
        batch.draw(currentFrame, x, y, 50, 50);
    }

    public void handleExplosionList(float gameclock, SpriteBatch batch) {
        Iterator<Explosion> iterator = explosionlist.listIterator();
        while (iterator.hasNext()) {
            Explosion explosionListener = iterator.next();
            if (explosionListener != null) {
                init();
                getExplosion(explosionListener.x, explosionListener.y, gameclock, batch);
                if (explosionListener.deltatime + 0.25f < gameclock)
                    iterator.remove();
            }
        }
    }
}
