package hu.hazazs.timestampadjuster.elements;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public final class Label extends JLabel {

	private Label() {

	}

	public static Label get(int a, int b, int x, int y, Font font) {
		Label label = new Label();
		label.setBounds(a, b, x, y);
		label.setFont(font);
		label.setForeground(Color.RED);
		return label;
	}

}