package controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Part;
import model.Product;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Vy Le
 * Product Modify Controller
 */
public class ProductModifyController extends Controller implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField min;
    @FXML
    private TextField inv;
    @FXML
    private TextField price;
    @FXML
    private TextField max;
    @FXML
    private TableView<Part> secondPart;
    @FXML
    private TableColumn<Part, Integer> secondPartId;
    @FXML
    private TableColumn<Part, String> secondPartName;
    @FXML
    private TableColumn<Part, Integer> secondPartInv;
    @FXML
    private TableColumn<Part, Double> secondPartPrice;
    @FXML
    private TableView<Part> mainPart;
    @FXML
    private TableColumn<Part, Integer> mainPartId;
    @FXML
    private TableColumn<Part, String> mainPartName;
    @FXML
    private TableColumn<Part, Integer> mainPartInv;
    @FXML
    private TableColumn<Part, Double> mainPartPrice;
    @FXML
    private TextField searchField;
    private static Product selectedProduct;

    /**
     * Print Product and Part value to the table. Filter them
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setFieldText();

        secondPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        secondPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        secondPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        secondPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        secondPart.setItems(Inventory.getAllParts());

        mainPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        mainPart.setItems(Inventory.chosenPart);

        FilteredList<Part> filteredParts = new FilteredList<>(Inventory.allParts, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
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
        sortedListParts.comparatorProperty().bind(secondPart.comparatorProperty());
        secondPart.setItems(sortedListParts);
    }

    /**
     * When user clicked AddButton in Product Modify scene. Save Product and its associate Parts
     * @param actionEvent
     */
    @FXML
    public void addButton(ActionEvent actionEvent){
        try{
            int id = selectedProduct.getId();
            String name = this.name.getText();
            int stock = Integer.parseInt(this.inv.getText());
            double price = Double.parseDouble(this.price.getText());
            int max = Integer.parseInt(this.max.getText());
            int min = Integer.parseInt(this.min.getText());

            if(stock < min || stock > max || min<0 || max<0) {
                errorAlert("Min Max Inventory Error", " the inv Should be between max and min. Max & Min must be possitive ");
            }else if (name.trim().equals("")) this.errorAlert("Empty Space Error","Field must not be empty");
            else if (price < 0) this.errorAlert("Price Error","Price must be positive");
            else{
                int index = Inventory.getAllProducts().indexOf(selectedProduct);
                Product updatedProduct = new Product(id, name, price, stock, min, max);
                updatedProduct.addAssociatePart((ObservableList) Inventory.chosenPart);
                Inventory.allProducts.set(index, updatedProduct);

                this.name.clear();
                this.inv.clear();
                this.price.clear();
                this.max.clear();
                this.min.clear();

                Inventory.chosenPart.clear();

                this.jumpToMainScreen(actionEvent);
            }
        } catch (NumberFormatException | IOException nfe){
            this.errorAlert("Number error","Please make sure that you enter a valid number to min, max, inventory");
        }
    }

    /**
     * Add the selected part to static field chosenPart in Inventory class
     * @param actionEvent
     */
    @FXML
    public void addPartToProduct(ActionEvent actionEvent){
        Part selectedPart = secondPart.getSelectionModel().getSelectedItem();
        Inventory.chosenPart.add(selectedPart);
    }

    /**
     * Remove  selected part to static field chosenPart in Inventory class
     * @param actionEvent
     */
    @FXML
    public void removeAssociatePart(ActionEvent actionEvent){
        Part chosenPart = mainPart.getSelectionModel().getSelectedItem();

        Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
        errorAlert.setTitle("Delete Part");
        errorAlert.setHeaderText("Do you really want to delete this part?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        errorAlert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        Optional<ButtonType> result = errorAlert.showAndWait();
        if (result.get() == buttonTypeYes) {
            Inventory.chosenPart.remove(chosenPart);
        } else {
            if (Inventory.chosenPart != null) Inventory.chosenPart.remove(chosenPart);
            else selectedProduct.deleteAssociatePart(chosenPart);
        }

    }

    /**
     * Fet the product. Then add all of its associated parts to the static chosenPart field in Inventory class
     * @param product
     */
    public void setSelectedProduct(Product product) {
        //System.out.print(product);
        this.selectedProduct = product;
        Inventory.chosenPart.addAll(product.getAllAssociateParts());

    }

    /**
     * Set all product value to the text field. Used to instantiate
     */
    private void setFieldText() {
        String productName = selectedProduct.getName();
        String productInventory = Integer.toString(selectedProduct.getStock());
        String productPrice = Double.toString(selectedProduct.getPrice());
        String productId = Integer.toString(selectedProduct.getId());
        String productMax = Integer.toString(selectedProduct.getMax());
        String productMin = Integer.toString(selectedProduct.getMin());

        this.id.setText(productId);
        this.name.setText(productName);
        this.inv.setText(productInventory);
        this.price.setText(productPrice);
        this.id.setText(productId);
        this.max.setText(productMax);
        this.min.setText(productMin);
    }
}
