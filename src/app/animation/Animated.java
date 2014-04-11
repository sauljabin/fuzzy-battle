/**
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 *		SAUL PIÑA - SAULJP07@GMAIL.COM
 *		2014
 */

package app.animation;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Vector;

public abstract class Animated implements Comparable<Animated> {
	private Vector<AnimatedMouseListener> animatedMouseListeners;
	private Animator animator;
	private Vector<AnimatedKeyListener> animatedKeyListeners;
	private int x;
	private int y;
	private int z;
	private int width;
	private int height;
	private int row;
	private int column;
	private int angle;
	private boolean isAlive;

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public Animated() {
		animatedKeyListeners = new Vector<AnimatedKeyListener>();
		animatedMouseListeners = new Vector<AnimatedMouseListener>();
	}

	public Vector<AnimatedMouseListener> getAnimatedMouseListeners() {
		return animatedMouseListeners;
	}

	public Vector<AnimatedKeyListener> getAnimatedKeyListeners() {
		return animatedKeyListeners;
	}

	public void addAnimatedMouseListener(AnimatedMouseListener listener) {
		animatedMouseListeners.add(listener);
	}

	public void removeAnimatedMouseListener(AnimatedMouseListener listener) {
		animatedMouseListeners.remove(listener);
	}

	public void addAnimatedKeyListener(AnimatedKeyListener listener) {
		animatedKeyListeners.add(listener);
	}

	public void removeAnimatedKeyListener(AnimatedKeyListener listener) {
		animatedKeyListeners.remove(listener);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Animator getAnimator() {
		return animator;
	}

	public void setAnimator(Animator animator) {
		this.animator = animator;
	}

	@Override
	public int compareTo(Animated animated) {
		if (z < animated.getZ()) {
			return -1;
		} else if (z > animated.getZ()) {
			return 1;
		} else {
			return 0;
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public synchronized void removeAnimated() {
		animator.removeAnimated(this);
		isAlive = false;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public abstract void init();

	public abstract Shape getShape();

	public abstract void paint(Graphics2D g);

	public abstract void animate();
}
