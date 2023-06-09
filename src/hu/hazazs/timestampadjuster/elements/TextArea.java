package hu.hazazs.timestampadjuster.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextArea;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public final class TextArea extends JTextArea {

	private TextArea(String placeholder) {
		super(placeholder);
	}

	public static TextArea get(String placeholder, int a, int b, int x, int y, Border border, Font font) {
		TextArea textArea = new TextArea(placeholder);
		textArea.setBounds(a, b, x, y);
		textArea.setBorder(border);
		textArea.setFont(font);
		textArea.setForeground(Color.GRAY);
		textArea.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent event) {
				if (textArea.getText().equals(placeholder)) {
					textArea.setText("");
					textArea.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent event) {
				if (textArea.getText().isEmpty()) {
					textArea.setForeground(Color.GRAY);
					textArea.setText(placeholder);
				}
			}

		});
		return textArea;
	}

}