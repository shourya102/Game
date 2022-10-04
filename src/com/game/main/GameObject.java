package com.game.main;

import java.awt.*;

public abstract class GameObject {
    protected float x;
    protected  float y;
    protected ID id;



    protected float velX;
    protected float velY;

    GameObject(float x, float y, ID id)
    {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    protected abstract void tick();
    protected abstract void render(Graphics g);

    protected float getX() {
        return x;
    }

    protected void setX(float x) {
        this.x = x;
    }

    protected float getY() {
        return y;
    }

    protected abstract Rectangle getBounds();

    protected void setY(float y) {
        this.y = y;
    }

    protected ID getId() {
        return id;
    }

    protected void setId(ID id) {
        this.id = id;
    }

    protected float getVelX() {
        return velX;
    }

    protected void setVelX(float velX) {
        this.velX = velX;
    }

    protected float getVelY() {
        return velY;
    }

    protected void setVelY(float velY) {
        this.velY = velY;
    }
}
