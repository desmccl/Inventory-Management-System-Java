package com.example.newc4823;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**This is the Product class.*/

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private static int nextId = 1;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product (int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public Product (String name, double price, int stock, int min, int max) {
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /**returns the id.*/
    public int getId() {
        return id;
    }

    /**id is auotgenerated.*/
    public void setId(int id) {
        this.id = id;
    }
/**returns the name.*/
    public String getName() {
        return name;
    }
/**sets the name parameter to name.*/
    public void setName(String name) {
        this.name = name;
    }
/**returns the price*/
    public double getPrice() {
        return price;
    }
/**sets the price parameter to price.*/
    public void setPrice(double price) {
        this.price = price;
    }
/**returns the stock.*/
    public int getStock() {
        return stock;
    }
/**sets the stock parameter to stock.*/
    public void setStock(int stock) {
        this.stock = stock;
    }
/**returns the min.*/
    public int getMin() {
        return min;
    }
/**sets the min parameter to min.*/
    public void setMin(int min) {
        this.min = min;
    }
/**returns the max.*/
    public int getMax() {
        return max;
    }
/**sets the max parameter to max.*/
    public void setMax(int max) {
        this.max = max;
    }
/**adds a part to observable list of associated parts.*/
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
/**removes a part from observable list of associated parts.*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        boolean deleted = associatedParts.remove(selectedAssociatedPart);
        return deleted;
    }
/**returns observable list of associated parts.*/
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
}

