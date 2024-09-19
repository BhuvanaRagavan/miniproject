package com.example.javafx;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CurrencyconverterFX extends Application {

    TextField amountInput;
    ComboBox<String> fromCurrency;
    ComboBox<String> toCurrency;
    Label resultLabel;

    // Inputs
    final double USD_TO_EUR = 0.85;
    final double USD_TO_INR = 82.57;
    final double EUR_TO_USD = 1.18;
    final double EUR_TO_INR = 97.15;
    final double INR_TO_USD = 0.012;
    final double INR_TO_EUR = 0.010;

    @Override
    public void start(Stage primaryStage) {
        // UI elements
        Label amountLabel = new Label("Amount:");
        amountInput = new TextField();

        Label fromLabel = new Label("From Currency:");
        fromCurrency = new ComboBox<>();
        fromCurrency.getItems().addAll("USD", "EUR", "INR");

        Label toLabel = new Label("To Currency:");
        toCurrency = new ComboBox<>();
        toCurrency.getItems().addAll("USD", "EUR", "INR");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convertCurrency());

        resultLabel = new Label("Result:");

        // Layout setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(amountLabel, 0, 0);
        grid.add(amountInput, 1, 0);
        grid.add(fromLabel, 0, 1);
        grid.add(fromCurrency, 1, 1);
        grid.add(toLabel, 0, 2);
        grid.add(toCurrency, 1, 2);
        grid.add(convertButton, 1, 3);
        grid.add(resultLabel, 1, 4);

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountInput.getText());
            String from = fromCurrency.getValue();
            String to = toCurrency.getValue();

            if (from == null || to == null) {
                resultLabel.setText("Select currencies.");
                return;
            }

            double convertedAmount = 0;

            // Conversion logic
            switch (from + " to " + to) {
                case "USD to EUR":
                    convertedAmount = amount * USD_TO_EUR;
                    break;
                case "USD to INR":
                    convertedAmount = amount * USD_TO_INR;
                    break;
                case "EUR to USD":
                    convertedAmount = amount * EUR_TO_USD;
                    break;
                case "EUR to INR":
                    convertedAmount = amount * EUR_TO_INR;
                    break;
                case "INR to USD":
                    convertedAmount = amount * INR_TO_USD;
                    break;
                case "INR to EUR":
                    convertedAmount = amount * INR_TO_EUR;
                    break;
                case "USD to USD":
                case "EUR to EUR":
                case "INR to INR":
                    convertedAmount = amount;  // No conversion needed if same currency
                    break;
                default:
                    resultLabel.setText("Conversion not available.");
                    return;
            }

            resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, to));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

