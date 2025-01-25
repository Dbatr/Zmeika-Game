package org.zmeika;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake {
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];
    private int dots;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    public Snake() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
    }

    public void draw(GraphicsContext gc) {
        for (int i = 1; i < dots; i++) {
            gc.setFill(Color.GREEN);
            gc.fillRect(x[i], y[i], DOT_SIZE, DOT_SIZE);
        }
        gc.setFill(Color.GREENYELLOW);
        gc.fillRect(x[0], y[0], DOT_SIZE, DOT_SIZE);
    }

    public void grow() {
        dots++;
    }

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }

    public boolean isMovingLeft() {
        return left;
    }

    public boolean isMovingRight() {
        return right;
    }

    public boolean isMovingUp() {
        return up;
    }

    public boolean isMovingDown() {
        return down;
    }

    public void moveLeft() {
        left = true;
        right = false;
        up = false;
        down = false;
    }

    public void moveRight() {
        right = true;
        left = false;
        up = false;
        down = false;
    }

    public void moveUp() {
        up = true;
        down = false;
        left = false;
        right = false;
    }

    public void moveDown() {
        down = true;
        up = false;
        left = false;
        right = false;
    }

    public void reset() {
        dots = 3;
        left = false;
        right = true;
        up = false;
        down = false;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
    }

    public boolean checkCollision(int size) {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                return true;
            }
        }
        return x[0] > size || x[0] < 0 || y[0] > size || y[0] < 0;
    }
}