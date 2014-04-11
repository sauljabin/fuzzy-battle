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
