package model;

import javafx.application.Application;
import javafx.stage.Stage;

public class InHouse extends Part {

    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id,name,price,stock, min, max);
        this.machineId = machineId;
    }
    public InHouse(){
        super();
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    @Override
    public String toString() {
        return "InHouse{" +
                "machineId=" + this.machineId + " " + super.getName() +
                '}';
    }
}
