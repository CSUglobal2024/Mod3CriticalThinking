package com.csc372.mod3criticalthinking;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class App extends Application {

    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Demo");

        BorderPane borderPane = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem printDateTimeItem = new MenuItem("Print Date & Time");
        printDateTimeItem.setOnAction(e -> printDateTime());
        MenuItem writeToFileItem = new MenuItem("Write to File");
        writeToFileItem.setOnAction(e -> writeToFile());
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().addAll(printDateTimeItem, writeToFileItem, new SeparatorMenuItem(), exitItem);

        Menu colorMenu = new Menu("Color");
        MenuItem changeColorItem = new MenuItem("Change Background Color");
        changeColorItem.setOnAction(e -> changeBackgroundColor());
        colorMenu.getItems().add(changeColorItem);

        menuBar.getMenus().addAll(fileMenu, colorMenu);

        textArea = new TextArea();
        textArea.setWrapText(true);

        borderPane.setTop(menuBar);
        borderPane.setCenter(textArea);

        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void printDateTime() {
        LocalDateTime now = LocalDateTime.now();
        textArea.appendText(now.toString() + "\n");
    }

     private static final String FILE_PATH = "C:/Users/18135/Documents/log.txt";

    private void writeToFile() {
        try {
            String content = textArea.getText();
            FileWriter writer = new FileWriter(new File(FILE_PATH));
            writer.write(content);
            writer.close();
            System.out.println("File 'log.txt' has been successfully created.");

        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file:");
            e.printStackTrace();
        }
    }

    private void changeBackgroundColor() {
        double hue = Math.random();
        Color color = Color.hsb(hue * 360, 1.0, 1.0);
        textArea.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
