/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of FuzzyBattle.
 * 
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Vector;

/**
 * 
 * @author Saul Pina - sauljp07@gmail.com
 */
public class Animator extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private Vector<Animated> animateds;
	private int FPS;
	private boolean pause;
	private Thread thread;
	private boolean stop;
	private Image image;
	private Graphics2D graphics;
	private boolean antialiasing;
	private boolean gameFinish;

	public boolean isAntialiasing() {
		return antialiasing;
	}

	public void setAntialiasing(boolean antialiasing) {
		this.antialiasing = antialiasing;
		if (graphics != null) {
			if (antialiasing) {
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			} else {
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
				graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
			}
		}
	}

	public Vector<Animated> getAnimateds() {
		return animateds;
	}

	public boolean isPause() {
		return pause;
	}

	public boolean isStop() {
		return stop;
	}

	public synchronized void addAnimated(Animated animated) {
		animateds.add(animated);
		animated.setAnimator(this);
		animated.init();
		animated.setAlive(true);
	}

	public synchronized void removeAnimated(Animated animated) {
		animateds.remove(animated);
	}

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int FPS) {
		this.FPS = FPS;
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
				animatorKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				animatorKeyReleased(e);
			}

		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				makeImage();
			}
		});
	}

	public synchronized void makeImage() {
		image = new BufferedImage(getWidth(), getHeight(), 1);
		graphics = (Graphics2D) image.getGraphics();
		setAntialiasing(true);
	}

	public synchronized void animatorKeyPressed(KeyEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			for (int j = 0; j < animateds.get(i).getAnimatedKeyListeners().size(); j++) {
				animateds.get(i).getAnimatedKeyListeners().get(j).animatedKeyPressed(animateds.get(i), e);
			}
		}
	}

	public synchronized void animatorKeyReleased(KeyEvent e) {
		for (int i = 0; i < animateds.size(); i++) {
			for (int j = 0; j < animateds.get(i).getAnimatedKeyListeners().size(); j++) {
				animateds.get(i).getAnimatedKeyListeners().get(j).animatedKeyReleased(animateds.get(i), e);
			}
		}
	}

	public synchronized void animatorMouseReleased(MouseEvent e) {
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

	public synchronized void animatorMousePressed(MouseEvent e) {
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

	public synchronized void start() {
		if (!thread.isAlive()) {
			makeImage();
			thread.start();
		}
	}

	public synchronized void stop() {
		stop = true;
		pause = true;
		try {
			thread.join(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void pause() {
		pause = true;
	}

	public synchronized void resume() {
		pause = false;
	}

	public synchronized boolean isGameFinish() {
		return gameFinish;
	}

	public synchronized void setGameFinish(boolean gameFinish) {
		this.gameFinish = gameFinish;
	}

	@Override
	public void run() {
		stop = false;
		pause = false;
		gameFinish = false;
		while (!stop) {
			sortAnimateds();

			if (!pause && !gameFinish) {
				animate();
			}

			rendering();

			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void sortAnimateds() {
		Collections.sort(animateds);
	}

	public synchronized void animate() {
		for (int i = 0; i < animateds.size(); i++) {
			animateds.get(i).animate();
		}
	}

	public synchronized void rendering() {
		graphics.setBackground(getBackground());
		graphics.clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < animateds.size(); i++) {
			Animated animate = animateds.get(i);
			graphics.rotate(-Math.toRadians(animate.getAngle()), animate.getX(), animate.getY());
			animate.paint(graphics);
			graphics.rotate(Math.toRadians(animate.getAngle()), animate.getX(), animate.getY());
		}
		getGraphics().drawImage(image, 0, 0, this);
	}

	public synchronized void restart() {
		makeImage();
		initAnimateds();
	}

	public synchronized void initAnimateds() {
		for (int i = 0; i < animateds.size(); i++) {
			animateds.get(i).init();
			animateds.get(i).setAlive(true);
		}
	}
}
