package dicegame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class wellDoneController implements Initializable {

    //the fxml requests
    @FXML
    private Button theBackButton;

    @FXML
    private Label playerWinHere;

    @FXML
    private Label playerScore;

    @FXML
    private RadioButton radioYes,radioNo;

    @FXML
    private ToggleGroup group;

    //the variables
    boolean press = true;

    private static String winner, drawPlayer1,drawPlayer2;

    public static int winnerScore,drawScore1,drawScore2;

    leaderBoardHandling leaderBoard= new leaderBoardHandling();


    public static String musicFile2 = "music1.mp3";
    static Media sound2 = new Media(new File(musicFile2).toURI().toString());
    static MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);


    //the fxml handling and methods

    //lets the user to mute the music
    @FXML
    void handleTheMute() {
        if(!press){
           mediaPlayer2.play();
            press = true;
        }

        if (press){
           mediaPlayer2.pause();
            press = false;
        }
    }

    //sends the user back to the main menu
    @FXML
    void handleTheBackButton() throws Exception{

        mediaPlayer2.stop();
        Main.playSong();
        Parent theRoot = FXMLLoader.load(getClass().getResource("fxml/startScreen.fxml"));

        if(radioYes.isSelected()) {
            String winnerScoreConverted = Integer.toString(winnerScore);

            try {
                FileWriter newFile = new FileWriter("people.txt", true); //for adding to leaderboard
                leaderBoard.create_person(winner, winnerScoreConverted, newFile);
            } catch (IOException e) {
                e.getStackTrace();
            }
        }


        Stage scene = (Stage) theBackButton.getScene().getWindow();
        scene.setScene(new Scene(theRoot));
    }

    //get the persons name that won or both of their names if they draw
    public void setVictorName(String playerName, int playerScore) {
        winner = playerName;
        winnerScore = playerScore;
    }

    public void setVictorName(String playerName1, int playerScore1,String playerName2, int playerScore2){
        drawPlayer1 = playerName1;
        drawPlayer2 = playerName2;
        drawScore1 = playerScore1;
        drawScore2 = playerScore2;

    }

    //starts up before the page is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        radioNo.setToggleGroup(group);
        radioYes.setToggleGroup(group);
        radioYes.setSelected(true);

        if(winner!=null){
            playerWinHere.setText(winner);
            playerScore.setText("Your Score was "+ winnerScore);
            
        } else if(drawPlayer1 != null && drawPlayer2 != null){
            playerWinHere.setText(drawPlayer1+"  "+drawPlayer2);
            playerScore.setText("It's a Draw!");
        }
        mediaPlayer2.play();
    }
}
