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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends MainSceneSwitchController implements Initializable {

    @FXML
    private TableView<Part> tableView;

    @FXML
    private TableColumn<Part, String> partName;

    @FXML
    private TableColumn<Part, Integer> partId;

    @FXML
    private TableColumn<Part, Double>  partPrice;

    @FXML
    private TableColumn<Part, Integer>  partStock;

    //@FXML
    //private TableColumn<Part, Integer>  partMin;

   // @FXML
    //private TableColumn<Part, Integer>  partMax;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partId.setCellValueFactory(new PropertyValueFactory<>("partId"));
        partName.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        partStock.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        //partMin.setCellValueFactory(new PropertyValueFactory<>("partMin"));
        //partMax.setCellValueFactory(new PropertyValueFactory<>("partMax"));

        tableView.getItems(Inventory.getAllParts());
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
