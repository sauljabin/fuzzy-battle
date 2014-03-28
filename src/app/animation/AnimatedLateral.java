package app.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class AnimatedLateral extends Animated {

	private Color color;

	@Override
	public void init() {
		setX(0);
		setY(0);
		setZ(5);
		setWidth(200);
		setHeight(getAnimator().getHeight());
		color = Color.GRAY;
	}

	@Override
	public Rectangle2D getShape() {
		return null;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void animate() {

	}

}
