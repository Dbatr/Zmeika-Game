package org.zmeika;

import java.util.Random;

public class Apple {
    private int appleX;
    private int appleY;

    public void createApple() {
        int DOT_SIZE = 16;
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
    }

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }
}