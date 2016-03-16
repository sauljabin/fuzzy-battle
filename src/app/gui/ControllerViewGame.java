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

package app.gui;

import java.awt.Color;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

import app.Config;
import app.Translate;
import app.animation.Animated;
import app.animation.AnimatedMouseListener;
import app.animation.Animator;
import app.animation.animated.AnimatedBase;
import app.animation.animated.AnimatedButton;
import app.animation.animated.AnimatedEnemy;
import app.animation.animated.AnimatedInfoPlayer;
import app.animation.animated.AnimatedLateral;
import app.animation.animated.AnimatedLattice;
import app.animation.animated.AnimatedPlayer;
import app.animation.animated.AnimatedWall;
import app.util.UtilSound;

public class ControllerViewGame extends Controller implements AnimatedMouseListener {

	private ViewGame viewGame;
	private Animator animator;
	private AnimatedButton animatedButtonPause;
	private AnimatedButton animatedButtonMenu;
	private int latticeSize;
	private Clip music;
	private Clip engine;

	public ControllerViewGame() {

		latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
		viewGame = new ViewGame();
		viewGame.setController(this);
		animator = viewGame.getAnimator();
		initGame();
		animator.start();
		if (Config.get("MUSIC").equals("ON"))
			startMusic();

		if (Config.get("SOUND_EFFECTS").equals("ON"))
			startEngine();
	}

	private void startEngine() {
		Thread engineThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					engine = UtilSound.getClip("sound/engine.wav");
					if (engine != null)
						engine.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		engineThread.start();
	}

	private void startMusic() {
		Thread musicThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Random rand = new Random();
					music = UtilSound.getClip("sound/game" + (rand.nextInt(Integer.parseInt(Config.get("TOTAL_SONGS"))) + 1) + ".wav");
					if (music != null)
						music.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		musicThread.start();
	}

	public void initGame() {
		// INIT ANIMATOR
		animator.setBackground(Color.BLACK);
		animator.setFPS(Integer.parseInt(Config.get("DEFAULT_FPS")));
		animator.setFocusable(true);
		// INIT ANIMATOR

		// INIT MENU
		animatedButtonPause = new AnimatedButton();
		animatedButtonPause.setX(animator.getWidth() - 170);
		animatedButtonPause.setY(animator.getHeight() - 110);
		animatedButtonPause.setText(Translate.get("GUI_PAUSE"));
		animatedButtonPause.setTextX(43);
		animatedButtonPause.addAnimatedMouseListener(this);

		animatedButtonMenu = new AnimatedButton();
		animatedButtonMenu.setX(animator.getWidth() - 170);
		animatedButtonMenu.setY(animator.getHeight() - 60);
		animatedButtonMenu.setText(Translate.get("GUI_MENU"));
		animatedButtonMenu.setTextX(45);
		animatedButtonMenu.addAnimatedMouseListener(this);

		animator.addAnimated(new AnimatedLateral());
		animator.addAnimated(animatedButtonPause);
		animator.addAnimated(animatedButtonMenu);
		// INIT MENU

		// LATTICE FOR WORLD INITIALIZATION
		animator.addAnimated(new AnimatedLattice());
		Animated[][] lattice = new Animated[latticeSize][latticeSize];
		// LATTICE FOR WORLD INITIALIZATION

		// INIT PLAYER
		int rowPlayer = latticeSize - 1;
		int columnPlayer = (latticeSize - 1) / 2 + 2;
		AnimatedPlayer animatedPlayer = new AnimatedPlayer(rowPlayer, columnPlayer);
		lattice[rowPlayer][columnPlayer] = animatedPlayer;
		animator.addAnimated(animatedPlayer);
		animator.addAnimated(new AnimatedInfoPlayer(animatedPlayer));
		// INIT PLAYER

		// INIT BASE
		int rowBase = latticeSize - 1;
		int columnBase = (latticeSize - 1) / 2;
		AnimatedBase animatedBase = new AnimatedBase(rowBase, columnBase);
		lattice[rowBase][columnBase] = animatedBase;
		animator.addAnimated(animatedBase);

		AnimatedWall wallForBase = new AnimatedWall(rowBase, columnBase - 1);
		lattice[rowBase][columnBase - 1] = wallForBase;
		animator.addAnimated(wallForBase);

		AnimatedWall wallForBase2 = new AnimatedWall(rowBase, columnBase + 1);
		lattice[rowBase][columnBase + 1] = wallForBase2;
		animator.addAnimated(wallForBase2);

		AnimatedWall wallForBase3 = new AnimatedWall(rowBase - 1, columnBase - 1);
		lattice[rowBase - 1][columnBase - 1] = wallForBase3;
		animator.addAnimated(wallForBase3);

		AnimatedWall wallForBase4 = new AnimatedWall(rowBase - 1, columnBase);
		lattice[rowBase - 1][columnBase] = wallForBase4;
		animator.addAnimated(wallForBase4);

		AnimatedWall wallForBase5 = new AnimatedWall(rowBase - 1, columnBase + 1);
		lattice[rowBase - 1][columnBase + 1] = wallForBase5;
		animator.addAnimated(wallForBase5);
		// INIT BASE

		// INIT ENEMYS
		int totalEnemys = Integer.parseInt(Config.get("ENEMYS"));
		Random randEnemys = new Random();
		for (int i = 0; i < totalEnemys; i++) {
			while (true) {
				int row = randEnemys.nextInt(latticeSize);
				int column = randEnemys.nextInt(latticeSize);
				if (lattice[row][column] == null) {
					AnimatedEnemy animatedEnemy = new AnimatedEnemy(row, column);
					lattice[row][column] = animatedEnemy;
					animator.addAnimated(animatedEnemy);
					break;
				}
			}
		}
		// INIT ENEMYS

		// INIT WALLS
		for (int i = 0; i < latticeSize; i++) {
			for (int j = 0; j < latticeSize; j++) {
				double rand = Math.random();
				if (lattice[i][j] == null && rand < 0.3) {
					AnimatedWall animated = new AnimatedWall(i, j);
					animator.addAnimated(animated);
					lattice[i][j] = animated;
				}
			}
		}
		// INIT WALLS

	}

	@Override
	public void action(Object source) {
		if (source.equals(viewGame))
			close();
	}

	public void close() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (music != null)
					music.stop();
				if (engine != null)
					engine.stop();
				animator.stop();
				new ControllerViewMenu();
				viewGame.dispose();
			}
		});
	}

	public void pause() {
		if (!animator.isPause()) {
			animatedButtonPause.setText(Translate.get("GUI_RESUME"));
			animatedButtonPause.setTextX(30);
			animator.pause();
		} else {
			animatedButtonPause.setText(Translate.get("GUI_PAUSE"));
			animatedButtonPause.setTextX(43);
			animator.resume();
		}
	}

	@Override
	public void animatedMousePressed(Animated source) {
		if (source.equals(animatedButtonPause))
			pause();
	}

	@Override
	public void animatedMouseReleased(Animated source) {
		if (source.equals(animatedButtonMenu))
			close();
	}
}
