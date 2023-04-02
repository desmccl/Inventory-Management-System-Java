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

/**This is the controller for the modify product page.*/
public class ModifyProductFormController implements Initializable {
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

    /**This provides functionality for the parts search bar.*/
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

    /**This allows for searches by part name.*/
    private ObservableList<Part> searchByPartName(String name) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part parts : allParts) {
            if (parts.getName().contains(name)) {
                namedParts.add(parts);
            }

        }

        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Part not found");
            alert.showAndWait();
        }

        return namedParts;
    }

    /**This allows for searches by part id.*/
    private Part searchByPartId(int id) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(int i=0; i < allParts.size(); i++){
            Part part = allParts.get(i);

            if (part.getId() == id){
                return part;
            }
        }

        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Part not found");
            alert.showAndWait();
        }

        return null;
    }

    /**This adds a part to the associated parts of the product.*/
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        ObservableList<Part> selectedItems = partTableView.getSelectionModel().getSelectedItems();

        associatedPartsTableView.getItems().addAll(selectedItems);

    }

    /**This redirects to the main page.*/
    @FXML
    void onActionCancelProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "All updates will be lost, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK ){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**This removes associated parts from a product.*/
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

    /**This saves the modified product.*/
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(IDtxt.getText());
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
            Product existingProduct = Inventory.lookupProduct(id);
            if (existingProduct == null) {
                throw new IllegalArgumentException("Product with ID " + id + " not found in inventory");
            }

            // Create new product instance with updated values
            Product updatedProduct = new Product(id, name, price, stock, min, max);
            for (Part part : associatedParts) {
                updatedProduct.addAssociatedPart(part);
            }

            // Update product in inventory
            ObservableList<Product> allProducts = Inventory.getAllProducts();
            for (int i = 0; i < allProducts.size(); i++) {
                Product product = allProducts.get(i);
                if (product.getId() == id) {
                    allProducts.set(i, updatedProduct);
                    break;
                }
            }

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please check that all values are correct.");
            alert.showAndWait();
        }

    }



    /**This allows for communication of information about the product to the main page.*/
    public void sendProduct(Product product) {
        IDtxt.setText(String.valueOf(product.getId()));
        Nametxt.setText((product.getName()));
        Stocktxt.setText(String.valueOf(product.getStock()));
        Maxtxt.setText(String.valueOf(product.getMax()));
        Pricetxt.setText(String.valueOf(product.getPrice()));
        Mintxt.setText(String.valueOf(product.getMin()));

        associatedPartsTableView.setItems(product.getAssociatedParts());



    }

    /**this displays relevant information in the tables.*/
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
