package com.yyk.tank;

import java.awt.*;

public class Explode {

    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x, y;
    TankFrame tf = null;

    private int step = 0;


    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes[step++], x,y,null);
        if(step>=ResourceMgr.explodes.length) {
            tf.explodes.remove(this);
        }
    }
}
