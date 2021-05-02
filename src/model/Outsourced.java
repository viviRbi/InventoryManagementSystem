package model;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * @author Vy Le
 * Outsource class from Part abstract class
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id,name,price,stock, min, max);
        this.companyName = companyName;
    }

    public Outsourced(){
        super();
    }

    /**
     *
     * @return company name
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Outsourced{" +
                "companyName=" + companyName +
                '}';
    }
}
