/**
 * Copyright (c) 2016 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of FuzzyBattle.
 * 
 * FuzzyBattle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * FuzzyBattle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with FuzzyBattle.  If not, see <http://www.gnu.org/licenses/>.
 */

package app.animation.animated;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import app.Config;
import app.animation.Animated;

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
