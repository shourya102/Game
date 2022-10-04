package com.game.main;

import java.awt.*;

public class Player extends GameObject{

    private Handler handler;

    Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }


    protected void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, Game.WIDTH - 47, 0);
        y = Game.clamp(y, Game.HEIGHT - 69, 0);
        collision();
    }

    public void collision(){
        for(int i = 0;i< handler.objects.size();i++)
        {
            GameObject tempObject = handler.objects.get(i);
            if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss)
            {
                if(getBounds().intersects(tempObject.getBounds()) == true)
                {
                    if(tempObject.getId() == ID.EnemyBoss)
                    {
                        Hud.HEALTH-=1000;
                    }
                    Hud.HEALTH-=2;
                }
            }
        }
    }



    protected void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
