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
import app.Translate;
import app.animation.Animated;

import java.awt.*;

public class AnimatedInfoPlayer extends Animated {

    private AnimatedPlayer player;
    private int latticeSize;
    private int cellSize;
    private int sizeBarLife;
    private int sizeBarRes;
    private int sizeBarSpeed;
    private Font font;

    public AnimatedInfoPlayer(AnimatedPlayer player) {
        this.player = player;
    }

    @Override
    public void init() {
        latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
        setX(latticeSize * cellSize + 40);
        setY(50);
        setAngle(180);
        setZ(500);
        setWidth(cellSize - 4);
        setHeight(cellSize - 4);
        font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void paint(Graphics2D g) {

        int x = getX() - getWidth() / 2;
        int y = getY() - getHeight() / 2;

        g.setColor(Color.BLUE);
        g.fillRoundRect(x, y, getWidth(), getHeight(), 10, 10);

        g.setColor(Color.black);
        g.fillRoundRect(getX() + 4, getY() - 2, 25, 4, 3, 3);
        g.fillRoundRect(getX() + 25, getY() - 4, 6, 8, 3, 3);
        g.fillOval(getX() - 12, getY() - 10, 24, 20);
        g.setColor(Color.gray);
        g.fillRoundRect(getX() + 5, getY() - 1, 23, 2, 3, 3);
        g.fillRoundRect(getX() + 26, getY() - 3, 4, 6, 3, 3);
        g.fillOval(getX() - 11, getY() - 9, 22, 18);
        g.setColor(Color.white);
        g.fillOval(getX() - 5, getY() + 3, 10, 3);

        g.rotate(-Math.toRadians(getAngle()), getX(), getY());
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString(Translate.get("GUI_LIFE"), x, y + getHeight() / 2 + 40);
        g.setColor(Color.red);
        g.fillRect(x, y + getHeight() / 2 + 45, 150, 5);
        g.setColor(Color.green);
        g.fillRect(x, y + getHeight() / 2 + 45, sizeBarLife, 5);

        g.setColor(Color.black);
        g.drawString(Translate.get("GUI_RESISTANCE"), x, y + getHeight() / 2 + 65);
        g.setColor(Color.red);
        g.fillRect(x, y + getHeight() / 2 + 70, 150, 5);
        g.setColor(Color.green);
        g.fillRect(x, y + getHeight() / 2 + 70, sizeBarRes, 5);

        g.setColor(Color.black);
        g.drawString(Translate.get("GUI_SPEED"), x, y + getHeight() / 2 + 90);
        g.setColor(Color.red);
        g.fillRect(x, y + getHeight() / 2 + 95, 150, 5);
        g.setColor(Color.green);
        g.fillRect(x, y + getHeight() / 2 + 95, sizeBarSpeed, 5);

        g.rotate(Math.toRadians(getAngle()), getX(), getY());

    }

    @Override
    public void animate() {
        sizeBarLife = player.getLife() * 150 / 100;
        sizeBarRes = player.getResistance() * 150 / 100;
        sizeBarSpeed = player.getSpeed() * 150 / 6;
    }

}
