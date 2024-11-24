import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The {@code BullsAndCowsGame} class implements the logic for a game of Bulls
 * and Cows. In this game, the player guesses a secret number, and feedback is
 * provided in terms of "bulls" (correct digits in the correct position) and
 * "cows" (correct digits in the wrong position).
 */
public class BullsAndCowsGame {
	// Constants
	private static final int SECRET_NUMBER_RANGE = 9000;

	// Does not violates the encapsulation principle
	// since the user can't change constants.

	public static final int SECRET_NUMBER_MIN = 1000;
	public static final int SECRET_NUMBER_MAX = 9999;
	public static final int NUMBER_OF_GAME_DIGITS = 4;

	public static final String BULLS_LABEL = "bulls";
	public static final String COWS_LABEL = "cows";

	// Instance Variables
	private final int SECRET_NUMBER;
	private int numberOfGuesses = 0;

	/**
	 * A constructor that generates a random secret number for the game.
	 */
	public BullsAndCowsGame() {
		Random random = new Random();
		// Will generate a random integer in the range [1000, 9999]
		this.SECRET_NUMBER = BullsAndCowsGame.SECRET_NUMBER_MIN + random.nextInt(BullsAndCowsGame.SECRET_NUMBER_RANGE);
	}

	/**
	 * A getter method for the secret number.
	 * 
	 * @return
	 */
	public int getSecretNumber() {
		return this.SECRET_NUMBER;
	}

	/**
	 * A getter method for the number of guesses.
	 * 
	 * @return
	 */
	public int getNumberOfGuesses() {
		return this.numberOfGuesses;
	}

	/**
	 * Returns a Map that contains the number of bulls and cows.
	 * 
	 * @param guessedNumber
	 * @return
	 */
	public Map<String, Integer> guess(int guessedNumber) {
		Map<String, Integer> mapping = new HashMap<>();
		mapping.put(BullsAndCowsGame.BULLS_LABEL, this.numberOfBulls(guessedNumber));
		mapping.put(BullsAndCowsGame.COWS_LABEL, this.numberOfCows(guessedNumber));
		this.numberOfGuesses += 1;
		return mapping;
	}

	/**
	 * Returns the number of bulls of the given guess compared to the secret number.
	 */
	private int numberOfBulls(int guess) {
		int secretNumberTemp = this.getSecretNumber();
		int guessNumberTemp = guess;

		int secretNumberCurrentDigit;
		int guessNumberCurrentDigit;

		int numOfBulls = 0;

		// Compare digits by digit until no digits left
		while (guessNumberTemp != 0) {
			secretNumberCurrentDigit = secretNumberTemp % 10;
			guessNumberCurrentDigit = guessNumberTemp % 10;

			if (guessNumberCurrentDigit == secretNumberCurrentDigit) {
				numOfBulls += 1;
			}
			secretNumberTemp = (int) (secretNumberTemp / 10);
			guessNumberTemp = (int) (guessNumberTemp / 10);
		}
		return numOfBulls;
	}

	/**
	 * Returns the number of cows of the given guess compared to the secret number.
	 */
	private int numberOfCows(int guess) {
		return this.numberOfDigitsInSecretNumber(guess) - this.numberOfBulls(guess);
	}

	/**
	 * Calculates the number of digits in the secret number that match the digits in
	 * the player's guess. This method compares the player's guess to the secret
	 * number digit by digit and counts how many digits are present in the secret
	 * number.
	 *
	 * @param guess the player's guess, represented as an integer.
	 * @return the number of digits in the guess that match digits in the secret
	 *         number.
	 */
	private int numberOfDigitsInSecretNumber(int guess) {
		int guessNumberTemp = guess;
		int secretNumberTemp = this.getSecretNumber();
		int secretNumberTempCopy;

		int guessNumberCurrentDigit;
		int secretNumberCurrentDigit;

		int index = 0;
		boolean matchedDigits = false;
		int numOfCommonDigits = 0;

		while (guessNumberTemp != 0) {
			guessNumberCurrentDigit = guessNumberTemp % 10;
			secretNumberTempCopy = secretNumberTemp;

			while (secretNumberTempCopy != 0) {
				secretNumberCurrentDigit = secretNumberTempCopy % 10;
				if (guessNumberCurrentDigit == secretNumberCurrentDigit) {
					numOfCommonDigits += 1;
					secretNumberTemp = BullsAndCowsGame.removeDigitByIndex(secretNumberTemp, index);
					break;
				}
				secretNumberTempCopy = (int) (secretNumberTempCopy / 10);
				index += 1;
			}
			guessNumberTemp = (int) (guessNumberTemp / 10);
			index = 0;

		}
		return numOfCommonDigits;
	}

	/**
	 * Removes a digit from a number at the specified index. This method takes an
	 * integer number and an index, removes the digit at the given index (0-based,
	 * counting from right), and returns the resulting number.
	 *
	 * @param num   the original number.
	 * @param index the index of the digit to remove (0-based).
	 * @return the number after removing the specified digit.
	 */
	private static int removeDigitByIndex(int num, int index) {
		if (num <= 9) {
			return num;
		}
		String numStr = Integer.toString(num);
		int indexFromLeft = numStr.length() - 1 - index;

		String resultStr = numStr.substring(0, indexFromLeft) + numStr.substring(indexFromLeft + 1);
		return Integer.parseInt(resultStr);
	}
}
