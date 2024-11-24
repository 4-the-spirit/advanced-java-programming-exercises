/**
 * Represents temperature data for a single year. This class holds an array of
 * monthly temperature values, allowing analysis or manipulation of yearly
 * temperature.
 */
public class YearlyTemperatureData {
	private static final int NUMBER_OF_MONTHS_IN_YEAR = 12;
	private double[] data;
	private int year;

	/**
	 * A constructor that takes an array of data and a year
	 * checks if they are valid and assign them to the instance variables.
	 */
	public YearlyTemperatureData(double[] data, int year) {
		if (data.length != YearlyTemperatureData.NUMBER_OF_MONTHS_IN_YEAR) {
			throw new IllegalArgumentException("The data given should match the number of months in a year!");
		} else if (year < 0) {
			throw new IllegalArgumentException("The year must be a non-negative integer!");
		}
		this.data = data;
		this.year = year;
	}

	/**
	 * A getter method for the temperature data.
	 */
	public double[] getData() {
		return this.data;
	}

	/**
	 * A getter method for the year that the data belongs to.
	 */
	public int getYear() {
		return this.year;
	}

	/*
	 * Returns the index of the maximum temperature in this year in the data array.
	 */
	public int getArgMax() {
		double[] temperatureData = this.data;
		int tempMaxIndex = 0;
		double tempMax = temperatureData[0];
		for (int i = 0; i < temperatureData.length; i++) {
			if (temperatureData[i] > tempMax) {
				tempMaxIndex = i;
				tempMax = temperatureData[i];
			}
		}
		return tempMaxIndex;
	}

	/**
	 * Returns the index of the minimum temperature in this year in the data array.
	 */
	public int getArgMin() {
		double[] temperatureData = this.data;
		int tempMinIndex = 0;
		double tempMin = temperatureData[0];
		for (int i = 0; i < temperatureData.length; i++) {
			if (temperatureData[i] < tempMin) {
				tempMinIndex = i;
				tempMin = temperatureData[i];
			}
		}
		return tempMinIndex;
	}
}
