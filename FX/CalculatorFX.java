package com.example.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorFX extends Application {

    TextField textField;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    @Override
    public void start(Stage primaryStage) {
        textField = new TextField();
        textField.setEditable(false);
        textField.setStyle("-fx-font-size: 20px;");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Number buttons
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setStyle("-fx-font-size: 20px;");
            numberButtons[i].setOnAction(e -> {
                Button source = (Button) e.getSource();
                textField.setText(textField.getText() + source.getText());
            });
        }

        // Operator buttons
        Button addButton = new Button("+");
        Button subButton = new Button("-");
        Button mulButton = new Button("*");
        Button divButton = new Button("/");
        Button decButton = new Button(".");
        Button equButton = new Button("=");
        Button clrButton = new Button("Clr");
        Button delButton = new Button("Del");
        Button negButton = new Button("(-)");

        Button[] functionButtons = {addButton, subButton, mulButton, divButton, decButton, equButton, clrButton, delButton, negButton};
        for (Button btn : functionButtons) {
            btn.setStyle("-fx-font-size: 20px;");
        }

        addButton.setOnAction(e -> handleOperator('+'));
        subButton.setOnAction(e -> handleOperator('-'));
        mulButton.setOnAction(e -> handleOperator('*'));
        divButton.setOnAction(e -> handleOperator('/'));

        equButton.setOnAction(e -> calculate());
        clrButton.setOnAction(e -> textField.setText(""));
        decButton.setOnAction(e -> textField.setText(textField.getText() + "."));

        delButton.setOnAction(e -> {
            String currentText = textField.getText();
            if (!currentText.isEmpty()) {
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        });

        negButton.setOnAction(e -> {
            if (!textField.getText().isEmpty()) {
                double value = Double.parseDouble(textField.getText()) * -1;
                textField.setText(String.valueOf(value));
            }
        });

        // Layout: Adding buttons to grid
        grid.addRow(0, textField);
        grid.addRow(1, numberButtons[1], numberButtons[2], numberButtons[3], addButton);
        grid.addRow(2, numberButtons[4], numberButtons[5], numberButtons[6], subButton);
        grid.addRow(3, numberButtons[7], numberButtons[8], numberButtons[9], mulButton);
        grid.addRow(4, decButton, numberButtons[0], equButton, divButton);
        grid.addRow(5, negButton, delButton, clrButton);

        Scene scene = new Scene(grid, 300, 400);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleOperator(char op) {
        num1 = Double.parseDouble(textField.getText());
        operator = op;
        textField.setText("");
    }

    private void calculate() {
        num2 = Double.parseDouble(textField.getText());
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        textField.setText(String.valueOf(result));
        num1 = result; // Store the result for further calculations
    }

    public static void main(String[] args) {
        launch(args);
    }
}

