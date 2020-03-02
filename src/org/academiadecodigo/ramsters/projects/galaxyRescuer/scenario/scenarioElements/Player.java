package org.academiadecodigo.ramsters.projects.galaxyRescuer.scenario.scenarioElements;

import org.academiadecodigo.ramsters.projects.galaxyRescuer.position.Grid;
import org.academiadecodigo.ramsters.projects.galaxyRescuer.position.Movable;
import org.academiadecodigo.ramsters.projects.galaxyRescuer.position.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Player implements Movable, KeyboardHandler {

    private Position position;
    private int xSpeed;
    private int ySpeed;

    private Directions direction = Directions.STOP;

    private Keyboard keyboard;
    private Rectangle rectangle;
    private Grid grid;

    public Player(Grid grid) {

        this.xSpeed = 0;
        this.ySpeed = 0;


        this.grid = grid;

        this.keyboard = new Keyboard(this);

        this.position = new Position(this.grid.getCols()/2, this.grid.getRows(), this.grid);

        this.rectangle = new Rectangle(this.grid.colToX(position.getCol()), this.grid.rowToY(position.getRow()), this.grid.getCellSize(), this.grid.getCellSize());

        this.position.setRectangle(this.rectangle);

    }

    public void init() {

        rectangle.setColor(Color.GREEN);
        rectangle.fill();

        KeyboardEvent upPressed = new KeyboardEvent();
        KeyboardEvent upReleased = new KeyboardEvent();

        KeyboardEvent downPressed = new KeyboardEvent();
        KeyboardEvent downReleased = new KeyboardEvent();

        KeyboardEvent leftPressed = new KeyboardEvent();
        KeyboardEvent leftReleased = new KeyboardEvent();

        KeyboardEvent rightPressed = new KeyboardEvent();
        KeyboardEvent rightReleased = new KeyboardEvent();


        upPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        upReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        upPressed.setKey(KeyboardEvent.KEY_UP);
        upReleased.setKey(KeyboardEvent.KEY_UP);

        downPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        downReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        downPressed.setKey(KeyboardEvent.KEY_DOWN);
        downReleased.setKey(KeyboardEvent.KEY_DOWN);

        leftPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        leftReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        leftPressed.setKey(KeyboardEvent.KEY_LEFT);
        leftReleased.setKey(KeyboardEvent.KEY_LEFT);

        rightPressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rightReleased.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        rightPressed.setKey(KeyboardEvent.KEY_RIGHT);
        rightReleased.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(upPressed);
        keyboard.addEventListener(upReleased);

        keyboard.addEventListener(downPressed);
        keyboard.addEventListener(downReleased);

        keyboard.addEventListener(leftPressed);
        keyboard.addEventListener(leftReleased);

        keyboard.addEventListener(rightPressed);
        keyboard.addEventListener(rightReleased);

    }

    public void collide() {

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:

               direction = Directions.UP;

                break;

            case KeyboardEvent.KEY_DOWN:

               direction = Directions.DOWN;

                break;

            case KeyboardEvent.KEY_LEFT:

               direction = Directions.LEFT;

                break;

            case KeyboardEvent.KEY_RIGHT:

               direction = Directions.RIGHT;

                break;


        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_DOWN:
            case KeyboardEvent.KEY_LEFT:
            case KeyboardEvent.KEY_RIGHT:

                direction = Directions.STOP;

                break;

        }

    }

    @Override
    public void move() {

        if (direction == Directions.UP) {

            ySpeed = -1;

            if (this.position.getRow() + ySpeed <= 0) return;

            position.updateRow(ySpeed);

        }

        if (direction == Directions.DOWN) {

            ySpeed = 1;

            if (this.position.getRow() + ySpeed >= this.grid.getRows()) return;

            position.updateRow(ySpeed);

        }


        if (direction == Directions.LEFT) {

            xSpeed = -1;

            if (this.position.getCol() + xSpeed <= 0) return;

            position.updateCol(xSpeed);

        }

        if (direction == Directions.RIGHT) {

            xSpeed = 1;

            if (this.position.getCol() + xSpeed >= this.grid.getCols()) return;

            position.updateCol(xSpeed);

        }

    }

    public Position getPosition() {

        return position;

    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
