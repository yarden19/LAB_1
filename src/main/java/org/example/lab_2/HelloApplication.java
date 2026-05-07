package org.example.lab_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {

    public static int n;
    public static int t;

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Lab 3 - Multi-threading Login!");
        stage.setScene(scene);
        stage.show();
    }

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter max failed attempts (n): ");
            n = scanner.nextInt();

            System.out.print("Enter lockout time in seconds (t): ");
            t = scanner.nextInt();

            launch();
        }
    }

