package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

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

    public void jumpToMainScreen(ActionEvent actionEvent) throws IOException {
        jumpScreen(actionEvent,"mainScreen", "Main Screen", 1137.0, 480.0);
    }
}
