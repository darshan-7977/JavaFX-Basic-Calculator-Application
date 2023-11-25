package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {

	private TextField display;
	private String currentInput = "";
	private String operator = "";
	private double result = 0.0;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		display = new TextField();
		display.setId("display");
		display.setAlignment(Pos.CENTER_RIGHT);

		Button[] numberButtons = new Button[10];
		for (int i = 0; i < 10; i++) {
			final int buttonValue = i;
			numberButtons[i] = new Button(Integer.toString(i));
			numberButtons[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					// TODO Auto-generated method stub
					appendToInput(Integer.toString(buttonValue));
				}
			});
			numberButtons[i].setStyle(
					"-fx-background-color: linear-gradient(to bottom, #001F3F, #003366); -fx-text-field: white;");
		}

		Button addButton = createOperatorButton("+");
		Button subtractbutton = createOperatorButton("-");
		Button multiplyButton = createOperatorButton("*");
		Button divideButton = createOperatorButton("/");
		Button equalsButton = createOperatorButton("=");
		equalsButton.setOnAction(e -> calculator());
		Button clearButton = new Button("C");
		clearButton.setOnAction(e -> clear());

		GridPane grid = new GridPane();
		grid.setFocusTraversable(false);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setStyle("-fx-background-color: linear-gradient(to bottom, #001F3F, #003366);");
		display.setStyle("-fx-control-inner-background: #000000; -fx-text-fill: white;");
		grid.add(display, 0, 0, 4, 1);
		grid.add(numberButtons[7], 0, 1);
		grid.add(numberButtons[8], 1, 1);
		grid.add(numberButtons[9], 2, 1);
		grid.add(divideButton, 3, 1);
		grid.add(numberButtons[4], 0, 2);
		grid.add(numberButtons[5], 1, 2);
		grid.add(numberButtons[6], 2, 2);
		grid.add(multiplyButton, 3, 2);
		grid.add(numberButtons[1], 0, 3);
		grid.add(numberButtons[2], 1, 3);
		grid.add(numberButtons[3], 2, 3);
		grid.add(subtractbutton, 3, 3);
		grid.add(numberButtons[0], 0, 4);
		grid.add(clearButton, 1, 4);
		grid.add(equalsButton, 2, 4);
		grid.add(addButton, 3, 4);

		for (int i = 0; i < 10; i++) {
			numberButtons[i].setStyle("-fx-background-color: linear-gradient(to bottom, #FFA500, #FF8C00);");
		}
		addButton.setStyle("-fx-background-color: linear-gradient(to bottom, #FFA500, #FF8C00);");
		subtractbutton.setStyle("-fx-background-color: linear-gradient(to bottom, #FFA500, #FF8C00);");
		multiplyButton.setStyle("-fx-background-color: linear-gradient(to bottom, #FFA500, #FF8C00);");
		divideButton.setStyle("-fx-background-color: linear-gradient(to bottom, #FFA500, #FF8C00);");
		equalsButton.setStyle("-fx-background-color: linear-gradient(to bottom, #FFA500, #FF8C00);");

		Scene scene = new Scene(grid, 300, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Button createOperatorButton(String op) {
		Button button = new Button(op);
		button.setOnAction(e -> handleOperator(op));
		button.setStyle("-fx-background-color: linear-gradient(to bottom, #001F3F, #003366); -fx-text-field: white;");
		return button;
	}

	private void appendToInput(String value) {
		currentInput += value;
		display.setText(currentInput);
	}

	private void handleOperator(String op) {
		operator = op;
		result = Double.parseDouble(currentInput);
		currentInput = "";
	}

	private void calculator() {
		double secondOperand = Double.parseDouble(currentInput);
		switch (operator) {
		case "+":
			result += secondOperand;
			break;

		case "-":
			result -= secondOperand;
			break;

		case "*":
			result *= secondOperand;
			break;

		case "/":
			if (secondOperand != 0) {
				result /= secondOperand;
			} else {
				display.setText("Error");
				clear();
				return;
			}
			break;
		}
		display.setText(Double.toString(result));
		currentInput = "";
		operator = "";
	}

	private void clear() {
		currentInput = "";
		result = 0.0;
		operator = "";
		display.setText("");
	}
}
