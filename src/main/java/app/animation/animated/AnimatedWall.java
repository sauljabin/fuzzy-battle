/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation.animated;

import app.Config;
import app.animation.Animated;
import app.util.ImageLoader;

import java.awt.*;

public class AnimatedWall extends Animated {

    private int cellSize;
    private Image image;
    private Rectangle rectangle;

    public AnimatedWall(int row, int column) {
        setRow(row);
        setColumn(column);
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        image = ImageLoader.loadImage("brick-wall").getImage();
        rectangle = new Rectangle();
    }

    @Override
    public void init() {
        setWidth(cellSize);
        setHeight(cellSize);
        setX(getColumn() * cellSize);
        setY(getRow() * cellSize);
    }

    @Override
    public Shape getShape() {
        rectangle.setFrame(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void animate() {

    }

}
