package dicegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application{

    //the variables
    Stage window;
    Parent root;

    //the static methods to be used throughtout the program for the main menu music
    public static boolean clicked = true;

    public static String musicFile = "music2.mp3";
    static Media sound = new Media(new File(musicFile).toURI().toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(sound);

    public static void stopSong(){
        mediaPlayer.stop();
    }
    public static void playSong(){
        mediaPlayer.play();
    }
    public static void muteSong(){
        mediaPlayer.pause();
    }
    
    public static boolean setFalse() {
    	clicked = false;
    	return clicked;
    }
    
    public static boolean setTrue() {
    	clicked = true;
    	return clicked;
    }


    //start method for the javaFX
    @Override
    public void start(Stage primaryStage) throws Exception{

    	root = FXMLLoader.load(getClass().getResource("fxml/startScreen.fxml"));
    	
        window = primaryStage;
        window.getIcons().add(new Image("images/mainLogoDice.png"));
        window.setTitle("Dice Drop");
        window.setResizable(false);
        
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        window.setOnCloseRequest(e -> {
            Controller run = new Controller();
            e.consume();
            run.handleTheQuit();
        });
        
        window.setScene(new Scene(root, 700, 600));
        window.show();
    }

    //launches the app
    public static void main(String[] args) {
        launch(args);
    }

}
