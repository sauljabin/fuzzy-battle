/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation.animated;

import app.Translate;
import app.animation.Animated;

import java.awt.*;

public class AnimatedGameOver extends Animated {

    private boolean win;
    private Font font;

    public AnimatedGameOver(boolean win) {
        this.win = win;
        font = new Font(Font.SANS_SERIF, Font.BOLD, 100);
    }

    @Override
    public void init() {
        setX(60);
        setY(320);
        setZ(1000);
        getAnimator().setGameFinish(true);
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(font);
        if (win)
            g.drawString(Translate.get("GUI_YOUWIN"), getX(), getY());
        else
            g.drawString(Translate.get("GUI_YOULOSE"), getX(), getY());
    }

    @Override
    public void animate() {

    }

}
