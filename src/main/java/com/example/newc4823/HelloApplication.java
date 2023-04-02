package com.example.newc4823;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**a future enhancement that would extend the functionality of the application would be storing data in a database for persistance.*/
/**this is the main part of the application, it launches to the main page of the application and holds data.*/
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("Inventory");
        stage.setScene(scene);
        stage.show();
    }

    /**test data is here and populates in the application tables on launch.*/
    public static void main(String[] args) {
        InHouse inhouse = new InHouse(1, "Handle", 40.00, 30, 10, 50, 3);
        InHouse inhouse1 = new InHouse(2, "Seat", 50.00, 30, 10, 50, 5);
        InHouse inhouse2 = new InHouse(3, "Tire", 20.00, 30, 10, 50, 7);
        Outsourced outsourced = new Outsourced(89, "Chain", 20.00, 30, 10, 50, "BikeCo");
        Outsourced outsourced1 = new Outsourced(78, "Rim", 20.00, 30, 10, 50, "BikeCo");
        Outsourced outsourced2 = new Outsourced(45, "InnerTube", 20.00, 30, 10, 50, "BikeCo");

        Product product = new Product( 78, "Bike", 2000.00, 30, 10, 50);
        Product product1 = new Product(45, "Car", 20000.00, 30, 10, 50);
        Product product2 = new Product(12, "Trike", 3000.00, 30, 10, 50);




        Inventory.addPart(inhouse);
        Inventory.addPart(inhouse1);
        Inventory.addPart(inhouse2);
        Inventory.addPart(outsourced);
        Inventory.addPart(outsourced1);
        Inventory.addPart(outsourced2);

        Inventory.addProduct(product);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);





        launch();
    }
}