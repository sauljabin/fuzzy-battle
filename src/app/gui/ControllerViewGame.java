package app.gui;

import java.awt.Color;

import javax.swing.SwingUtilities;

import app.Config;
import app.Translate;
import app.animation.Animated;
import app.animation.AnimatedButton;
import app.animation.AnimatedKeyListener;
import app.animation.AnimatedLateral;
import app.animation.AnimatedMouseListener;
import app.animation.Animator;

public class ControllerViewGame extends Controller implements AnimatedMouseListener, AnimatedKeyListener {

	private ViewGame viewGame;
	private Animator animator;
	private AnimatedLateral animatedLatelar;
	private AnimatedButton animatedButtonPause;
	private AnimatedButton animatedButtonMenu;

	public ControllerViewGame() {
		viewGame = new ViewGame();
		viewGame.setController(this);
		animator = viewGame.getAnimator();
		initGame();
		animator.start();
	}

	public void initGame() {
		animator.setBackground(Color.BLACK);
		animator.setFPS(Integer.parseInt(Config.get("DEFAULT_FPS")));
		animator.setFocusable(true);
		
		animatedLatelar = new AnimatedLateral();

		animatedButtonPause = new AnimatedButton();
		animatedButtonPause.setX(20);
		animatedButtonPause.setY(animator.getHeight() - 130);
		animatedButtonPause.setText(Translate.get("GUI_PAUSE"));
		animatedButtonPause.addAnimatedMouseListener(this);

		animatedButtonMenu = new AnimatedButton();
		animatedButtonMenu.setX(20);
		animatedButtonMenu.setY(animator.getHeight() - 70);
		animatedButtonMenu.setText(Translate.get("GUI_MENU"));
		animatedButtonMenu.addAnimatedMouseListener(this);

		animator.addAnimated(animatedLatelar);
		animator.addAnimated(animatedButtonPause);
		animator.addAnimated(animatedButtonMenu);
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
				animator.stop();
				new ControllerViewMenu();
				viewGame.dispose();
			}
		});
	}

	public void pause() {
		if (animator.isPause())
			animator.pause();
		else
			animator.resume();
	}

	@Override
	public void animatedKeyPressed(Animated source) {

	}

	@Override
	public void animatedKeyReleased(Animated source) {

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
