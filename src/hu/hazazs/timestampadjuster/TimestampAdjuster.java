package hu.hazazs.timestampadjuster;

import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;

final class TimestampAdjuster {

	private static final String ICON_PATH = "src/resources/timestamp.png";
	private static final String TITLE = "Timestamp Adjuster 1.0";

	private final JFrame frame = new JFrame();

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new TimestampAdjuster());
	}

	/**
	 * Create the application.
	 */
	private TimestampAdjuster() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ICON_PATH));
		frame.setTitle(TITLE);
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
