package com.yyk.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x;
    private int y;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    Rectangle rect = new Rectangle();
    private static final int speed = 5;
    private Dir dir = Dir.down;
    private Group group = Group.BAD;
    private boolean moving = true;
    private TankFrame tf;
    private boolean live = true;
    private Random random = new Random();

    public Dir getDir() {
        return dir;
    }

    public void SetDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        super();
        this.setX(x);
        this.setY(y);
        this.dir = dir;
        this.setGroup(group);
        this.tf = tf;
        rect.x =this.x;
        rect.y = this.y;
        rect.width =WIDTH;
        rect.height =HEIGHT;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.tanks.remove(this);
            return;
        }

        switch (dir) {
            case left:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, getX(), getY(), null);
                break;
            case rigth:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, getX(), getY(), null);
                break;
            case up:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, getX(), getY(), null);
                break;
            case down:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, getX(), getY(), null);
                break;
        }


        move();
    }

    public void move() {
        if (!isMoving()) {
            return;
        }
        switch (dir) {
            case left:
                setX(getX() - speed);
                break;
            case rigth:
                setX(getX() + speed);
                break;
            case down:
                setY(getY() + speed);
                break;
            case up:
                setY(getY() - speed);
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        if (this.group == Group.BAD && random.nextInt(100) > 90) {
            this.dir = Dir.values()[random.nextInt(4)];
        }

        boundsCheck();

        //update rect
        rect.x=this.x;
        rect.y =this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }


    public void fire() {
        int bx = this.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bx, by, this.dir, getGroup(), this.tf));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void die() {
        this.live = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
