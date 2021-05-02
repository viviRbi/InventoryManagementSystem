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
 * @author: Vy Le
 * Modify part scene controller
 */
public class PartModifyController extends Controller implements Initializable {

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

    // Static because setSelected method in MainSceneSwitchController cannot save anything into this controler once screen jump to Modifify Part Screen
    private static Part selectedPart;
    private static String altFieldText;

    /**
     * Initialize Part table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setField();
    }

    /**
     * Update part table
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    public void updatePart(ActionEvent actionEvent) throws Exception {
        try {
            int partIndex = Inventory.getAllParts().indexOf(selectedPart);
            String partName = name.getText();
            int partID = Integer.parseInt(id.getText());
            int partInventory = Integer.parseInt(stock.getText());
            double partPrice = Double.parseDouble(price.getText());
            int partMax = Integer.parseInt(max.getText());
            int partMin = Integer.parseInt(min.getText());
            if(partMax < partMin){
                this.errorAlert("Min Max Error","Min value have to less than max value. Max & Min must be positive");
            } else if (partName.trim().equals("")) this.errorAlert("Empty Space Error","Field must not be empty");
            else if (partPrice < 0) this.errorAlert("Price Error","Price must be positive");
            else {
                Part update = this.setPartType(toggleSource.getSelectedToggle());
                update.setName(partName);
                update.setId(partID);
                update.setMax(partMax);
                update.setMin(partMin);
                update.setPrice(partPrice);
                update.setStock(partInventory);
                if(this.toggleSource.getSelectedToggle().equals(inhouse)){
                    int machineID = Integer.parseInt(machineId.getText());
                    update = (InHouse) update;
                    ((InHouse) update).setMachineId(machineID);
                    Inventory.allParts.set(partIndex, update);
                } else {
                    String machineID = machineId.getText();
                    update = (Outsourced) update;
                    ((Outsourced) update).setCompanyName(machineID);
                    Inventory.allParts.set(partIndex, update);
                }
                this.jumpToMainScreen(actionEvent);
            }
        } catch(NumberFormatException err){
            this.errorAlert("Number error","Please make sure that you enter a valid number to max, min, stock, machine id");
        }
    }

    /**
     * Based on selected toogle to instatiate a new Part (either inHouse or Outsouce object)
     * @param selected chosen toogle
     * @return either an InHouse or Outsource object
     */
    public Part setPartType(Toggle selected){
        Part update;
        if (selected.equals(inhouse)){
            update= new InHouse();
        } else update = new Outsourced();
        return update;
    }

    /**
     * Change text field to either companyNameId field type value or companyName field type value
     * @param part
     */
    public static void setSelectedPart(Part part) {
        selectedPart = part;
       if (part instanceof  InHouse) {
           InHouse inhousePart = new InHouse();
           inhousePart = (InHouse) part;
           altFieldText = Integer.toString(inhousePart.getMachineId());
        } else {
           Outsourced outsourcedPart = new Outsourced();
           outsourcedPart = (Outsourced) part;
           altFieldText = outsourcedPart.getCompanyName();
        }

    }

    /**
     * Add text to all Part text field to show info
     */
    private void setField() {
        String stockText = selectedPart.getStock() + "";
        String minText = Integer.toString(selectedPart.getMin());
        String maxText = Integer.toString(selectedPart.getMax());
        String idText = selectedPart.getId() + "";
        String priceText = Double.toString(selectedPart.getPrice());

        System.out.println(idText + stockText);


        this.id.setText(idText);
        this.name.setText(selectedPart.getName());
        this.machineId.setText(altFieldText);

        this.stock.setText(stockText);
        this.max.setText(maxText);
        this.min.setText(minText);
        this.price.setText(priceText);

        this.setToggle();
    }

    /**
     * Set text field to be machine id or Company name base on Part instance type. This used for instantiate
     */
    public void setToggle(){
        //System.out.println(selectedPart instanceof InHouse);
       if(selectedPart instanceof InHouse){
            this.machineIdToggle.setText("MachineId");
            this.toggleSource.selectToggle(inhouse);
        } else {
            this.machineIdToggle.setText("Company Name");
            this.toggleSource.selectToggle(outsourced);
        }
    }

    /**
     * Set text field to be machine id or Company name base on Part instance type. This used for modify object on the fly based on Action (user input) on screen
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
