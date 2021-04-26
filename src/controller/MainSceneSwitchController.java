package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Part;
import model.Product;

import java.io.IOException;

public class MainSceneSwitchController extends Controller{

    private Part selectedPart;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableView<Product> productTable;

    public void jumpToAddPartScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"addPartScreen", "Add Part Screen", 749.0, 625.0);
    }

    public void jumpToModifyPartScreen(ActionEvent actionEvent) throws IOException {
        try{

            Part modifyPart = partTable.getSelectionModel().getSelectedItem();
            PartModifyController mC = new PartModifyController();
            mC.setSelectedPart(modifyPart);

            jumpScreen(actionEvent,"modifyPartScreen", "Modify Part Screen", 749.0, 625.0);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void jumpToAddProductScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"addProductScreen", "Add Product Screen", 1200.0, 800.0);
    }

    public void jumpToModifyProductScreen(ActionEvent actionEvent) throws IOException {
        try{
            Product modifyProduct = productTable.getSelectionModel().getSelectedItem();
            ProductModifyController pC = new ProductModifyController();
            pC.setSelectedProduct(modifyProduct);
            jumpScreen(actionEvent,"modifyProductScreen", "Modify Product Screen", 1200.0, 800.0);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
