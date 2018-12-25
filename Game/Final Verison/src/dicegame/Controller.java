package dicegame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller{

    //the variables
    static boolean answer;

    //the fxml icons
    @FXML
    private ImageView pressMute;

    @FXML
    private Button startScreenButton;

    @FXML
    private Button quitAppButton;

    @FXML
    private Button howToPlay;

    @FXML
    private Button theLeaderboard;

    @FXML
    private ImageView pressPlay;


    //the handle requests and the methods

    //method for quiting the app when the user presses quit or the x button
    @FXML
    public boolean theProcess() {

        Stage theStage = new Stage();
        theStage.initModality(Modality.APPLICATION_MODAL);

        theStage.setTitle("Close Window");
        theStage.setMinWidth(250);
        theStage.getIcons().add(new Image("images/dice_red.png"));
        theStage.setResizable(false);
        //the 2 buttons + label

        Label theLabel = new Label("Would you like to exit?");
        theLabel.setStyle("-fx-font-size: 18;");
        theLabel.getStylesheets().add("css/basestyle.css");

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
        theScene.getStylesheets().add("css/basestyle.css");
        theStage.setScene(theScene);
        theStage.showAndWait();

        return answer;
    }

    
    @FXML
    public void handleTheQuit(){
        boolean theAnswer =  theProcess();
        if(theAnswer) Platform.exit();
    }

    
    //lets the user mute the music being played
    @FXML
    void handleTheMute() {
        if(!Main.clicked){
            Main.playSong();
            Main.setTrue();
            
        } if (Main.clicked){
            Main.muteSong();
            Main.setFalse();
        }
    }

    
    //goes to the player for the player details
    @FXML
    public void handleTheStart() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/playerDetails.fxml"));
        Stage theScene = (Stage) startScreenButton.getScene().getWindow();
        theScene.setScene(new Scene(root));
    }

    
    //lets the user see instructions on how to play the game
    @FXML
    void handleTheInstructions() throws Exception{
        Parent theRoot = FXMLLoader.load(getClass().getResource("fxml/howToPlay.fxml"));
        Stage scene = (Stage) howToPlay.getScene().getWindow();
        scene.setScene(new Scene(theRoot));
    }

    
    //shows the top 6 scores of all time
    @FXML
    void handleTheLeaderboard() throws Exception{
        Parent theRoot = FXMLLoader.load(getClass().getResource("fxml/leaderboardPage.fxml"));
        Stage scene = (Stage) theLeaderboard.getScene().getWindow();
        scene.setScene(new Scene(theRoot));
    }

}
