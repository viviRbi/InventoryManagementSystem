/**
 * Supplied class Part.java
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * @author Vy Le
 * Product class
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList associatePart = FXCollections.observableArrayList();

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     */
    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param selected selected part from a Part table (top right table)
     */
    public void addAssociatePart(Part selected){
        this.associatePart.add(selected);
    }

    /**
     * @param selected select the observable list of Part from static chosenList in Inventory class. Final step before safe this Product object
     */
    public void addAssociatePart(ObservableList selected){
        //System.out.println(selected);
        this.associatePart.addAll(selected);
    }

    /**
     * @param part  After a product saved, this will take the selected part from the SecondPart table and remove it, then save the product
     * @return boolean if the product had been remove or not
     */
    public boolean deleteAssociatePart(Part part){
        if(this.associatePart.contains(part)){
            this.associatePart.remove(part);
            return true;
        } else return false;


    }

    /**
     * @return AllAssociate part
     */
    public ObservableList<Part> getAllAssociateParts(){
        return this.associatePart;
    }
}