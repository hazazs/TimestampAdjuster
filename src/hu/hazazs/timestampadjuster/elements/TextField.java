package hu.hazazs.timestampadjuster.elements;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public final class TextField extends JTextField {

	private static final List<Integer> ALLOWED_KEYS = List.of(8, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57);

	private TextField() {

	}

	public static TextField get(int a, int b, int x, int y, Border border, Font font) {
		TextField textField = new TextField();
		textField.setBounds(a, b, x, y);
		textField.setBorder(border);
		textField.setFont(font);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent keyEvent) {
				int key = (int) keyEvent.getKeyChar();
				if (!ALLOWED_KEYS.contains(key) || textField.getText().length() == 2) {
					keyEvent.consume();
				}
			}
		});
		textField.setTransferHandler(null);
		return textField;
	}

}