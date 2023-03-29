package hu.hazazs.timestampadjuster.elements;

import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public final class Button extends JButton {

	private Button(String label) {
		super(label);
	}

	public static Button get(String label, int a, int b, int x, int y, Font font) {
		Button button = new Button(label);
		button.setBounds(105, 11, 88, 82);
		button.setFont(font);
		return button;
	}

}