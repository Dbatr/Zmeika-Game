package org.zmeika;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameField extends Pane {
    private final int SIZE = 640;
    private Snake snake;
    private Apple apple;
    private int score;
    private final Canvas canvas;
    private long lastUpdateTime = 0;
    private final long updateInterval = 200_000_000;
    private final Button restartButton;
    private boolean inGame = true;

    public GameField() {
        canvas = new Canvas(SIZE, SIZE);
        getChildren().add(canvas);
        setBackground(Background.fill(Color.GRAY));
        initGame();
        setOnKeyPressed(this::handleKeyPress);
        setFocusTraversable(true);
        startGameLoop();

        restartButton = new Button("Restart");
        restartButton.setOnAction(e -> restartGame());
        restartButton.setVisible(false);
        VBox vbox = new VBox(restartButton);
        getChildren().add(vbox);
        vbox.setTranslateY(((SIZE - restartButton.getHeight()) / 2) + 30);
        vbox.setTranslateX((SIZE - restartButton.getWidth()) / 2 - 20);
    }

    private void initGame() {
        snake = new Snake();
        apple = new Apple();
        apple.createApple();
    }


    private void startGameLoop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (inGame && now - lastUpdateTime >= updateInterval) {
                    lastUpdateTime = now;
                    checkApple();
                    checkCollisions();
                    snake.move();
                    draw();
                }
            }
        };
        timer.start();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, SIZE, SIZE);
        gc.setFill(Color.RED);
        int DOT_SIZE = 16;
        gc.fillRect(apple.getAppleX(), apple.getAppleY(), DOT_SIZE, DOT_SIZE);
        snake.draw(gc);

        if (!inGame) {
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font(30));
            String gameOverText = "Game Over";
            double textWidth = gc.getFont().getSize() * gameOverText.length() / 2;
            double textHeight = gc.getFont().getSize();
            gc.fillText(gameOverText, (SIZE - textWidth) / 2, (SIZE + textHeight) / 2);
            gc.fillText("Score: " + score, (SIZE - textWidth) / 2 + 20, (SIZE + textHeight) / 2 - 30);
            restartButton.setVisible(true);
        } else {
            gc.setFill(Color.WHITE);
            gc.setFont(javafx.scene.text.Font.font(20));
            gc.fillText("Score: " + score, 10, 20);
        }
    }

    private void checkApple() {
        if (snake.getX(0) == apple.getAppleX() && snake.getY(0) == apple.getAppleY()) {
            snake.grow();
            score += 10;
            apple.createApple();
        }
    }

    private void checkCollisions() {
        if (snake.checkCollision(SIZE)) {
            inGame = false;
        }
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode key = event.getCode();
        if (key == KeyCode.LEFT && !snake.isMovingRight()) {
            snake.moveLeft();
        }
        if (key == KeyCode.RIGHT && !snake.isMovingLeft()) {
            snake.moveRight();
        }
        if (key == KeyCode.UP && !snake.isMovingDown()) {
            snake.moveUp();
        }
        if (key == KeyCode.DOWN && !snake.isMovingUp()) {
            snake.moveDown();
        }
    }

    private void restartGame() {
        inGame = true;
        score = 0;
        snake.reset();
        apple.createApple();
        restartButton.setVisible(false);
        draw();
    }
}