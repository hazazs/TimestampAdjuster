package hu.hazazs.timestampadjuster.elements;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public final class TextField extends JTextField {

	public TextField(int a, int b, int x, int y, Border border, Font font) {
		this.setBounds(a, b, x, y);
		this.setBorder(border);
		this.setFont(font);
		this.setHorizontalAlignment(JTextField.RIGHT);
	}

}