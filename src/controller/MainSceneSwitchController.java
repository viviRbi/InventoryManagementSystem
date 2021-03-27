package controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class MainSceneSwitchController extends Controller{

    public void jumpToAddPartScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"addPartScreen", "Add Part Screen", 749.0, 625.0);
    }

    public void jumpToModifyPartScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"modifyPartScreen", "Modify Part Screen", 749.0, 625.0);
    }

    public void jumpToAddProductScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"addProductScreen", "Add Product Screen", 1200.0, 800.0);
    }

    public void jumpToModifyProductScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"modifyProductScreen", "Modify Product Screen", 1200.0, 800.0);
    }
}
