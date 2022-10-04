package com.game.main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject{
    private Random random;
    private Handler handler;
    private int timer;
    EnemyBoss(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        random = new Random();
        velX = 0;
        velY = 3;
        timer = 50;
    }

    @Override
    protected void tick() {
        x += velX;
        y += velY;
        if(timer == 0)
        {
            velY = 0;
            if(velX == 0)
                velX = 2;
        }
        else
        {
            timer--;
        }
        if(timer <= 0)
        {
            int spawn = random.nextInt(10);
            if(spawn == 0)
            {
                handler.addObject(new EnemyBossBullet((int)x + 48, (int)y +48, ID.Enemy, handler));
            }
            if(velX > 0)
            {
                velX += 0.005f;
            } else if (velX < 0) {
                velX -= 0.005f;
            }
            velX = Game.clamp(velX, 8, -8);
        }
//        if(y <= 0 || y >= Game.HEIGHT - 32)
//        {
//            velY *= -1;
//        }
        if(x <= 0 || x >= Game.WIDTH - 108)
        {
            velX *= -1;
        }
        //handler.addObject(new TrailEnemy((int) x, (int) y, ID.Trail, handler, Color.red, 0.03f, 16, 16));
    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 96, 96);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }
}
