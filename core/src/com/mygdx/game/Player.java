package com.mygdx.game;

public class Player {

    int score;
    int lives;
    boolean isDead;
    //maybe include buttons pressed and pass them into this class instead of gameinputs

    public Player() { //default settings
        this.score = 0;
        this.lives = 3;
        this.isDead = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
