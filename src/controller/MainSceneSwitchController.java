package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Part;

import java.io.IOException;
import java.lang.reflect.Modifier;

public class MainSceneSwitchController extends Controller{
    @FXML
    private RadioButton inhouse;

    @FXML
    private RadioButton outsourced;

    @FXML
    private ToggleGroup toggleSource;

    @FXML
    private Label machineIdToggle;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField min;

    @FXML
    private TextField stock;

    @FXML
    private TextField price;

    @FXML
    private TextField max;

    @FXML
    private TextField machineId;

    private Part selectedPart;
    @FXML
    private TableView<Part> partTable;

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
        jumpScreen(actionEvent,"modifyProductScreen", "Modify Product Screen", 1200.0, 800.0);
    }
}
