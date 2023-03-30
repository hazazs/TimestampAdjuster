package hu.hazazs.timestampadjuster;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.concurrent.Executors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import hu.hazazs.timestampadjuster.elements.Button;
import hu.hazazs.timestampadjuster.elements.Label;
import hu.hazazs.timestampadjuster.elements.TextArea;
import hu.hazazs.timestampadjuster.elements.TextField;

final class MainWindow {

	private static final String ICON_PATH = "src/resources/timestamp.png";
	private static final String TITLE = "Timestamp Adjuster 1.0";

	private JFrame frame;
	private JTextField textFieldHour;
	private JTextField textFieldMinute;
	private JTextField textFieldSecond;
	private JButton adjustButton;
	private JLabel errorLabel;
	private JTextArea textArea;

	JTextField getTextFieldHour() {
		return textFieldHour;
	}

	JTextField getTextFieldMinute() {
		return textFieldMinute;
	}

	JTextField getTextFieldSecond() {
		return textFieldSecond;
	}

	JLabel getErrorLabel() {
		return errorLabel;
	}

	JTextArea getTextArea() {
		return textArea;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new MainWindow().run());
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void run() {
		frame = new JFrame(TITLE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ICON_PATH));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 500);
		frame.setResizable(false);

		final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);

		textFieldHour = TextField.get("hour", 10, 11, 86, 20, font, getBorder(Color.BLACK, 0, 5, 0, 0));
		frame.getContentPane().add(textFieldHour);
		textFieldMinute = TextField.get("minute", 10, 42, 86, 20, font, getBorder(Color.BLACK, 0, 5, 0, 0));
		frame.getContentPane().add(textFieldMinute);
		textFieldSecond = TextField.get("second", 10, 73, 86, 20, font, getBorder(Color.BLACK, 0, 5, 0, 0));
		frame.getContentPane().add(textFieldSecond);

		adjustButton = Button.get("ADJUST", 105, 11, 88, 82, font);
		adjustButton.addActionListener(event -> {
			frame.getContentPane().requestFocusInWindow();
			adjust();
		});
		frame.getContentPane().add(adjustButton);

		errorLabel = Label.get(15, 104, 414, 14, font);
		frame.getContentPane().add(errorLabel);

		textArea = TextArea.get("Please insert your timestamps here", 10, 129, 414, 321, font,
				getBorder(Color.BLACK, 5, 5, 5, 5));
		frame.getContentPane().add(textArea);

		frame.getContentPane().requestFocusInWindow();
	}

	Border getBorder(Color color, int a, int b, int x, int y) {
		final Border outside = BorderFactory.createLineBorder(color);
		final Border inside = BorderFactory.createEmptyBorder(a, b, x, y);
		return BorderFactory.createCompoundBorder(outside, inside);
	}

	private void adjust() {
		Executors.newSingleThreadExecutor().execute(TimestampAdjuster.get(this));
	}
}
