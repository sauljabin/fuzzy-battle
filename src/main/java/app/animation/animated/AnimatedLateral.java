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

import java.awt.*;

public class AnimatedLateral extends Animated {

    private Color color;
    private int latticeSize;
    private int cellSize;

    @Override
    public void init() {
        latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        setX(latticeSize * cellSize);
        setY(0);
        setZ(5);
        setWidth(250);
        setHeight(getAnimator().getHeight());
        color = Color.GRAY;
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void animate() {

    }

}
