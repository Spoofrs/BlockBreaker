package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu {

        BitmapFont text = new BitmapFont(Gdx.files.internal("game-font-white.fnt"));


        public void drawMenu(SpriteBatch batch) {
                Texture button = new Texture("normal-button.png");
                Texture title = new Texture("game-logo-med.png");
                System.out.println("WIDTH: "+Gdx.graphics.getWidth()+" height: "+Gdx.graphics.getHeight());
                batch.draw(title, ((float) Gdx.graphics.getWidth() / 2) - title.getWidth() / 2 + 20, Gdx.graphics.getHeight() - 225);
                batch.draw(button, ((float) Gdx.graphics.getWidth() / 2) - button.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 160, 150, 50);
                batch.draw(button, ((float) Gdx.graphics.getWidth() / 2) - button.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 100, 150, 50);
                batch.draw(button, ((float) Gdx.graphics.getWidth() / 2) - button.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 40, 150, 50);
                text.getData().setScale(0.8f);
                text.setColor(Color.BLACK);
                text.draw(batch, "PLAY", ((float) Gdx.graphics.getWidth() / 2) - 0, Gdx.graphics.getHeight() - 245);
                text.draw(batch, "SETTINGS", ((float) Gdx.graphics.getWidth() / 2) - 30, Gdx.graphics.getHeight() - 305);
                text.draw(batch, "STORE", ((float) Gdx.graphics.getWidth() / 2) - 8, Gdx.graphics.getHeight() - 365);

        }


}
