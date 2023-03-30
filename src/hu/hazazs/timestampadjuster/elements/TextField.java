package hu.hazazs.timestampadjuster.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public final class TextField extends JTextField {

	private TextField(String placeholder) {
		super(placeholder);
	}

	public static TextField get(String placeholder, int a, int b, int x, int y, Font font, Border border) {
		TextField textField = new TextField(placeholder);
		textField.setBounds(a, b, x, y);
		textField.setFont(font);
		textField.setForeground(Color.GRAY);
		textField.setBorder(border);
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (textField.getText().isEmpty()) {
					textField.setForeground(Color.GRAY);
					textField.setText(placeholder);
				}
			}

		});
		return textField;
	}

}