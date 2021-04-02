package controller;

import javafx.scene.control.Alert;

public class AlertHelper{

    public static void errorAlert(String Title, String Massage) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(Title);
        errorAlert.setContentText(Massage);
        errorAlert.showAndWait();
    }
}
