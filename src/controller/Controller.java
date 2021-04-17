package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Part;

import java.io.IOException;

public class Controller {
    @FXML
    private TableView<Part> partTable;
    // Jump screen method use for all other controller
    @FXML
    public void jumpScreen(ActionEvent actionEvent, String viewName, String title, Double width, Double height) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/view/"+viewName+".fxml"));
            Stage stage =  (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, width, height));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e){
            System.out.println(e);
            System.out.println("Cannot jump to " + title);
        }
    }
    /*@FXML
    public void jumpModifyScreen(ActionEvent actionEvent, String viewName, String title, Double width, Double height) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/"+viewName+".fxml"));
        Parent root = loader.load();

        Part modifyPart = partTable.getSelectionModel().getSelectedItem();
        PartModifyController mC = new PartModifyController();
        mC.setSelectedPart(modifyPart);

        Stage stage =  (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, width, height));
        stage.setTitle(title);
        stage.show();
    }*/


    // jump back to the main screen, use for all cancel btn
    public void jumpToMainScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"mainScreen", "Main Screen", 1137.0, 480.0);
    }

    public void errorAlert(String title,String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(title);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }
}
