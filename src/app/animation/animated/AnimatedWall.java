/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of FuzzyBattle.
 * 
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation.animated;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.ImageIcon;

import app.Config;
import app.animation.Animated;

public class AnimatedWall extends Animated {

	private int cellSize;
	private Image image;
	private Rectangle rectangle;

	public AnimatedWall(int row, int column) {
		setRow(row);
		setColumn(column);
		cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
		image = new ImageIcon("img/brick_wall.jpg").getImage();
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
