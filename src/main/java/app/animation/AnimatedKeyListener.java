/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

import java.awt.event.KeyEvent;

public interface AnimatedKeyListener {

    public void animatedKeyPressed(Animated source, KeyEvent e);

    public void animatedKeyReleased(Animated source, KeyEvent e);
}
