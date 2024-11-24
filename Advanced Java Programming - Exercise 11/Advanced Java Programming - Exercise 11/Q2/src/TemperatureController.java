
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TemperatureController {

	@FXML
	private Canvas canvas;

	@FXML
	private Text title;

	private GraphicsContext gc;

	private YearlyTemperatureData[] temperatureData = new YearlyTemperatureData[] {
			new YearlyTemperatureData(
					new double[] { 11.2, 5.4, 6.3, 14.5, 21.4, 21.1, 12.3, 15.7, 25.3, 33.5, 32.5, 15.3 }, 2017),
			new YearlyTemperatureData(
					new double[] { 10.5, 24.3, 37.8, 2.4, 20.5, 22.1, 14.6, 16.9, 23.5, 30.7, 29.4, 13.2 }, 2018),
			new YearlyTemperatureData(
					new double[] { 12.1, 16.7, 38.4, 15.2, 39.8, 24.3, 17.5, 18.8, 26.1, 31.9, 28.7, 16.4 }, 2019),
			new YearlyTemperatureData(
					new double[] { 11.8, 5.9, 6.7, 23.5, 18.6, 23.4, 15.3, 17.2, 24.8, 32.3, 30.1, 14.7 }, 2020),
			new YearlyTemperatureData(
					new double[] { 10.9, 3.5, 1.6, 10.8, 26.7, 19.5, 12.9, 24.1, 32.3, 28.5, 27.8, 11.3 }, 2021), };

	private int yearIndex = 0;

	private static final double X_TEXT_SHIFT = 10.0;
	private static final double Y_TEXT_SHIFT = 12.0;

	private static final double X_SPACE_DELTA = 49.0;
	private static final double Y_SPACE_DELTA = 20.0;

	private static final double DEFAULT_WIDTH = 40.0;
	private static final double HEIGHT_SCALING_FACTOR = 6.0;

	public void initialize() {
		gc = canvas.getGraphicsContext2D();
	}

	@FXML
	void btnNextPressed(ActionEvent event) {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		YearlyTemperatureData yearlyData = temperatureData[yearIndex];
		double[] temperatureArray = yearlyData.getData();
		double currentTemperature;

		title.setText(Integer.toString(yearlyData.getYear()));

		for (int i = 0; i < temperatureArray.length; i++) {
			currentTemperature = temperatureArray[i] * HEIGHT_SCALING_FACTOR;

			if (i == yearlyData.getArgMax()) {
				gc.setFill(Color.RED);
			} else if (i == yearlyData.getArgMin()) {
				gc.setFill(Color.BLUE);
			} else {
				gc.setFill(Color.GRAY);
			}

			gc.fillRect(10.0 + i * X_SPACE_DELTA, canvas.getHeight() - currentTemperature - Y_SPACE_DELTA,
					DEFAULT_WIDTH, currentTemperature);
			gc.strokeText(Integer.toString(i + 1), X_TEXT_SHIFT + i * X_SPACE_DELTA,
					canvas.getHeight() - Y_SPACE_DELTA + Y_TEXT_SHIFT);
		}

		yearIndex = (yearIndex + 1) % temperatureData.length;
	}

}
