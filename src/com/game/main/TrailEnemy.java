package com.game.main;

import java.awt.*;

public class TrailEnemy extends GameObject {

    private Handler handler;
    private int width, height;
    float life;
    Color color;
    private float alpha = 1;

    TrailEnemy(int x, int y, ID id, Handler handler, Color color, float life, int width, int height) {
        super(x, y, id);
        this.handler = handler;
        this.width = width;
        this.height = height;
        this.life = life;
        this.color = color;
    }

    
    protected void tick() {
        if(alpha > life)
        {
            alpha -= life - 0.001f;
        }
        else
        {
            handler.removeObject(this);
        }
    }
    protected void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g2d.setColor(color);
        g2d.fillRect((int) x, (int) y, width, height);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    protected Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }
}
