package com.example.newc4823;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**This is the controller for inhouse version of the add part form*/
public class AddPartInhouseController {

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
    private TextField MachineIDtxt;



    /**This saves a part added inhouse and its information*/
    @FXML
    void onActionSavePart (ActionEvent event) throws IOException {

        try {
            int id = getClass().getModifiers();
            String name = Nametxt.getText();
            int stock = Integer.parseInt(Stocktxt.getText());
            int max = Integer.parseInt(Maxtxt.getText());
            double price = Double.parseDouble(Pricetxt.getText());
            int min = Integer.parseInt(Mintxt.getText());
            int machineId = Integer.parseInt(MachineIDtxt.getText());
            boolean inHouse;


            if(inhouseradio.isSelected())
                inHouse = true;
            else
                inHouse = false;

            if (min >= max) {
                throw new IllegalArgumentException("Min should be less than Max");
            }
            if (stock < min || stock > max) {
                throw new IllegalArgumentException("Stock should be between Min and Max");
            }


            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch ( RuntimeException e) {
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

    /**this changes to the outsourced version of the add parts form*/
    @FXML
    void onActionDisplayOutsourcedForm (ActionEvent event) throws IOException {
        stage = (Stage)((RadioButton)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("AddPartOutsourced.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }



}
