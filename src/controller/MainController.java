package controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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

    @FXML
    private TextField partSearch;
    @FXML
    private TextField productSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.displayPart();
        this.displayProduct();
        this.searchPart();
        this.searchProduct();

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

    public void searchPart(){
        FilteredList<Part> filteredParts = new FilteredList<>(Inventory.allParts, b -> true);

        partSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredParts.setPredicate(part -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (part.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(part.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Part> sortedListParts = new SortedList<>(filteredParts);
        sortedListParts.comparatorProperty().bind(partTable.comparatorProperty());
        partTable.setItems(sortedListParts);
    }

    public void searchProduct(){
        FilteredList<Product> filteredProducts = new FilteredList<>(Inventory.allProducts, b -> true);

        productSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProducts.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (product.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(product.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Product> sortedListProducts = new SortedList<>(filteredProducts);
        sortedListProducts.comparatorProperty().bind(productTable.comparatorProperty());
        productTable.setItems(sortedListProducts);
    }

    @FXML
    public void deletePart(ActionEvent event) throws Exception{
        int selectedIndex = partTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setTitle("Delete Part");
            errorAlert.setHeaderText("Do you really want to delete this part?");

            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            errorAlert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

            Optional<ButtonType> result = errorAlert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Inventory.allParts.remove(selectedIndex);
            } else {
                errorAlert.close();
            }
        }
    }

    @FXML
    public void deleteProduct(ActionEvent event) throws Exception{
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setTitle("Delete Product");
            errorAlert.setHeaderText("Do you really want to delete this product?");

            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            errorAlert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
            Optional<ButtonType> result = errorAlert.showAndWait();

            if (result.get() == buttonTypeYes) {
                Inventory.allProducts.remove(selectedIndex);
            } else {
                errorAlert.close();
            }
        }
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
