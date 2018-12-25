package dicegame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class defaultGameController {

    //the fxml items for the page
    @FXML
    private ImageView theBackButton;

    @FXML
    public Button startButton;

    @FXML
    private TextField player1;

    @FXML
    private TextField player2;

    @FXML
    public String player1_text,player2_text;

    @FXML
    private Slider theSlider;

    @FXML
    private Label overallScoreLabel;
    
    @FXML
    private Label invalidInput;

    @FXML
    private ImageView pressMute;


    //the handle requests and the methods

    //launches the player into the main GUI of game
    @FXML
    void handleTheStartGame() throws Exception{

    	if (isValidUsername()) {

	        Main.stopSong();
	        FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/gameUI.fxml"));
	        Parent root= loader.load();
	        gameUIController sc =loader.getController();
	        sc.setDisplayText(player1.getText(),player2.getText(),theSlider.getValue());
	        Stage scene = (Stage) startButton.getScene().getWindow();
	        scene.setScene(new Scene(root));
    }
    	
  }

  //sends the user back to the previous page
    @FXML
    void handleTheBackButton() throws Exception{
        Parent theRoot = FXMLLoader.load(getClass().getResource("fxml/startScreen.fxml"));
        Stage scene = (Stage) theBackButton.getScene().getWindow();
        scene.setScene(new Scene(theRoot));
    }

    //lets the user mute the music
    @FXML
    void handleTheMute(){
        if(!Main.clicked){
            Main.playSong();
            Main.setTrue();
            
        } if (Main.clicked){
            Main.muteSong();
            Main.setFalse();
        }
    }

    //changes the value of the slider so that it stays updated
    @FXML
    void sliderChanged() {

    	AtomicInteger newValue = new AtomicInteger(50);

        theSlider.valueProperty().addListener(
                (var, theLastValue, theUpdatedValue) -> {
                    newValue.set(theUpdatedValue.intValue());
                    overallScoreLabel.setText(""+newValue);
                });
    }

    //checks to see if the name that the user typed in is valid
    private boolean isValidUsername() {
	
    	if (player1.getText().equals("") && player2.getText().equals("")) {
    		invalidInput.setText("Please enter both players' names");
    		return false;
    	} else if (player1.getText().equals("") && !player2.getText().equals("")) {
    		invalidInput.setText("Please enter a name for player 1");
    		return false;
    	} else if (!player1.getText().equals("") && player2.getText().equals("")) {
    		invalidInput.setText("Please enter a name for player 2");
    		return false;
    	} else if (!player1.getText().equals("") && !player2.getText().equals("")) {
    		
    		if (player1.getText().length() > 10 && player2.getText().length() > 10) {
    			invalidInput.setText("Please make sure the names are no more than 10 characters");
    			return false;
    		} else if (player1.getText().length() <= 10 && player2.getText().length() > 10) {
    			invalidInput.setText("Please make sure player 2's name is no more than 10 characters");
    			return false;
    		} else if (player1.getText().length() > 10 && player2.getText().length() <= 10) {
    			invalidInput.setText("Please make sure player 1's name is no more than 10 characters");
    			return false;
    		} else if (player1.getText().equals(player2.getText())) {
    			invalidInput.setText("Please make sure the names are not the same");
    			return false;
    		} else if (player1.getText().length() <= 10 && player2.getText().length() <= 10)
    			return true;	
    	}
    	
    	return false;
    }

}

