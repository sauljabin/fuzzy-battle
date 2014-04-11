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

package app.animation.animated;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.ImageIcon;

import app.Config;
import app.animation.Animated;

public class AnimatedBase extends Animated {

	private int cellSize;
	private Image image;
	private Rectangle rectangle;
	private Image image2;
	private boolean destroy;

	public AnimatedBase(int row, int column) {
		setColumn(column);
		setRow(row);
		cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
		image = new ImageIcon("img/shield.png").getImage();
		image2 = new ImageIcon("img/shield2.png").getImage();
		rectangle = new Rectangle();
		destroy = false;
	}

	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
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
		if (destroy)
			g.drawImage(image2, getX(), getY(), getWidth(), getHeight(), null);
		else
			g.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);

	}

	@Override
	public void animate() {

	}

}
