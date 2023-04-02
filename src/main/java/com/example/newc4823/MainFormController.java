package com.example.newc4823;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

/**This is the controller for the main page of the inventory management system.*/
public class MainFormController implements Initializable {
    Stage stage;
    Parent scene;
    private static Part partToModify;

    private Stage primaryStage;
    private Scene scene1, scene2;
    @FXML
    private TextField search1;
    @FXML
    private TextField search2;
    @FXML
    private Button deletebtn;
    @FXML
    private Button modifybtn;
    @FXML
    private Button addbtn;
    @FXML
    private Button deletebtn2;
    @FXML
    private Button modifybtn2;
    @FXML
    private Button addbtn2;
    @FXML
    private Button exitbtn;
    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn <Part, Integer> idCol;
    @FXML
    private TableColumn <Part, String> nameCol;
    @FXML
    private TableColumn <Part, Double> priceCol;
    @FXML
    private TableColumn <Part, Integer> stockCol;
    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn <Product, Integer> prodIdCol;
    @FXML
    private TableColumn <Product, String> prodNameCol;
    @FXML
    private TableColumn <Product, Double> prodPriceCol;
    @FXML
    private TableColumn <Product, Integer> prodStockCol;

    public static Part getPartToModify() {
        return partToModify;
    }
/**this provides functionality to the parts search bar.*/
    @FXML
    public void onActionSearchParts (ActionEvent event) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String s = search1.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(s) ||
                    part.getName().contains(s)) {
                partsFound.add(part);
            }
        }
        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Part not found");
            alert.showAndWait();
        }
    }

    /**This allows for searches by part name or id.*/
    void onActionSearchParts (KeyEvent event) {
        if (search1.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }

    /**this provides functionality to the products search bar.*/
    @FXML
    void onActionSearchProducts (ActionEvent event) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String ss = search2.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(ss) ||
            product.getName().contains(ss)) {
                productsFound.add(product);
            }
        }
        productTableView.setItems(productsFound);

        if (productsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Product not found");
            alert.showAndWait();
        }
    }

    /**This allows for searches by product name or id.*/
    void onActionSearchProducts (KeyEvent event) {
        if (search2.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
    }

    /**This opens the page to add a new part.*/
    @FXML
    void onActionAddPart (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("AddPartInhouse.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This allows for a user to delete a part.*/
    @FXML
    void onActionDeletePart (ActionEvent event) throws IOException {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Selected part will be deleted, do you want to continue?");
        Optional<ButtonType> result = alert1.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK ){
            int idToDelete = partTableView.getSelectionModel().getSelectedItem().getId();
            Iterator<Part> iterator = partTableView.getItems().iterator();
            while (iterator.hasNext()) {
                Part part = iterator.next();
                if (part.getId() == idToDelete) {
                    iterator.remove();
                }

            }
        }
        else if (result.isPresent() && result.get() == ButtonType.CANCEL)

        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Error Dialog");
            alert2.setContentText("Part not deleted");
            alert2.showAndWait();

        }

    }

    /**This opens the page to modify a part, it carries over existing values to be edited
     * it can be switched between inhouse and outsourced.*/
    @FXML
    void onActionModifyPart (ActionEvent event) throws IOException {

        partToModify = partTableView.getSelectionModel().getSelectedItem();

        if (partToModify == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Please select a part to modify");
            alert.showAndWait();
        }
        else {

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ModifyPart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ModifyPartController MPIcontroller = loader.getController();
            MPIcontroller.sendPart(partToModify);

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

    }

    /**This opens the page to add a new product.*/
    @FXML
    void onActionAddProduct (ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("AddProductform.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**runtime error where it would search and return error for each product was corrected here.*/
    /**This allows a user to delete a product, but not a product with parts associated.*/
    @FXML
    void onActionDeleteProduct (ActionEvent event) throws IOException {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (!selectedProduct.getAssociatedParts().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Cannot delete product with associated parts");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Selected product will be deleted, do you want to continue?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            int idToDelete = selectedProduct.getId();
            Iterator<Product> iterator = productTableView.getItems().iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getId() == idToDelete) {
                    iterator.remove();
                }
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Product not deleted");
            errorAlert.showAndWait();
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(HelloApplication.class.getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This opens the page to modify a product and carries over existing values to be edited.*/
    @FXML
    void onActionModifyProduct (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("ModifyProductForm.fxml"));
        loader.load();

        ModifyProductFormController MPFController = loader.getController();
        MPFController.sendProduct(productTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**This button exits the application.*/
    @FXML
    void onActionExit (ActionEvent event) throws IOException {
        System.exit(0);
    }


    /**This displays the data in the tables for parts and products.*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        partTableView.setItems(Inventory.getAllParts());


        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productTableView.setItems(Inventory.getAllProducts());

        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));


    }
}