package hu.hazazs.timestampadjuster.elements;

import java.awt.Color;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public final class Label extends JLabel {

	public Label(String text, int a, int b, int x, int y) {
		super(text);
		this.setBounds(a, b, x, y);
	}

	public Label(String text, int a, int b, int x, int y, Color color) {
		this(text, a, b, x, y);
		this.setVisible(false);
		this.setForeground(color);
	}

}