/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import app.animation.Animator;

public class ViewGame extends View {

    private Animator animator;

    @Override
    public void init() {
        setSize(800, 635);
        animator = new Animator();
        add(animator);
    }

    public Animator getAnimator() {
        return animator;
    }
}
