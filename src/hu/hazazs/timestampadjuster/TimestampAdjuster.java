package hu.hazazs.timestampadjuster;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JTextField;

final class TimestampAdjuster implements Runnable {

	private static TimestampAdjuster timestampAdjuster;
	private final MainWindow mainWindow;

	private TimestampAdjuster(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	static TimestampAdjuster get(MainWindow mainWindow) {
		if (timestampAdjuster == null) {
			timestampAdjuster = new TimestampAdjuster(mainWindow);
		}
		return timestampAdjuster;
	}

	@Override
	public void run() {
		reset();
		int hour = getValueFrom(mainWindow.getTextFieldHour(), "hour");
		int minute = getValueFrom(mainWindow.getTextFieldMinute(), "minute");
		int second = getValueFrom(mainWindow.getTextFieldSecond(), "second");
		Map<LocalTime, String> timestamps = getTimestamps();
		if (hour < 0 || minute < 0 || second < 0 || timestamps.keySet().isEmpty()) {
			mainWindow.getErrorLabel().setText("Some of the input fields are invalid.");
			return;
		}
		mainWindow.getTextArea().setText(adjust(hour, minute, second, timestamps));
	}
	
	private void reset() {
		mainWindow.getTextFieldHour().setBorder(mainWindow.getBorder(Color.BLACK, 0, 5, 0, 0));
		mainWindow.getTextFieldMinute().setBorder(mainWindow.getBorder(Color.BLACK, 0, 5, 0, 0));
		mainWindow.getTextFieldSecond().setBorder(mainWindow.getBorder(Color.BLACK, 0, 5, 0, 0));
		mainWindow.getErrorLabel().setText("");
		mainWindow.getTextArea().setBorder(mainWindow.getBorder(Color.BLACK, 5, 5, 5, 5));
	}
	
	private int getValueFrom(JTextField textField, String placeholder) {
		String text = textField.getText();
		if (placeholder.equals(text)) {
			return 0;
		}
		try {
			int value = Integer.parseInt(text);
			if (value < 0) {
				textField.setBorder(mainWindow.getBorder(Color.RED, 0, 5, 0, 0));
			}
			return value;
		} catch (NumberFormatException exception) {
			textField.setBorder(mainWindow.getBorder(Color.RED, 0, 5, 0, 0));
			return -1;
		}
	}
	
	private Map<LocalTime, String> getTimestamps() {
		Map<LocalTime, String> timestamps = new LinkedHashMap<>();
		String[] lines = mainWindow.getTextArea().getText().split("\n");
		Arrays.stream(lines).forEach(line -> {
			if (line.matches("\\d+:\\d{2}:\\d{2} - .+")) {
				String[] parts = line.split(" - ");
				LocalTime timestamp = LocalTime.parse(parts[0], DateTimeFormatter.ofPattern("H:mm:ss"));
				timestamps.put(timestamp, parts[1]);
			} else {
				timestamps.clear();
				mainWindow.getTextArea().setBorder(mainWindow.getBorder(Color.RED, 5, 5, 5, 5));
				return;
			}
		});
		return timestamps;
	}
	
	private String adjust(int hour, int minute, int second, Map<LocalTime, String> timestamps) {
		StringBuilder builder = new StringBuilder();
		timestamps.forEach((key, value) -> {
			LocalTime adjusted = key.plusHours(hour).plusMinutes(minute).plusSeconds(second);
			builder.append(adjusted.format(DateTimeFormatter.ofPattern("H:mm:ss"))).append(" - ").append(value).append("\n");
		});
		return builder.toString();
	}

}