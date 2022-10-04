package com.game.main;

import java.util.Random;

public class Spawn {
    Handler handler;
    Hud hud;
    Random random = new Random();
    private int keepScore;
    Spawn(Handler handler, Hud hud)
    {
        this.handler = handler;
        this.hud = hud;
    }

    void tick()
    {
        keepScore++;
        if(keepScore >= 250){
            keepScore = 0;
            hud.setLevel(hud.getLevel() + 1);
            if(hud.getLevel() == 2)
            {
                handler.addObject(new Enemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
            } else if (hud.getLevel() == 3) {
                handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 4) {
                handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 5) {
                handler.addObject(new Enemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
            } else if (hud.getLevel() == 6) {
                handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 7) {
                handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            } else if (hud.getLevel() == 8) {
                handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
            } else if (hud.getLevel() == 9) {
                handler.addObject(new Enemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
            } else if (hud.getLevel() == 10) {
                handler.clearEnemies();
                handler.addObject(new EnemyBoss(Game.WIDTH/2, -120, ID.EnemyBoss, handler));
            }
        }
    }
}
