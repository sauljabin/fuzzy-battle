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

public class AnimatedBase extends Animated {

    private int cellSize;
    private Image image;
    private Rectangle rectangle;
    private Image image2;
    private boolean destroy;

    public AnimatedBase(int row, int column) {
        setColumn(column);
        setRow(row);
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        image = ImageLoader.loadImage("shield").getImage();
        image2 = ImageLoader.loadImage("shield-off").getImage();
        rectangle = new Rectangle();
        destroy = false;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
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
        if (destroy)
            g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);
        else
            g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);

    }

    @Override
    public void animate() {

    }

}
