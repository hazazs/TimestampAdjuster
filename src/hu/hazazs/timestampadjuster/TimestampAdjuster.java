package hu.hazazs.timestampadjuster;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
		int hour = getValueFrom(mainWindow.getHourTextField());
		int minute = getValueFrom(mainWindow.getMinuteTextField());
		int second = getValueFrom(mainWindow.getSecondTextfield());
		Map<LocalTime, String> timestamps = getTimestamps();
		if (timestamps.isEmpty()) {
			mainWindow.getErrorLabel().setVisible(true);
			mainWindow.getTextArea().setBorder(mainWindow.getBorder(Color.RED));
			return;
		}
		mainWindow.getTextArea().setText(adjust(hour, minute, second, timestamps));
	}

	private void reset() {
		mainWindow.getErrorLabel().setVisible(false);
		mainWindow.getTextArea().setBorder(mainWindow.getBorder(Color.BLACK));
	}

	private int getValueFrom(JTextField textField) {
		String text = textField.getText();
		if (text.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(text);
	}

	private Map<LocalTime, String> getTimestamps() {
		String[] lines = mainWindow.getTextArea().getText().split("\n");
		if (lines.length == 0) {
			return new LinkedHashMap<>();
		}
		Map<LocalTime, String> timestamps = new LinkedHashMap<>();
		for (String line : lines) {
			if (line.matches("\\d{1,2}:\\d{2}:\\d{2} - .+")) {
				String[] parts = line.split(" - ");
				LocalTime timestamp;
				try {
					timestamp = LocalTime.parse(parts[0], DateTimeFormatter.ofPattern("H:mm:ss"));
				} catch (DateTimeParseException exception) {
					return new LinkedHashMap<>();
				}
				timestamps.put(timestamp, parts[1]);
			} else {
				return new LinkedHashMap<>();
			}
		}
		return timestamps;
	}

	private String adjust(int hour, int minute, int second, Map<LocalTime, String> timestamps) {
		List<String> lines = new ArrayList<>();
		timestamps.forEach((key, value) -> {
			LocalTime adjusted = key.plusHours(hour).plusMinutes(minute).plusSeconds(second);
			lines.add(adjusted.format(DateTimeFormatter.ofPattern("H:mm:ss")) + " - " + value);
		});
		return lines.stream().collect(Collectors.joining("\n"));
	}

}