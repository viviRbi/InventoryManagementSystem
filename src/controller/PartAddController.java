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

/**
 * @author Vy Le
 * Part Add Controller
 */
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

    /**
     * Instnciate Part. Create id based on observable size. When a part delete, other part takes its id
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int partId = Inventory.getAllParts().size() + 1;
        this.partId = partId;
        id.setText(partId + "");
    }

    /**
     * Add Part to the static Observable Part in Inventory
     * @param event
     * @throws Exception
     */
    public void addPart(ActionEvent event) throws Exception{

        try{

            String name = this.name.getText();
            int inv = Integer.parseInt(stock.getText());
            double price = Double.parseDouble(this.price.getText());
            int max = Integer.parseInt(this.max.getText());
            int min = Integer.parseInt(this.min.getText());

            if (inv < min || inv > max  || max <0 || min<0){
                errorAlert("Min Max Inventory Error", "Inventory should be between min and max. Max and Min should be positive");
            } else if (name.trim().equals("")) this.errorAlert("Empty Space Error","Field must not be empty");
            else if (price < 0) this.errorAlert("Price Error","Price must be positive");
            else {
                Part newPart;
                if (toggleSource.getSelectedToggle().equals(inhouse)) {
                    int machineId = Integer.parseInt(this.machineId.getText());
                    newPart = new InHouse(this.partId, name, price, inv, min, max, machineId);
                } else {
                    String companyName = this.machineId.getText();
                    newPart = new Outsourced(this.partId, name, price, inv, min, max, companyName);
                }
                Inventory.addPart(newPart);
                this.jumpToMainScreen(event);
            }
        }catch(NumberFormatException e){
            this.errorAlert("Number error","Please make sure that you enter a valid number");
        }

    }

    /**
     * Base on user choice, change text to Machine Id/ Company Name
     * @param event
     * @throws Exception
     */
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
