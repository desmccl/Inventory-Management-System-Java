package com.example.newc4823;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**This is the controller for the outsourced version of the add parts form*/
public class AddPartOutsourcedController {
    Stage stage;
    Parent scene;
    @FXML
    public Button savebutton;
    @FXML
    public Button cancelbutton;
    @FXML
    public RadioButton inhouseradio;
    @FXML
    public RadioButton outsourcedradio;
    @FXML
    private TextField IDtxt;
    @FXML
    private TextField Nametxt;
    @FXML
    private TextField Stocktxt;
    @FXML
    private TextField Maxtxt;
    @FXML
    private TextField Pricetxt;
    @FXML
    private TextField Mintxt;
    @FXML
    private TextField CompNametxt;



    /**this saves a part added outsourced and its information*/
    @FXML
    void onActionSavePart (ActionEvent event) throws IOException {

        try {
            int id = getClass().getModifiers();
            String name = Nametxt.getText();
            int stock = Integer.parseInt(Stocktxt.getText());
            int max = Integer.parseInt(Maxtxt.getText());
            double price = Double.parseDouble(Pricetxt.getText());
            int min = Integer.parseInt(Mintxt.getText());
            boolean outsourced;
            String companyName = CompNametxt.getText();

            if(outsourcedradio.isSelected())
                outsourced = true;
            else
                outsourced = false;

            if (min >= max) {
                throw new IllegalArgumentException("Min should be less than Max");
            }
            if (stock < min || stock > max) {
                throw new IllegalArgumentException("Stock should be between Min and Max");
            }

            Inventory.addPart(new Outsourced(name, price, stock, min, max, companyName));

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please enter correct values.");
            alert.showAndWait();
        }



    }

    /**this redirects to the main form*/
    @FXML
    void onActionDisplayMainForm (ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK ){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**this displays the inhouse version of the add parts form*/
    @FXML
    void onActionDisplayInhouseForm (ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("AddPartInhouse.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


}
