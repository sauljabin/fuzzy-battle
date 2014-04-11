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

import javax.sound.sampled.Clip;

import app.Config;
import app.animation.Animated;
import app.util.UtilSound;

public class AnimatedImpact extends Animated {

	@Override
	public void init() {
		setWidth(0);

		if (Config.get("SOUND_EFFECTS").equals("OFF"))
			return;

		Thread soundThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Clip sound = UtilSound.getClip("sound/explosion.wav");
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
		return null;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawOval(getX() - getWidth() / 2, getY() - getWidth() / 2, getWidth(), getWidth());
	}

	@Override
	public void animate() {
		setWidth(getWidth() + 10);
		if (getWidth() > 40)
			removeAnimated();
	}

}
