package com.game.main;

import java.awt.*;
import java.util.Random;

public class FastEnemy extends GameObject{
    private Random random;
    private Handler handler;
    FastEnemy(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        random = new Random();
        velX = 2;
        velY = 9;
    }

    @Override
    protected void tick() {
        x += velX;
        y += velY;
        if(y <= 0 || y >= Game.HEIGHT - 32)
        {
            velY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH - 16)
        {
            velX *= -1;
        }
        handler.addObject(new TrailEnemy((int) x, (int) y, ID.Trail, handler, Color.CYAN, 0.03f, 16, 16));
    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }
}
