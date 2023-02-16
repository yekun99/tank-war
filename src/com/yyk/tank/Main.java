package com.yyk.tank;

import java.awt.Frame;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        //初始化敵方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i * 60, 200, Dir.down, Group.BAD, tf));
        }

        while (true) {
            Thread.sleep(30);
            tf.repaint();
        }
    }


}
