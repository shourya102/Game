package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH/12 *9;
    private Thread thread;
    private boolean running = false;
    private final Handler handler;
    private final Hud hud;
    private final Spawn spawner;
    private final Menu menu;
    public enum STATE{
        Game(),
        Menu(),
        Help(),
        GameOver();
    }

    public STATE gameState = STATE.Menu;

    Game()
    {
        handler = new Handler();
        Random random = new Random();
        hud = new Hud();
        spawner = new Spawn(handler, hud);
        menu = new Menu(this, handler);
        this.addMouseListener(menu);
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "Game", this);
//        if(gameState == STATE.Game)
//        {
//            handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
//            handler.addObject(new Enemy(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.Enemy, handler));
//        }
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try{
            thread.join();
            running = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);
        if(gameState == STATE.Game)
        {
            hud.render(g);
        } else if (gameState == STATE.Menu) {
            menu.render(g);
        } else if (gameState == STATE.Help) {
            menu.render(g);
        } else if(gameState == STATE.GameOver)
        {
            menu.render(g);
            handler.objects.clear();
        }
        g.dispose();
        bs.show();
    }

    private void tick()
    {

        handler.tick();
        if(gameState == STATE.Game)
        {
            hud.tick();
            spawner.tick();
        }
        if(gameState == STATE.Game)
        {
            if(Hud.HEALTH == 0)
            {
                gameState = STATE.GameOver;
            }
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if(running)
            {
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+frames);
                frames = 0;
            }
        }
        stop();
    }

    public static float clamp(float var, float max, float min){
        if(var >= max){
            return max;
        } else if (var <= min) {
            return min;
        }
        else
            return var;
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
