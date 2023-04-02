package hu.hazazs.timestampadjuster.elements;

import java.awt.Color;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public final class Label extends JLabel {

	public Label(String text, int a, int b, int x, int y, Color... color) {
		super(text);
		this.setBounds(a, b, x, y);
		if (color.length > 0) {
			this.setForeground(color[0]);
		}
	}

}