package com.game.main;

import java.awt.*;

public class Hud {
    public static int HEALTH = 100;

    public void tick()
    {
        HEALTH = Game.clamp(HEALTH, 100, 0);
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 30);
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 30);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 30);
    }
}
