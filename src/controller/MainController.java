package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends MainSceneSwitchController implements Initializable {

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partId;

    @FXML
    private TableColumn<Part, Double>  partPrice;

    @FXML
    private TableColumn<Part, Integer>  partStock;


    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Product, Integer> productId;

    @FXML
    private TableColumn<Product, Double>  productPrice;

    @FXML
    private TableColumn<Product, Integer>  productInv;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.displayPart();
        this.displayProduct();
    }

    public void displayPart(){
        partId.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        partTable.setItems(Inventory.getAllParts());
    }

    public void displayProduct(){
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInv.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productTable.setItems(Inventory.getAllProducts());
    }



    @FXML
    public void ExitButton(ActionEvent event) throws Exception {
        try {

            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
