package tjobah.a5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ETHController {

    @FXML
    ImageView btnBackArrow;

    public void onBackArrowClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
        Stage primaryStage = (Stage) btnBackArrow.getScene().getWindow();
        primaryStage.setScene(new Scene(root, 700, 475));
    }
}
