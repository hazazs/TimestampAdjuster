package hu.hazazs.timestampadjuster.elements;

import javax.swing.JButton;

@SuppressWarnings("serial")
public final class Button extends JButton {

	public Button(String label, int a, int b, int x, int y) {
		super(label);
		this.setBounds(a, b, x, y);
		this.setFocusable(false);
	}

}