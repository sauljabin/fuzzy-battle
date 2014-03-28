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

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Vector;

public class Animator extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private Vector<Animated> animateds;
	private int FPS;
	private boolean pause;
	private Thread thread;
	private boolean stop;
	private Image image;

	public boolean isPause() {
		return pause;
	}

	public boolean isStop() {
		return stop;
	}

	public void addAnimated(Animated animated) {
		animateds.add(animated);
		animated.setAnimator(this);
		animated.init();
	}

	public void removeAnimated(Animated animated) {
		animateds.remove(animated);
	}

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int fPS) {
		FPS = fPS;
	}

	public Animator() {
		FPS = 24;
		animateds = new Vector<Animated>();
		thread = new Thread(this);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				animatorMousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				animatorMouseReleased(e);
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("holsss");
				animatorKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				animatorKeyReleased(e);
			}

		});
	}

	public void makeImage() {
		image = createImage(getWidth(), getHeight());
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	public void animatorKeyPressed(KeyEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			for (int j = 0; j < animateds.get(i).getAnimatedKeyListeners().size(); j++) {
				animateds.get(i).getAnimatedKeyListeners().get(j).animatedKeyPressed(animateds.get(i));
			}
		}
	}

	public void animatorKeyReleased(KeyEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			for (int j = 0; j < animateds.get(i).getAnimatedKeyListeners().size(); j++) {
				animateds.get(i).getAnimatedKeyListeners().get(j).animatedKeyReleased(animateds.get(i));
			}
		}
	}

	public void animatorMouseReleased(MouseEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			if (animateds.get(i).getShape() != null) {
				for (int j = 0; j < animateds.get(i).getAnimatedMouseListeners().size(); j++) {
					if (animateds.get(i).getShape().contains(e.getPoint())) {
						animateds.get(i).getAnimatedMouseListeners().get(j).animatedMouseReleased(animateds.get(i));
					}
				}
			}
		}
	}

	public void animatorMousePressed(MouseEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			if (animateds.get(i).getShape() != null) {
				for (int j = 0; j < animateds.get(i).getAnimatedMouseListeners().size(); j++) {
					if (animateds.get(i).getShape().contains(e.getPoint())) {
						animateds.get(i).getAnimatedMouseListeners().get(j).animatedMousePressed(animateds.get(i));
					}
				}
			}
		}
	}

	public void start() {
		makeImage();
		thread.start();
		stop = false;
		pause = false;
	}

	public void stop() {
		stop = true;
		pause = true;
	}

	public void pause() {
		pause = true;
	}

	public void resume() {
		pause = false;
	}

	@Override
	public void run() {
		while (!stop) {
			Collections.sort(animateds);

			rendering();

			if (!pause) {
				animate();
			}

			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void animate() {
		for (int i = 0; i < animateds.size(); i++) {
			animateds.get(i).animate();
		}
	}

	public void rendering() {
		image.getGraphics().clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < animateds.size(); i++) {
			animateds.get(i).paint(image.getGraphics());
		}
		getGraphics().drawImage(image, 0, 0, this);
	}

	public void clear() {
		image.getGraphics().clearRect(0, 0, getWidth(), getHeight());
		getGraphics().drawImage(image, 0, 0, this);
	}

	public void restart() {
		initAnimateds();
	}

	public void initAnimateds() {
		for (int i = 0; i < animateds.size(); i++) {
			animateds.get(i).init();
		}
	}
}
