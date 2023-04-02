package com.example.newc4823;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

/**this is the controller for the add product form*/
public class AddProductFormController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private Button addbtn;
    @FXML
    private Button removebtn;
    @FXML
    private Button savebtn;
    @FXML
    private Button cancelbtn;
    @FXML
    private TextField searchtxt;
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
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> idCol;
    @FXML
    private TableColumn <Part, String> nameCol;
    @FXML
    private TableColumn <Part, Double> priceCol;
    @FXML
    private TableColumn <Part, Integer> stockCol;
    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML
    private TableColumn <Product, Integer> prodIdCol;
    @FXML
    private TableColumn <Product, String> prodNameCol;
    @FXML
    private TableColumn <Product, Double> prodPriceCol;
    @FXML
    private TableColumn <Product, Integer> prodStockCol;

    /**this adds functionality to the parts search bar*/
    @FXML
    void onActionSearchPart(ActionEvent event) {
        String s = searchtxt.getText();

        ObservableList<Part> parts = searchByPartName(s);

        if(parts.size() == 0) {
            try {
                int id = Integer.parseInt(s);
                Part part = searchByPartId(id);
                if (part != null)
                    parts.add(part);
            }
            catch(NumberFormatException e) {
                //ignore
            }
        }


        partTableView.setItems(parts);
    }

    /**this allows search by part name*/
    private ObservableList<Part> searchByPartName(String name) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part parts : allParts) {
            if (parts.getName().contains(name)) {
                namedParts.add(parts);
            }

        }

        return namedParts;
    }

    /**this allows search by part id*/
    private Part searchByPartId(int id) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(int i=0; i < allParts.size(); i++){
            Part part = allParts.get(i);

            if (part.getId() == id){
                return part;
            }
        }

        return null;
    }


    /**this adds parts to the associated parts of the product*/
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        ObservableList<Part> selectedItems = partTableView.getSelectionModel().getSelectedItems();

        associatedPartsTableView.getItems().addAll(selectedItems);

    }


    /**this redirects to the main form*/
    @FXML
    void onActionCancelProduct(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK ){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**this removes parts from the associated parts of the product*/
    @FXML
    void onActionRemoveProduct(ActionEvent event) throws IOException {
        Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Selected part will be deleted, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (selectedPart instanceof InHouse || selectedPart instanceof Outsourced) {
                Iterator<Part> iterator = associatedPartsTableView.getItems().iterator();
                while (iterator.hasNext()) {
                    Part part = iterator.next();
                    if (part == selectedPart) {
                        iterator.remove();
                    }
                }
            }
        } else {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Part not deleted");
            errorAlert.showAndWait();
        }


    }

    /**this saves the new product*/
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        try {
            int id = getClass().getModifiers();
            String name = Nametxt.getText();
            int stock = Integer.parseInt(Stocktxt.getText());
            int max = Integer.parseInt(Maxtxt.getText());
            double price = Double.parseDouble(Pricetxt.getText());
            int min = Integer.parseInt(Mintxt.getText());

            if (min >= max) {
                throw new IllegalArgumentException("Min should be less than Max");
            }
            if (stock < min || stock > max) {
                throw new IllegalArgumentException("Stock should be between Min and Max");
            }

            ObservableList<Part> associatedParts = associatedPartsTableView.getItems();
            Product newProduct = new Product(name,price,stock,min,max);
            for (Part part : associatedParts) {
                newProduct.addAssociatedPart(part);
            }
            Inventory.addProduct(newProduct);




            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
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

    /**this displays the tables and information related to them*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        partTableView.setItems(Inventory.getAllParts());

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        associatedPartsTableView.setItems(associatedPartsTableView.getItems());

        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }

}
