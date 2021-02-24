package tjobah.a5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsController {

    @FXML
    Label labBTCValue;
    @FXML
    Label labETHValue;

    @FXML
    HBox btcHBox;
    @FXML
    HBox ethHBox;

    public void initialize(){
        labBTCValue.setText("$53,000.00");
        labETHValue.setText("$1,800.00");
    }

    public DetailsController(){
        System.out.println("constructor");
    }

    public void onDetailButtonClicked(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() == btcHBox){
            System.out.println("Change to BTC Scene");
            Parent root = FXMLLoader.load(getClass().getResource("BTC.fxml"));
            Stage primaryStage = (Stage) btcHBox.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 475));
        }
        if(mouseEvent.getSource() == ethHBox){
            System.out.println("change to eth scene");
            Parent root = FXMLLoader.load(getClass().getResource("ETH.fxml"));
            Stage primaryStage = (Stage) ethHBox.getScene().getWindow();
            primaryStage.setScene(new Scene(root, 700, 475));
        }
    }
}
