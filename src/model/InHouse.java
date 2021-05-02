package model;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Vy Le
 * InHouse class for Part abstract class
 */
public class InHouse extends Part {

    private int machineId;

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id,name,price,stock, min, max);
        this.machineId = machineId;
    }
    public InHouse(){
        super();
    }

    /**
     *
     * @return machine id
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     *
     * @param machineId
     */
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
