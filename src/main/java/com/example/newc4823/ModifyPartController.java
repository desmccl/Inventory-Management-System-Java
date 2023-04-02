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

/**this is the controller for the modify parts page*/
public class ModifyPartController implements Initializable {
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

    private InHouse inhouse;
    private Outsourced outsourced;

    /**
     * this saves a modified part with all relevant updated information.
     */
    public void onActionSavePart(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(IDtxt.getText());
            String name = Nametxt.getText();
            int stock = Integer.parseInt(Stocktxt.getText());
            int max = Integer.parseInt(Maxtxt.getText());
            double price = Double.parseDouble(Pricetxt.getText());
            int min = Integer.parseInt(Mintxt.getText());
            if (inhouseradio.isSelected()) {

                int machineId = Integer.parseInt(MachineIDtxt.getText());
                InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                ObservableList<Part> allParts = Inventory.getAllParts();
                for (int i = 0; i < allParts.size(); i++) {
                    Part part = allParts.get(i);
                    if (part.getId() == id) {
                        allParts.set(i, newInHousePart);
                        break;
                    }
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            } else if (outsourcedradio.isSelected()) {

                String companyName = MachineIDtxt.getText();
                Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                ObservableList<Part> allParts = Inventory.getAllParts();
                for (int i = 0; i < allParts.size(); i++) {
                    Part part = allParts.get(i);
                    if (part.getId() == id) {
                        allParts.set(i, newOutsourcedPart);
                        break;
                    }
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error Modifying Part");
            alert.setContentText("Form contains invalid values. " + e.getMessage());
            alert.showAndWait();
        }
    }


    /**this redirects to the main page.*/
    public void onActionDisplayMainForm(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "All updates will be lost, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK ) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**this switches over to inhouse version of the form.*/
    public void onActionDisplayInhouse(ActionEvent event) {
        partTextField.setText("Machine ID");
    }

    /**this switches over to outsourced version of the form.*/
    public void onActionDisplayOutsourced(ActionEvent event) {
        partTextField.setText("Company Name");
    }

    /**this communicates information to the main page controller.*/
    public void sendPart(Part inhouse) {
        IDtxt.setText(String.valueOf(inhouse.getId()));
        Nametxt.setText((inhouse.getName()));
        Stocktxt.setText(String.valueOf(inhouse.getStock()));
        Maxtxt.setText(String.valueOf(inhouse.getMax()));
        Pricetxt.setText(String.valueOf(inhouse.getPrice()));
        Mintxt.setText(String.valueOf(inhouse.getMin()));


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

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
