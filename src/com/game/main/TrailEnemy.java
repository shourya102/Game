package com.game.main;

import java.awt.*;

public class TrailEnemy extends GameObject {

    private Handler handler;

    TrailEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
    }

    
    protected void tick() {
        y += velY;
        x += velX;
    }
    protected void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 16, 16);
    }

    @Override
    protected Rectangle getBounds() {
        return null;
    }
}
