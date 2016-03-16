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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import app.animation.Animated;
import app.animation.AnimatedMouseListener;

public class AnimatedButton extends Animated {

	private Color backColor;
	private Color fontColor;
	private Font font;
	private String text;
	private int textX;
	private Rectangle rectangle;

	public int getTextX() {
		return textX;
	}

	public void setTextX(int textX) {
		this.textX = textX;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public AnimatedButton() {
		addAnimatedMouseListener(new AnimatedMouseListener() {
			@Override
			public void animatedMousePressed(Animated source) {
				backColor = new Color(40, 40, 40);
			}

			@Override
			public void animatedMouseReleased(Animated source) {
				backColor = Color.BLACK;
			}
		});
	}

	@Override
	public void init() {
		setWidth(150);
		setHeight(40);
		setZ(10);

		backColor = Color.BLACK;
		fontColor = Color.WHITE;
		font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		rectangle = new Rectangle();
	}

	@Override
	public Shape getShape() {
		rectangle.setFrame(getX(), getY(), getWidth(), getHeight());
		return rectangle;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(backColor);
		g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 10, 10);
		g.setFont(font);
		g.setColor(fontColor);
		g.drawString(text, getX() + textX, getY() + 28);
	}

	@Override
	public void animate() {

	}

}
