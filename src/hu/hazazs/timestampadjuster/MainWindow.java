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
	private JTextField hourTextField;
	private JLabel hourLabel;
	private JTextField minuteTextField;
	private JLabel minuteLabel;
	private JTextField secondTextField;
	private JLabel secondLabel;
	private JButton adjustButton;
	private JLabel errorLabel;
	private JTextArea textArea;

	JTextField getHourTextField() {
		return hourTextField;
	}

	JTextField getMinuteTextField() {
		return minuteTextField;
	}

	JTextField getSecondTextfield() {
		return secondTextField;
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

		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);

		hourTextField = new TextField(10, 11, 30, 20, getBorder(Color.BLACK, 0, 5, 0, 0), font);
		frame.getContentPane().add(hourTextField);
		minuteTextField = new TextField(10, 42, 30, 20, getBorder(Color.BLACK, 0, 5, 0, 0), font);
		frame.getContentPane().add(minuteTextField);
		secondTextField = new TextField(10, 73, 30, 20, getBorder(Color.BLACK, 0, 5, 0, 0), font);
		frame.getContentPane().add(secondTextField);

		adjustButton = new Button("ADJUST", 120, 11, 88, 82);
		adjustButton.addActionListener(event -> {
			frame.getContentPane().requestFocusInWindow();
			adjust();
		});
		frame.getContentPane().add(adjustButton);

		hourLabel = new Label("hour(s)", 42, 10, 150, 20);
		frame.getContentPane().add(hourLabel);
		minuteLabel = new Label("minute(s)", 42, 41, 150, 20);
		frame.getContentPane().add(minuteLabel);
		secondLabel = new Label("second(s)", 42, 72, 150, 20);
		frame.getContentPane().add(secondLabel);
		errorLabel = new Label("", 15, 104, 414, 14, Color.RED);
		frame.getContentPane().add(errorLabel);

		textArea = TextArea.get("Please insert your timestamps here", 10, 129, 414, 321, getBorder(Color.BLACK, 5, 5, 5, 5), font);
		frame.getContentPane().add(textArea);

		frame.getContentPane().requestFocusInWindow();
	}

	Border getBorder(Color color, int a, int b, int x, int y) {
		Border outside = BorderFactory.createLineBorder(color);
		Border inside = BorderFactory.createEmptyBorder(a, b, x, y);
		return BorderFactory.createCompoundBorder(outside, inside);
	}

	private void adjust() {
		Executors.newSingleThreadExecutor().execute(TimestampAdjuster.get(this));
	}
	
}