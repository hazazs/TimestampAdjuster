package hu.hazazs.timestampadjuster;

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
		System.out.println("anyád");
	}

}