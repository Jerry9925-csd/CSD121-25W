package ca.saultcollege.hello_word;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Dropdown for unit selection
        ComboBox<String> unitSelector = new ComboBox<>();
        unitSelector.getItems().addAll(
                "Length (Meters to Feet)", "Length (Feet to Meters)",
                "Temperature (Celsius to Fahrenheit)", "Temperature (Fahrenheit to Celsius)",
                "Weight (Kg to Lbs)", "Weight (Lbs to Kg)",
                "Distance (Miles to Kilometers)", "Distance (Kilometers to Miles)",
                "Length (Inches to Centimeters)", "Length (Centimeters to Inches)"
        );
        unitSelector.setValue("Length (Meters to Feet)"); // Default value

        // Input field
        TextField inputField = new TextField();
        inputField.setPromptText("Enter value");

        // Convert button
        Button convertButton = new Button("Convert");
        convertButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Label to display result
        Label resultLabel = new Label("Result: ");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");

        // ListView to show conversion history
        ListView<String> historyList = new ListView<>();
        historyList.setPrefHeight(150);

        // Mapping unit type to converter class
        Map<String, Converter> converters = new HashMap<>();
        converters.put("Length (Meters to Feet)", new LengthConverter());
        converters.put("Length (Feet to Meters)", new LengthConverter());
        converters.put("Temperature (Celsius to Fahrenheit)", new TemperatureConverter());
        converters.put("Temperature (Fahrenheit to Celsius)", new TemperatureConverter());
        converters.put("Weight (Kg to Lbs)", new WeightConverter());
        converters.put("Weight (Lbs to Kg)", new WeightConverter());
        converters.put("Distance (Miles to Kilometers)", new DistanceConverter());
        converters.put("Distance (Kilometers to Miles)", new DistanceConverter());
        converters.put("Length (Inches to Centimeters)", new LengthInchesToCmConverter());
        converters.put("Length (Centimeters to Inches)", new LengthInchesToCmConverter());

        // Event handling for conversion
        convertButton.setOnAction(event -> {
            try {
                // Ensure input is a valid number
                double value = Double.parseDouble(inputField.getText());

                // Handle conversion
                Converter converter = converters.get(unitSelector.getValue());
                double result;

                if (unitSelector.getValue().contains("to")) {
                    result = converter.convert(value);  // Standard conversion
                } else {
                    result = converter.reverseConvert(value);  // Reverse conversion
                }

                // Update result label
                resultLabel.setText("Result: " + result);

                // Add conversion to history
                String historyEntry = value + " " + unitSelector.getValue() + " = " + result;
                historyList.getItems().add(0, historyEntry); // Add new entry at the top

            } catch (NumberFormatException e) {
                resultLabel.setText("Please enter a valid number!");
            } catch (Exception e) {
                resultLabel.setText("An error occurred during conversion.");
            }
        });

        // Layout
        VBox layout = new VBox(15, unitSelector, inputField, convertButton, resultLabel, historyList);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20; -fx-background-color: #f4f4f4;");

        // Scene setup
        Scene scene = new Scene(layout, 320, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Unit Converter with History");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}