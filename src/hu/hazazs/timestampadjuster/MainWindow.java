package hu.hazazs.timestampadjuster;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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
		final Border outsideBorder = BorderFactory.createLineBorder(Color.BLACK);

		final Border insideBorderTextField = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		textFieldHour = TextField.get("hour", 10, 11, 86, 20, font, outsideBorder, insideBorderTextField);
		frame.getContentPane().add(textFieldHour);
		textFieldMinute = TextField.get("minute", 10, 42, 86, 20, font, outsideBorder, insideBorderTextField);
		frame.getContentPane().add(textFieldMinute);
		textFieldSecond = TextField.get("second", 10, 73, 86, 20, font, outsideBorder, insideBorderTextField);
		frame.getContentPane().add(textFieldSecond);

		adjustButton = Button.get("ADJUST", 105, 11, 88, 82, font);
		adjustButton.addActionListener(event -> frame.getContentPane().requestFocusInWindow());
		frame.getContentPane().add(adjustButton);

		errorLabel = Label.get(15, 104, 414, 14, font);
		frame.getContentPane().add(errorLabel);

		final Border insideBorderTextArea = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		textArea = TextArea.get("Please insert your timestamps here", 10, 129, 414, 321, font, outsideBorder,
				insideBorderTextArea);
		frame.getContentPane().add(textArea);

		frame.getContentPane().requestFocusInWindow();
	}

}
