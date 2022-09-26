package com.game.main;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected  int y;
    protected ID id;



    protected int velX;
    protected int velY;

    GameObject(int x, int y, ID id)
    {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    protected abstract void tick();
    protected abstract void render(Graphics g);

    protected int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected int getY() {
        return y;
    }

    protected abstract Rectangle getBounds();

    protected void setY(int y) {
        this.y = y;
    }

    protected ID getId() {
        return id;
    }

    protected void setId(ID id) {
        this.id = id;
    }

    protected int getVelX() {
        return velX;
    }

    protected void setVelX(int velX) {
        this.velX = velX;
    }

    protected int getVelY() {
        return velY;
    }

    protected void setVelY(int velY) {
        this.velY = velY;
    }
}
