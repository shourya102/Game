package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private final Handler handler;
    private Game game;
    
    private Random random;

    Menu(Game game, Handler handler)
    {
        random = new Random();
        this.game = game;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mouseOver(mx, my, 250, 200, 130, 30))
        {
            game.gameState = Game.STATE.Game;
            handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
            handler.addObject(new Enemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.Enemy, handler));
        } else if (mouseOver(mx, my, 250, 240, 130, 30)) {
            game.gameState = Game.STATE.Help;
        } else if(mouseOver(mx, my, 250, 280, 130, 30)) {
            System.exit(1);
        } else if (game.gameState == Game.STATE.Help) {
            if(mouseOver(mx, my, 250, 340, 130, 30))
            {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        if(mx >= x && mx <= x + width)
        {
            if(my >= y && my <= y + height)
            {
                return true;
            }
            return false;
        }
        return false;
    }

    public void tick()
    {

    }

    public void render(Graphics graphics)
    {
        if(game.gameState == Game.STATE.Menu)
        {
            graphics.setColor(Color.white);
            Font font = new Font("arial", Font.ITALIC, 28);
            Font font1 = new Font("arial", 1, 18);

            graphics.setFont(font);

            graphics.drawString("Menu!", 280, 150);
            graphics.drawRect(50, 50, 528, 340);

            graphics.setFont(font1);

            graphics.drawString("Play", 295, 220);
            graphics.drawRect(250, 200, 130, 30);

            graphics.drawString("Help", 295, 260);
            graphics.drawRect(250, 240, 130, 30);

            graphics.drawString("Exit", 295, 300);
            graphics.drawRect(250, 280, 130, 30);
        }
        else if (game.gameState == Game.STATE.Help)
        {
            Font fnt = new Font("arial", Font.ITALIC, 28);
            Font fnt2 = new Font("arial", 1, 15);

            graphics.setFont(fnt);
            graphics.setColor(Color.white);
            graphics.drawRect(50, 50, 528, 340);
            graphics.drawString("Help!", 280, 100);

            graphics.setFont(fnt2);

            graphics.drawString("Use the 'WASD' keys to move around in this fast phased game.", 80, 150);
            graphics.drawString("W = Up, A = Left, S = Down, D = Right.", 80, 180);
            graphics.drawString("In this game your goal is to dodge enemies and get as many", 80, 210);
            graphics.drawString("points as possible. Every 10 levels there is a boss enemy.", 80, 240);
            graphics.setFont(fnt2);

            graphics.drawString("Back", 295, 360);
            graphics.drawRect(250, 340, 130, 30);
        }
    }
}
