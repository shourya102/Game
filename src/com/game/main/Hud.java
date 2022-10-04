package com.game.main;

import java.awt.*;

public class Hud {
    public static float HEALTH = 100;
    private float greenValue = 255;
    private int score;
    private int level;

    public void tick()
    {
        score++;
        HEALTH = Game.clamp(HEALTH, 100, 0);
        greenValue = Game.clamp(greenValue, 255, 0);
        greenValue = HEALTH * 2;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 30);
        g.setColor(new Color(75, (int) greenValue, 0));
        g.fillRect(15, 15, (int) (HEALTH * 2), 30);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 30);
        g.drawString("Score: " + score, 15, 64 );
        g.drawString("Level: " + level, 15, 80);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
