package com.game.main;

import java.awt.*;
import java.util.Random;

public class SmartEnemy extends GameObject{
    private Random random;
    private Handler handler;
    private GameObject player;
    SmartEnemy(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        random = new Random();
        for(int i = 0;i<handler.objects.size();i++)
        {
            if(handler.objects.get(i).getId() == ID.Player)
            {
                this.player = handler.objects.get(i);
            }
        }
    }

    @Override
    protected void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));

        velX = ((-1/distance) * diffX);
        velY = ((-1/distance) * diffY);

//        if(y <= 0 || y >= Game.HEIGHT - 32)
//        {
//            velY *= -1;
//        }
//        if(x <= 0 || x >= Game.WIDTH - 16)
//        {
//            velX *= -1;
//        }

        handler.addObject(new TrailEnemy((int) x, (int) y, ID.Trail, handler, Color.green, 0.03f, 16, 16));
    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }
}
