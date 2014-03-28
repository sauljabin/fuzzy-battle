package app.gui;

import app.animation.Animator;

public class ViewGame extends View {

	private static final long serialVersionUID = 770798512323759734L;
	private Animator animator;

	@Override
	public void init() {
		setSize(800, 600);
		animator = new Animator();
		add(animator);
	}

	public Animator getAnimator() {
		return animator;
	}
}
