package model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author Vy Le
 * Inventory class. Hold static field for Part, Product, currently chosen associate part
 */
public class Inventory {

    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static ObservableList<Part> chosenPart = FXCollections.observableArrayList();

    /**
     *
     * @return all Part
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @return all Product
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    /**
     *
     * @param newPart
     */
    public static void addPart (Part newPart){

        allParts.add(newPart);
    }

    /**
     *
     * @param newProduct
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

}
