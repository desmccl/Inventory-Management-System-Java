package com.example.newc4823;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**this is the inventory class, it holds information about the inventory of parts and products.*/
public class Inventory {

    public static int nextPartId = 1;

    /**observable list for all parts.*/
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**observable list for all products.*/
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**this allows to add parts.*/
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**this allows to add products.*/
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**this allows to lookup parts by Id.*/
    public static int lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {

            }
        }
        return partID;
    }

    /**this allows to lookup products by Id.*/
    public static Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null;

    }
    /**observable list for looking up parts.*/
    public static ObservableList<Part> lookupPart(String partName) {
        return lookupPart(partName);
    }
    /**observable list for looking up products.*/
    public static ObservableList<Product> lookupProduct(String productName) {
        return lookupProduct(productName);
    }

    /**this allows to update parts.*/
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**this allows to update products.*/
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**this allows to delete a selected part.*/
    public static boolean deletePart(Part selectedPart) {
        boolean deleted = Inventory.deletePart(selectedPart);
        return deleted;
    }

    /**this allows to delete a product.*/
    public static boolean deleteProduct(Product selectedProduct) {
        boolean deleted = Inventory.deleteProduct(selectedProduct);
        return deleted;
    }

    /**this returns all parts in an observable list.*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    /**this returns all products in an observable list.*/
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
