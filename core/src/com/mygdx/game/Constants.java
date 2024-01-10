package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;

public class Constants {
    public static final HashMap<Block, Rectangle> blocks = new HashMap<>();
    static Texture blockTexture = new Texture("block_sprite.png");
    static Texture ballTexture = new Texture("ball_sprite.png");
}
