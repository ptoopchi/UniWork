package dicegame;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class howToPlayController implements Initializable{

	  //the fxml items
	  @FXML
	  private Pagination pagination;

	  @FXML
	  private ImageView theBackButton;

	  @FXML
	  private ImageView pressMute;

	  //the variables
	  ArrayList<String> instructions;


	  //starts up all the images before the game is played
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		       
		instructions = new ArrayList<>();
		
		instructions.add("tutorial steps//first.png");
		instructions.add("tutorial steps//second.png");
		instructions.add("tutorial steps//third.png");
		instructions.add("tutorial steps//fourth.png");
		instructions.add("tutorial steps//gameRules.png");
		
		pagination.setPageFactory(iv -> new ImageView(instructions.get(iv)));
		
	}


	//sends user back to the main menu
	@FXML
	void handleTheBackButton() throws Exception{
		Parent theRoot = FXMLLoader.load(getClass().getResource("fxml/startScreen.fxml"));
		Stage scene = (Stage) theBackButton.getScene().getWindow();
		scene.setScene(new Scene(theRoot));
	}

	//lets the user to mute the music
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

}
