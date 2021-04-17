package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.net.URL;
import java.util.ResourceBundle;

public class PartAddController extends Controller implements Initializable {

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

    int partId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int partId = Inventory.getAllParts().size() + 1;
        this.partId = partId;
        id.setText(partId + "");
    }

    public void addPart(ActionEvent event) throws Exception{

        try{

            String name = this.name.getText();
            int inv = Integer.parseInt(stock.getText());
            double price = Double.parseDouble(this.price.getText());
            int max = Integer.parseInt(this.max.getText());
            int min = Integer.parseInt(this.min.getText());

            if (inv < min || inv > max){
                errorAlert("Min Max Invenventory Error", "Inventory should be between min and max");
            }
            Part newPart;
            if(toggleSource.getSelectedToggle().equals(inhouse)){
                int machineId = Integer.parseInt(this.machineId.getText());
                newPart = new InHouse(this.partId,name,price,inv,min,max,machineId);
            }else {
                String companyName = this.machineId.getText();
                newPart = new Outsourced(this.partId,name,price,inv,min,max,companyName);
            }
            Inventory.addPart(newPart);
            this.jumpToMainScreen(event);
        }catch(NumberFormatException e){
            this.errorAlert("Number error","Please make sure that you enter a valid number");
        }

    }

    @FXML
    public void outSouceInhouseToggle(ActionEvent event) throws Exception {

        if (toggleSource.getSelectedToggle().equals(inhouse)) {
            machineIdToggle.setText("Machine ID");
        }

        if (toggleSource.getSelectedToggle().equals(outsourced)) {
            machineIdToggle.setText("Company Name");
        }

    }
}
