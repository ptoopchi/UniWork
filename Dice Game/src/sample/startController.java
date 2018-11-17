package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class startController{

    @FXML
    private ImageView backButton;

    @FXML
    private Button createAccount;

    @FXML
    private Button logIn;

    @FXML
    public void handleTheCreate() {

    }

    @FXML
    public void handleTheLogIn() {

    }

    @FXML
    public void handleTheBackButton() throws Exception{
        Parent theRoot = FXMLLoader.load(getClass().getResource("startScreen.fxml"));

        Stage s = (Stage) backButton.getScene().getWindow();
        s.setScene(new Scene(theRoot));
    }

}
