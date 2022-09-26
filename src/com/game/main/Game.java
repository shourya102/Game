package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH/12 *9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random random;

    private Hud hud;
    Game()
    {
        handler = new Handler();
        random = new Random();
        hud = new Hud();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "Game", this);
        handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
        for(int i = 0;i<=4;i++)
        {
            handler.addObject(new Enemy(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.Enemy));
        }
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
        hud.render(g);
        handler.render(g);
        g.dispose();
        bs.show();
    }

    private void tick()
    {
        handler.tick();
        hud.tick();
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

    public static int clamp(int var, int max, int min){
        if(var >= max){
            return max;
        } else if (var <= min) {
            return min;
        }
        else
            return var;
    }

    public static void main(String args[])
    {
        new Game();
    }
}
