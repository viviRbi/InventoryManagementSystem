package model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Inventory {

    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static void addPart (Part newPart){

        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId){
        return null;
    }

    public Product lookupProduct(int productId){
        return null;
    }

    public Part lookupPart(String name){
        return null;
    }

    public Product lookupProduct(String name){
        return null;
    }

    public void updatePart(int index, Part selectedPart){

    }

    public void updateProduct(int index, Product newProduct){

    }

    public boolean deletePart(Part selectedPart){
        return false;
    }

    public boolean deleteProduct(Product selectedProduct){
        return false;
    }



}
