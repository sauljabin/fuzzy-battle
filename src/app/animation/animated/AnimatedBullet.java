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
import java.awt.Rectangle;
import java.awt.Shape;

import javax.sound.sampled.Clip;

import app.Config;
import app.animation.Animated;
import app.util.UtilSound;

public class AnimatedBullet extends Animated {

	public static int DIR_UP = 1;
	public static int DIR_RIGHT = 2;
	public static int DIR_DOWN = 3;
	public static int DIR_LEFT = 4;
	public static int DIR_NONE = 5;
	public int direction;
	private int speed;
	private int latticeSize;
	private int cellSize;
	private Rectangle rectangle;

	private int attack;
	private Animated creator;

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public AnimatedBullet(Animated creator) {
		this.creator = creator;
		latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
		cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public void init() {
		setWidth(10);
		setHeight(4);
		setZ(80);
		speed = 10;
		rectangle = new Rectangle();

		if (Config.get("SOUND_EFFECTS").equals("OFF"))
			return;

		Thread soundThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Clip sound = UtilSound.getClip("sound/tank-firing.wav");
					if (sound != null)
						sound.start();

					if (Config.get("OS").toLowerCase().contains("linux")) {
						Thread.sleep(1000);
						sound.close();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		soundThread.start();
	}

	@Override
	public Shape getShape() {
		rectangle.setFrame(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
		return rectangle;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fillOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
	}

	@Override
	public void animate() {

		for (int i = 0; i < getAnimator().getAnimateds().size(); i++) {
			Animated animated = getAnimator().getAnimateds().get(i);
			if (animated != this && this.getShape() != null && animated.getShape() != null)
				if (this.getShape().intersects(animated.getShape().getBounds2D())) {

					if (animated instanceof AnimatedWall) {
						getAnimator().removeAnimated(animated);
					} else if (animated instanceof AnimatedBase) {
						AnimatedBase base = (AnimatedBase) animated;
						base.setDestroy(true);
						AnimatedGameOver animatedGameOver = new AnimatedGameOver(false);
						getAnimator().addAnimated(animatedGameOver);
					} else if (animated instanceof AnimatedBullet) {
						continue;
					} else if (animated instanceof AnimatedEnemy && creator instanceof AnimatedEnemy) {
						continue;
					} else if (animated instanceof AnimatedPlayer && creator instanceof AnimatedEnemy) {
						AnimatedPlayer player = (AnimatedPlayer) animated;
						player.calculateDamage(attack);
						player.calculateSpeed();
					} else if (animated instanceof AnimatedEnemy && creator instanceof AnimatedPlayer) {
						AnimatedEnemy enemy = (AnimatedEnemy) animated;
						enemy.calculateDamage(attack);
						enemy.calculateSpeed();
					}
					AnimatedImpact animatedImpact = new AnimatedImpact();
					animatedImpact.setX(getX());
					animatedImpact.setY(getY());
					getAnimator().addAnimated(animatedImpact);
					removeAnimated();
				}

		}

		if (direction == DIR_UP) {
			setAngle(90);
			if (getY() - getHeight() / 2 > 0)
				setY(getY() - speed);
			else
				removeAnimated();
		} else if (direction == DIR_DOWN) {
			setAngle(270);
			if (getY() + getHeight() / 2 < latticeSize * cellSize)
				setY(getY() + speed);
			else
				removeAnimated();
		} else if (direction == DIR_LEFT) {
			setAngle(180);
			if (getX() - getWidth() / 2 > 0)
				setX(getX() - speed);
			else
				removeAnimated();
		} else if (direction == DIR_RIGHT) {
			setAngle(0);
			if (getX() + getWidth() / 2 < latticeSize * cellSize)
				setX(getX() + speed);
			else
				removeAnimated();
		}
	}

}
