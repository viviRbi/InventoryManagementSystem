package model;

import javafx.application.Application;
import javafx.stage.Stage;

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id,name,price,stock, min, max);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

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
