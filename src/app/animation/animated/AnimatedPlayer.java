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
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.Random;

import net.sourceforge.jFuzzyLogic.FIS;
import app.Config;
import app.animation.Animated;
import app.animation.AnimatedKeyListener;

public class AnimatedPlayer extends Animated implements AnimatedKeyListener {

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
	private boolean move;

	private int life;
	private int resistance;
	private int attack;
	private Random rand;
	private FIS fis;

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public AnimatedPlayer(int row, int column) {
		setColumn(column);
		setRow(row);
		latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
		cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
		addAnimatedKeyListener(this);

		rand = new Random();
		resistance = 61 + rand.nextInt(40);
		attack = 61 + rand.nextInt(40);
		life = 100;
		fis = FIS.load(Config.get("RULES_PATH"), true);
		calculateSpeed();

	}

	public void calculateSpeed() {
		fis.setVariable("life", life);
		fis.evaluate();
		speed = (int) fis.getVariable("speed").defuzzify();
	}

	public void calculateDamage(int attack) {
		fis.setVariable("life", life);
		fis.setVariable("attack", attack);
		fis.setVariable("resistance", resistance);
		fis.evaluate();
		life -= fis.getVariable("damage").defuzzify();
		if (life <= 0) {
			removeAnimated();
			AnimatedGameOver animatedGameOver = new AnimatedGameOver(false);
			getAnimator().addAnimated(animatedGameOver);
		}
	}

	@Override
	public void init() {
		setWidth(cellSize - 4);
		setHeight(cellSize - 4);
		setX(getColumn() * cellSize + getWidth() / 2);
		setY(getRow() * cellSize + getHeight() / 2);
		setZ(100);
		speed = 6;
		rectangle = new Rectangle();
		setDirection(DIR_RIGHT);
	}

	@Override
	public Shape getShape() {
		rectangle.setFrame(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
		return rectangle;
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

	}

	public int intersect(int x, int y, int w, int h, int direction) {
		for (int i = 0; i < getAnimator().getAnimateds().size(); i++) {
			Animated animated = getAnimator().getAnimateds().get(i);
			if (animated == this || animated.getShape() == null || animated instanceof AnimatedBullet)
				continue;

			if (direction == DIR_UP) {
				for (int j = 0; j <= speed; j++) {
					if (animated.getShape().intersects(x, y - j - 1, w, h))
						return j;
				}
			} else if (direction == DIR_DOWN) {
				for (int j = 0; j <= speed; j++) {
					if (animated.getShape().intersects(x, y + j + 1, w, h))
						return j;
				}
			} else if (direction == DIR_LEFT) {
				for (int j = 0; j <= speed; j++) {
					if (animated.getShape().intersects(x - j - 1, y, w, h))
						return j;
				}

			} else if (direction == DIR_RIGHT) {
				for (int j = 0; j <= speed; j++) {
					if (animated.getShape().intersects(x + j + 1, y, w, h))
						return j;
				}
			}

		}
		return speed;
	}

	@Override
	public void animate() {
		int speed = 0;
		if (direction == DIR_UP) {
			setAngle(90);
			speed = intersect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), 1, direction);
			if (move && getY() - getHeight() / 2 > 0)
				setY(getY() - speed);

		} else if (direction == DIR_DOWN) {
			setAngle(270);
			speed = intersect(getX() - getWidth() / 2, getY() + getHeight() / 2, getWidth(), 1, direction);
			if (move && getY() + getHeight() / 2 < latticeSize * cellSize)
				setY(getY() + speed);

		} else if (direction == DIR_LEFT) {
			setAngle(180);
			speed = intersect(getX() - getWidth() / 2, getY() - getHeight() / 2, 1, getHeight(), direction);
			if (move && getX() - getWidth() / 2 > 0)
				setX(getX() - speed);

		} else if (direction == DIR_RIGHT) {
			setAngle(0);
			speed = intersect(getX() + getWidth() / 2, getY() - getHeight() / 2, 1, getHeight(), direction);
			if (move && getX() + getWidth() / 2 < latticeSize * cellSize)
				setX(getX() + speed);
		}

		int enemys = 0;
		for (int i = 0; i < getAnimator().getAnimateds().size(); i++) {
			Animated animated = getAnimator().getAnimateds().get(i);
			if (animated instanceof AnimatedEnemy)
				enemys++;
		}

		if (enemys == 0) {
			AnimatedGameOver animatedGameOver = new AnimatedGameOver(true);
			getAnimator().addAnimated(animatedGameOver);
		}

	}

	@Override
	public void animatedKeyPressed(Animated source, KeyEvent e) {

		AnimatedPlayer animated = (AnimatedPlayer) source;

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			animated.setDirection(AnimatedPlayer.DIR_UP);
			move = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			animated.setDirection(AnimatedPlayer.DIR_DOWN);
			move = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			animated.setDirection(AnimatedPlayer.DIR_LEFT);
			move = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			animated.setDirection(AnimatedPlayer.DIR_RIGHT);
			move = true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			fire();
		}

	}

	public void fire() {
		AnimatedBullet animatedBullet = new AnimatedBullet(this);
		animatedBullet.setDirection(getDirection());
		int space = 10;
		if (direction == DIR_DOWN) {
			animatedBullet.setY(getY() + getHeight() / 2 + space);
			animatedBullet.setX(getX());
		} else if (direction == DIR_UP) {
			animatedBullet.setY(getY() - getHeight() / 2 - space);
			animatedBullet.setX(getX());
		} else if (direction == DIR_LEFT) {
			animatedBullet.setY(getY());
			animatedBullet.setX(getX() - getWidth() / 2 - space);
		} else if (direction == DIR_RIGHT) {
			animatedBullet.setY(getY());
			animatedBullet.setX(getX() + getWidth() / 2 + space);
		}
		animatedBullet.setAttack(attack);
		getAnimator().addAnimated(animatedBullet);
	}

	@Override
	public void animatedKeyReleased(Animated source, KeyEvent e) {
		move = false;
	}

}
