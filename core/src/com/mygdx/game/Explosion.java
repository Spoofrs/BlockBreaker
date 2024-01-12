package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Iterator;

public class Explosion {

    public static ArrayList<Explosion> explosionlist = new ArrayList<>();

    int x, y;
    Texture img;
    TextureRegion[] animationFrames;
    Animation<TextureRegion> animation;
    float animationTime;

    public Explosion() {

    }

    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        animationTime = 0;
    }

    public void initExplosionTexture() {
        img = new Texture("explosion_sheet.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(img, 96, 96);

        animationFrames = new TextureRegion[12];
        int index = 0;

        for (int i = 0; i < 12; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }

        animation = new Animation<TextureRegion>(0.025f, animationFrames);
    }

    public void getExplosion(int x, int y, SpriteBatch batch, Explosion explosion) {
        explosion.animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(explosion.animationTime, false);
        batch.draw(currentFrame, x, y, 50, 50);
    }

    public void handleExplosionList(SpriteBatch batch) {
        Iterator<Explosion> iterator = explosionlist.listIterator();
        while (iterator.hasNext()) {
            Explosion explosionListener = iterator.next();
            if (explosionListener != null) {
                initExplosionTexture();
                getExplosion(explosionListener.x, explosionListener.y, batch, explosionListener);
                if (animation.isAnimationFinished(explosionListener.animationTime)) {
                    iterator.remove();
                }
            }
        }
    }
}
