package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.GameInputs;

public class MainMenu {

        BitmapFont text = new BitmapFont(Gdx.files.internal("game-font-white.fnt"));
        GameInputs gameInputs = new GameInputs();
        Rectangle playButtonListener = new Rectangle(265,  230, 150, 50);
        Rectangle settingsButtonListener = new Rectangle(265,  290, 150, 50);
        Rectangle storeButtonListener = new Rectangle(265,  350, 150, 50);
        Texture buttonNormal = new Texture("normal-button.png");
        Texture hoverButton = new Texture("clicked-button.png");

        public void drawMenu(SpriteBatch batch) {
                Gdx.input.setInputProcessor(gameInputs);
                Texture title = new Texture("game-logo-med.png");
                batch.draw(title, ((float) Gdx.graphics.getWidth() / 2) - title.getWidth() / 2 + 20, Gdx.graphics.getHeight() - 225);
                batch.draw(buttonNormal, ((float) Gdx.graphics.getWidth() / 2) - buttonNormal.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 40, 150, 50);
                batch.draw(buttonNormal, ((float) Gdx.graphics.getWidth() / 2) - buttonNormal.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 100, 150, 50);
                batch.draw(buttonNormal, ((float) Gdx.graphics.getWidth() / 2) - buttonNormal.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 160, 150, 50);
                text.getData().setScale(0.8f);
                text.setColor(Color.BLACK);
                text.draw(batch, "PLAY", ((float) Gdx.graphics.getWidth() / 2) - 0, Gdx.graphics.getHeight() - 245);
                text.draw(batch, "SETTINGS", ((float) Gdx.graphics.getWidth() / 2) - 30, Gdx.graphics.getHeight() - 305);
                text.draw(batch, "STORE", ((float) Gdx.graphics.getWidth() / 2) - 8, Gdx.graphics.getHeight() - 365);
                checkActions(batch);

        }

        private void checkActions(SpriteBatch batch) {
                if (Gdx.input.isTouched()) {
                        if (playButtonListener.contains(gameInputs.mouseX, gameInputs.mouseY)) {
                                System.out.println("PLAY BUTTON CLICK");

                        }
                        if (settingsButtonListener.contains(gameInputs.mouseX, gameInputs.mouseY)) {
                                System.out.println("SETTINGS BUTTON CLICK");
                        }
                        if (storeButtonListener.contains(gameInputs.mouseX, gameInputs.mouseY)) {
                                System.out.println("STORE BUTTON CLICK");
                        }
                }
                if (playButtonListener.contains(gameInputs.mouseX, gameInputs.mouseY)) {
                        System.out.println("PLAY BUTTON HOVER");
                        batch.draw(hoverButton, ((float) Gdx.graphics.getWidth() / 2) - buttonNormal.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 40, 150, 50);
                }
                if (settingsButtonListener.contains(gameInputs.mouseX, gameInputs.mouseY)) {
                        System.out.println("SETTINGS BUTTON HOVER");
                        batch.draw(hoverButton, ((float) Gdx.graphics.getWidth() / 2) - buttonNormal.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 100, 150, 50);
                }
                if (storeButtonListener.contains(gameInputs.mouseX, gameInputs.mouseY)) {
                        System.out.println("STORE BUTTON HOVER");
                        batch.draw(hoverButton, ((float) Gdx.graphics.getWidth() / 2) - buttonNormal.getWidth(), (float) Gdx.graphics.getHeight() / 2 - 160, 150, 50);
                }
        }
}
