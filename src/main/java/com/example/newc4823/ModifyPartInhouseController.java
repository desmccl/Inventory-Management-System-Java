package com.example.newc4823;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/*public class ModifyPartInhouseController implements Initializable {
    public Label partTextField;
    private Part selectedPart;

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

    @FXML
    private TextField CompanyNametxt;
    private InHouse inhouse;
    private Outsourced outsourced;



    /*@FXML
    void onActionSavePart(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(IDtxt.getText());
            String name = Nametxt.getText();
            int stock = Integer.parseInt(Stocktxt.getText());
            int max = Integer.parseInt(Maxtxt.getText());
            double price = Double.parseDouble(Pricetxt.getText());
            int min = Integer.parseInt(Mintxt.getText());
            int machineId;
            String companyName;
            boolean partAddSuccessful = false;

            if (min >= max) {
                throw new IllegalArgumentException("Min should be less than or equal to Max");
            }
            if (stock < min || stock > max) {
                throw new IllegalArgumentException("Stock should be between Min and Max");
            }

            if (inhouseradio.isSelected()) {

                int machineId = Integer.parseInt(partTextField.getText());
                InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(newInHousePart);
                partAddSuccessful = true;

            } else if (outsourcedradio.isSelected()) {

                String companyName = partTextField.getText();
                Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(newOutsourcedPart);
                partAddSuccessful = true;

            } else {
                throw new IllegalArgumentException("Please select either In-House or Outsourced");
            }

            if (partAddSuccessful) {
                Inventory.deletePart(selectedPart);
                onActionDisplayMainForm(event);
            }

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Modifying Part");
            alert.setContentText("Form contains blank fields or invalid values. " + e.getMessage());
            alert.showAndWait();
        }*/








    /*@FXML
    void onActionDisplayMainForm (ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "All updates will be lost, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK ){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**this switches over to outsourced version of the form.*/


    /*public void onActionDisplayOutsourced (ActionEvent event) throws IOException {
        partTextField.setText("Company Name");
    }

    public void onActionDisplayInhouse (ActionEvent event) throws IOException {
        partTextField.setText("Machine ID");
    }

    /**this communicates information to the main page controller.*/
    /*public void sendPart(InHouse inhouse) {
        IDtxt.setText(String.valueOf(inhouse.getId()));
        Nametxt.setText((inhouse.getName()));
        Stocktxt.setText(String.valueOf(inhouse.getStock()));
        Maxtxt.setText(String.valueOf(inhouse.getMax()));
        Pricetxt.setText(String.valueOf(inhouse.getPrice()));
        Mintxt.setText(String.valueOf(inhouse.getMin()));
        MachineIDtxt.setText(String.valueOf((inhouse).getMachineId()));

    }


    @Override
    public void initialize(URL , ResourceBundle rb) {

        selectedPart = MainFormController.getPartToModify();

        if (selectedPart instanceof InHouse) {
            inhouseradio.setSelected(true);
            partTextField.setText("Machine ID");
            MachineIDtxt.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof Outsourced){
            outsourcedradio.setSelected(true);
            partTextField.setText("Company Name");
            MachineIDtxt.setText(((Outsourced) selectedPart).getCompanyName());
        }

        IDtxt.setText(String.valueOf(selectedPart.getId()));
        Nametxt.setText(selectedPart.getName());
        Stocktxt.setText(String.valueOf(selectedPart.getStock()));
        Pricetxt.setText(String.valueOf(selectedPart.getPrice()));
        Maxtxt.setText(String.valueOf(selectedPart.getMax()));
        Mintxt.setText(String.valueOf(selectedPart.getMin()));
    }
    }*/

