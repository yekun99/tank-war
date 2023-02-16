package com.yyk.tank;

import java.awt.*;

public class Bullet {
    private static final int speed = 10;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    Rectangle rect = new Rectangle();
    private int x, y;
    private Dir dir;
    private Group group;
    boolean live = true;
    private TankFrame tf = null;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.setGroup(group);
        this.tf = tf;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!live) {
            tf.bullets.remove(this);
        }
        switch (dir) {
            case left:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case rigth:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case up:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case down:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        move();
    }

    public void move() {
        switch (dir) {
            case left:
                x -= speed;
                break;
            case rigth:
                x += speed;
                break;
            case down:
                y += speed;
                break;
            case up:
                y -= speed;
                break;
        }
        //upadte rect
        rect.x =this.x;
        rect.y =this.y;

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_WIDTH) {
            live = false;
        }
    }

    /*
     *碰撞
     */
    public void colliewith(Tank tank) {
        if (this.getGroup() == tank.getGroup()) {
            return;
        }

        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int ex = tank.getX()+ Tank.WIDTH/2-Explode.WIDTH/2;
            int ey = tank.getY()+ Tank.HEIGHT/2-Explode.HEIGHT/2;
            tf.explodes.add(new Explode(ex, ey, tf));
        }
    }

    private void die() {
        this.live = false;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}




