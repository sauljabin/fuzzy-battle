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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;

import app.Translate;
import app.animation.Animated;

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
