package com.mygdx.game.util;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInputs implements InputProcessor {
    public boolean leftclicked;
    public boolean rightclicked;
    public int mouseX, mouseY;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            leftclicked = true;
            return true;
        }
        if (button == Input.Buttons.RIGHT) {
            rightclicked = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            leftclicked = false;
            return true;
        }
        if (button == Input.Buttons.RIGHT) {
            rightclicked = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseX = screenX;
        mouseY = screenY;
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
