package sample;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    static boolean answer;

    @FXML
    private Button startScreenButton;

    @FXML
    private Button quitAppButton;


    @FXML
    public boolean theProcess() {

        Stage theStage = new Stage();
        theStage.initModality(Modality.APPLICATION_MODAL);
        theStage.setTitle("Close Window");
        theStage.setMinWidth(250);

        //the 2 buttons + label

        Label theLabel = new Label("Would you like to exit?");

        Button b1 = new Button("Yes");
        Button b2 = new Button("No");


        //if they press button 1
        b1.setOnAction(e -> {
            answer = true;
            theStage.close();
            //Platform.exit();
        });
        b2.setOnAction(e -> {
            answer = false;
            theStage.close();
        });


        VBox theBox = new VBox(15);
        theBox.setAlignment(Pos.CENTER);
        theBox.getChildren().addAll(theLabel, b1, b2);
        Scene theScene = new Scene(theBox, 250,300);
        theStage.setScene(theScene);
        theStage.showAndWait();

        return answer;
    }
    @FXML
    public void handleTheQuit(){
        boolean theAnswer =  theProcess();
        if(theAnswer) Platform.exit();

    }

    @FXML
    public void handleTheStart() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));

        Stage theScene = (Stage) startScreenButton.getScene().getWindow();
        theScene.setScene(new Scene(root));
    }


}
