package app.animation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class AnimatedButton extends Animated {

	private Color backColor;
	private Color fontColor;
	private Font font;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public AnimatedButton() {
		addAnimatedMouseListener(new AnimatedMouseListener() {
			@Override
			public void animatedMousePressed(Animated source) {
				backColor = new Color(40, 40, 40);
			}

			@Override
			public void animatedMouseReleased(Animated source) {
				backColor = Color.BLACK;
			}
		});
	}

	@Override
	public void init() {
		setWidth(160);
		setHeight(50);
		setZ(10);

		backColor = Color.BLACK;
		fontColor = Color.WHITE;
		font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	}

	@Override
	public Rectangle2D getShape() {
		return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(backColor);
		g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), 20, 20);

		Font fontTemp = g.getFont();
		g.setFont(font);
		g.setColor(fontColor);
		g.drawString(text, getX() + 30, getY() + 35);
		g.setFont(fontTemp);
	}

	@Override
	public void animate() {

	}

}
