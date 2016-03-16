/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of FuzzyBattle.
 * 
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation.animated;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import app.Config;
import app.animation.Animated;

public class AnimatedLattice extends Animated {

	private Color color;
	private int size;
	private int cellSize;

	@Override
	public void init() {
		size = Integer.parseInt(Config.get("LATTICE_SIZE"));
		cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
		color = new Color(30, 30, 30);
	}

	@Override
	public Shape getShape() {
		return null;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		for (int i = 0; i <= size; i++) {
			g.drawLine(0, i * cellSize, size * cellSize, i * cellSize);
			g.drawLine(i * cellSize, 0, i * cellSize, size * cellSize);
		}
	}

	@Override
	public void animate() {

	}

}
