import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameController {

	@FXML
	private TextField guess;

	@FXML
	private Text totalGuesses;

	private BullsAndCowsGame game;

	private String guessesString = "";

	public void initialize() {
		game = new BullsAndCowsGame();
	}

	@FXML
	void btnPressGuess(ActionEvent event) {
		int guessNum = 0;

		while (true) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Bulls & Cows Game");
			dialog.setHeaderText("Guess the Secret Number");
			dialog.setContentText("Please enter a 4-digit number:");

			Optional<String> result = dialog.showAndWait();

			if (result.isPresent()) {
				String userInput = result.get();

				try {
					guessNum = Integer.parseInt(userInput);

					if (guessNum < BullsAndCowsGame.SECRET_NUMBER_MIN
							|| guessNum > BullsAndCowsGame.SECRET_NUMBER_MAX) {
						throw new IllegalArgumentException("Number out of range!");
					}

					Map<String, Integer> guessMap = game.guess(guessNum);
					System.out.println("Guess: " + guessNum);

					if (guessNum == game.getSecretNumber()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Congratulations!");
						alert.setContentText("You guessed the correct number!");
						alert.showAndWait();
						totalGuesses.setText(Integer.toString(game.getNumberOfGuesses()));
						;
						break;
					} else {
						guessesString += "Guess: " + guessNum + ", Bulls: " + guessMap.get(BullsAndCowsGame.BULLS_LABEL)
								+ ", Cows: " + guessMap.get(BullsAndCowsGame.COWS_LABEL) + "\n";
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Incorrect Guess");
						alert.setContentText("Pase Guesses:" + "\n" + guessesString);
						alert.showAndWait();
					}

				} catch (NumberFormatException e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Invalid Input");
					alert.setContentText("Please enter a valid number!");
					alert.showAndWait();

				} catch (IllegalArgumentException e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("Invalid Range");
					alert.setContentText("The number must be between " + BullsAndCowsGame.SECRET_NUMBER_MIN + " and "
							+ BullsAndCowsGame.SECRET_NUMBER_MAX + ".");
					alert.showAndWait();
				}
			} else {
				break;
			}
		}
	}

	@FXML
	void btnPressNewGame(ActionEvent event) {
		game = new BullsAndCowsGame();
		guessesString = "";
		totalGuesses.setText("");
	}

}
