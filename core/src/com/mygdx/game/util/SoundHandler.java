package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundHandler {

    public Sound sound;

    public SoundHandler(String soundLocation) {
        this.sound = Gdx.audio.newSound(Gdx.files.internal(soundLocation));
    }

    public void playSound(Sound sound, float volume) {
        sound.play(volume);
    }

    public void loopSound(Sound sound, float volume) {
        sound.loop(volume);
    }

}
