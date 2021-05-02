import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Part;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

/**
* JavaDoc folder locate next to the scr and out folder

 * RUNTIME ERROR: used to have one because of a typo mistake in MainController and MainScreen FXML

 * FUTURE ENHANCEMENT: serialize product and part observable to save and load those object so this can become a real application
 */



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainScreen.fxml"));
        primaryStage.setTitle("Main Screen");
        primaryStage.setScene(new Scene(root, 1137, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Part part1 = new InHouse(1,"sdsd",2.99,3,1,5,5);
        Part part2 = new InHouse(2,"shh",2.99,3,1,5,5);

        Inventory.addPart(part1);
        Inventory.addPart(part2);


        Product product1 = new Product(1,"sdsd",2.99,3,1,5);
        Product product2 = new Product(2,"shh",2.99,3,1,5);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        launch(args);
    }
}
