package dicegame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.*;


public class leaderboardController implements Initializable {

    //the fxml requests
    @FXML
    private Label theTextArea;

    @FXML
    private ImageView theBackButton;


    //lets the user mute the music
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

    //sends the user back to the main menu page
    @FXML
    void handleTheBackButton() throws Exception {
        Parent theRoot = FXMLLoader.load(getClass().getResource("fxml/startScreen.fxml"));
        Stage scene = (Stage) theBackButton.getScene().getWindow();
        scene.setScene(new Scene(theRoot));
    }

    //does the start ups before the page is fully loaded
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            Scanner theFile = new Scanner(new File("people.txt"));
            HashMap<String, Integer> theMap = new HashMap<String, Integer>();
            int theLimit = 0;

            while (theFile.hasNextLine()) {
                String[] each_line = theFile.nextLine().split(":");
                theMap.put(each_line[0], Integer.parseInt(each_line[1]));}

                    //prints the hashmap above in order into a new hashmap
                    Object[] a = theMap.entrySet().toArray();
                    Arrays.sort(a, new Comparator() {
                        public int compare(Object o1, Object o2) {
                            return ((Map.Entry<String, Integer>) o2).getValue()
                                       .compareTo(((Map.Entry<String, Integer>) o1).getValue());
                        }
                    });

                    //prints out the top 6
                    int count = 0;
                    for (Object e : a) {
                    	count++;
                        theTextArea.setText(theTextArea.getText() +"\n"+count +".  "+((Map.Entry<String, Integer>) e).getKey() + ":  "
                                + ((Map.Entry<String, Integer>) e).getValue()+"\n");
                        theLimit++;
                        if (theLimit >= 6) break;
                    }
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }
}
