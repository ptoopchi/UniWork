package dicegame;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class gameUIController implements Initializable{


    //the fxml items
    @FXML
    private Label timer;

    @FXML
    public ImageView theBackButton;

    @FXML
    public Label player1, player2;

    @FXML
    private ImageView dice1;

    @FXML
    private ImageView dice2;
    
    @FXML
    private ImageView dice3;

    @FXML
    private Label centerText;
    
    @FXML
    private Label playerOneScore;
    
    @FXML
    private Label playerTwoScore;

    @FXML
    private BorderPane gamUIPane;
    
    @FXML
    private Button theRollButton;

    //the variables
    private int endScorep1, endScorep2;

    private String p1, p2;

    private double valueOfSlider;
     
    private int mainScore1,mainScore2;

    private int playerTurnCounter= 0;

    player person1 = new player();
    
    player person2 = new player();

    public Stage stageHere;
    
    public boolean clicked = false;


    //static sound for the music to be called
    public static String musicFile1 = "roll_sound.mp3";
    static Media sound1 = new Media(new File(musicFile1).toURI().toString());
    static MediaPlayer mediaPlayer1 = new MediaPlayer(sound1);
    
    Image defaultDice = new Image("/images/question.png");
    
/////////////////////////////////////////////////////////////////////////////////////////

    //starts up the images before the game starts
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dice1.setImage(defaultDice);
		dice2.setImage(defaultDice);
		dice3.setImage(defaultDice);
		theRollButton.setStyle("-fx-font-size: 36");
	}

	//sets up the user names from the playerDetails page
    public void setDisplayText(String name1,String name2,double targetPoints){
        player1.setText(name1);
        player2.setText(name2);
        p1 = name1;
        p2 = name2;
        valueOfSlider =  targetPoints;
        timeCounter.start();
    	centerText.setText(p1+"'s Turn");
    }

    //the timer for the game
    AnimationTimer timeCounter = new AnimationTimer() {
        private long currentTime;
        private long countedTime = 0;
        private long frac = 0;

        @Override
        public void start() {
            currentTime = System.currentTimeMillis() - frac;
            super.start();
        }
        
        @Override
        public void handle(long now) {
            long time = System.currentTimeMillis();
            if (currentTime + 1000 <= time) {
                long divided_time = (time - currentTime) / 1000;
                countedTime += divided_time;
                currentTime += 1000 * divided_time;
                timer.setText("Elapsed Time: "+Long.toString(countedTime));
            }
        }
    };

    //the handle request and the methods
    @FXML
    void handleTheRoll(ActionEvent event) throws Exception{

    	//Reset dice faces 
    	dice1.setImage(defaultDice);
		dice2.setImage(defaultDice);
		dice3.setImage(defaultDice);
		
		//enables button 
    	theRollButton.setDisable(true);
    	
    	//disables button after player roll
    	final KeyFrame buttonDelay = new KeyFrame(Duration.seconds(2), e -> theRollButton.setDisable(false));
        final Timeline btn_delay_time = new Timeline(buttonDelay);
        Platform.runLater(btn_delay_time::play);
                  
        if(!clicked){
            mediaPlayer1.play();
            clicked = true;
        }

        if (clicked){
            mediaPlayer1.stop();
            clicked = false;
        }
        mediaPlayer1.play();
            
        //rotation animation for dice 1
        RotateTransition rotateDice1 = new RotateTransition(Duration.millis(700), dice1);
        rotateDice1.setFromAngle(0);
        rotateDice1.setToAngle(360);
        rotateDice1.setCycleCount(3);
        rotateDice1.setAutoReverse(true);

        //rotation animation for dice2
        RotateTransition rotateDice2 = new RotateTransition(Duration.millis(700), dice2);
        rotateDice2.setFromAngle(0);
        rotateDice2.setToAngle(360);
        rotateDice2.setCycleCount(3);
        rotateDice2.setAutoReverse(true);
        
        //rotation animation for dice3
        RotateTransition rotateDice3 = new RotateTransition(Duration.millis(700), dice3);
        rotateDice3.setFromAngle(0);
        rotateDice3.setToAngle(360);
        rotateDice3.setCycleCount(3);
        rotateDice3.setAutoReverse(true);

        //rotating the dice
        rotateDice1.play();
        rotateDice2.play();
        rotateDice3.play();
        
        dice userDice = new dice();
        ArrayList<Integer> List = new ArrayList<>();
        
        //generate random number between 1 and 6
            int rand1 = userDice.rollOne();
            int rand2 = userDice.rollTwo();
            int rand3 = userDice.rollThree();
            
            List.add(rand1);
            List.add(rand2);
            List.add(rand3);
                      
            person1.setOverallTotalScore(List);
            person2.setOverallTotalScore(List);
                
            //Display numbers on dice
            switch(rand1){
                case 1:
                    rotateDice1.setOnFinished(e -> {
                    Image one = new Image("images/1.png");
                    dice1.setImage(one);
                    });
                    break;
                    
                case 2:
                    rotateDice1.setOnFinished(e -> {
                        Image two = new Image("images/2.png");
                        dice1.setImage(two);
                    });
                    break;
                    
                case 3:
                    rotateDice1.setOnFinished(e -> {
                        Image three = new Image("images/3.png");
                        dice1.setImage(three);
                    });
                    break;
                    
                case 4:
                    rotateDice1.setOnFinished(e -> {
                        Image four = new Image("images/4.png");
                        dice1.setImage(four);
                    });
                    break;
                    
                case 5:
                    rotateDice1.setOnFinished(e -> {
                        Image five = new Image("images/5.png");
                        dice1.setImage(five);
                    });
                    break;
                    
                case 6:
                    rotateDice1.setOnFinished(e -> {
                        Image six = new Image("images/6.png");
                        dice1.setImage(six);
                    });
                    break;
                    
            }


            switch(rand2){
                case 1:
                    rotateDice2.setOnFinished(b -> {
                        Image one = new Image("images/1.png");
                        dice2.setImage(one);
                    });
                   break;
                   
                case 2:
                    rotateDice2.setOnFinished(b -> {
                        Image two = new Image("images/2.png");
                        dice2.setImage(two);
                    });
                    break;
                    
                case 3:
                    rotateDice2.setOnFinished(b -> {
                        Image three = new Image("images/3.png");
                        dice2.setImage(three);
                    });
                    break;
                    
                case 4:
                    rotateDice2.setOnFinished(b -> {
                        Image four = new Image("images/4.png");
                        dice2.setImage(four);
                    });
                    break;
                    
                case 5:
                    rotateDice2.setOnFinished(b -> {
                        Image five = new Image("images/5.png");
                        dice2.setImage(five);
                    });
                    break;
                    
                case 6:
                    rotateDice2.setOnFinished(b -> {
                        Image six = new Image("images/6.png");
                        dice2.setImage(six);
                    });
                    break;
                    
            }
            
            switch(rand3){
            case 1:
                rotateDice3.setOnFinished(b -> {
                    Image one = new Image("images/1.png");
                    dice3.setImage(one);
                });
               break;
               
            case 2:
                rotateDice3.setOnFinished(b -> {
                    Image two = new Image("images/2.png");
                    dice3.setImage(two);
                });
                break;
                
            case 3:
                rotateDice3.setOnFinished(b -> {
                    Image three = new Image("images/3.png");
                    dice3.setImage(three);
                });
                break;
                
            case 4:
                rotateDice3.setOnFinished(b -> {
                    Image four = new Image("images/4.png");
                    dice3.setImage(four);
                });
                break;
                
            case 5:
                rotateDice3.setOnFinished(b -> {
                    Image five = new Image("images/5.png");
                    dice3.setImage(five);
                });
                break;
                
            case 6:
                rotateDice3.setOnFinished(b -> {
                    Image six = new Image("images/6.png");
                    dice3.setImage(six);
                });
                break;                
        }
 
            //winning logic for the game
            if (playerTurnCounter == 0) {
            	
                endScorep1 = person1.getOverallTotalScore();
                mainScore1 += endScorep1;
                        
                final KeyFrame scoreDelay = new KeyFrame(Duration.seconds(2), x -> {
                	playerOneScore.setText("" + mainScore1);
                	centerText.setText(p2+"'s Turn");
                    if(mainScore1>mainScore2){
                        playerOneScore.setTextFill(Color.GREEN);
                        playerTwoScore.setTextFill(Color.BLACK);
                        playerOneScore.setStyle("-fx-font-size: 60;");
                        playerTwoScore.setStyle("-fx-font-size: 48;");
                        
                    }
                    else if(mainScore2 > mainScore1){
                        playerTwoScore.setTextFill(Color.GREEN);
                        playerOneScore.setTextFill(Color.BLACK);
                        playerTwoScore.setStyle("-fx-font-size: 60;");
                        playerOneScore.setStyle("-fx-font-size: 48;");
                        
                    } else {
                    	playerTwoScore.setTextFill(Color.BLACK);
                        playerOneScore.setTextFill(Color.BLACK);
                        playerTwoScore.setStyle("-fx-font-size: 48;");
                        playerOneScore.setStyle("-fx-font-size: 48;");        	
                    }
                });     
                final Timeline tl = new Timeline(scoreDelay);
                
                Platform.runLater(tl::play);         
                playerTurnCounter = 1;
                
            } else if (playerTurnCounter == 1){
            	
                endScorep2 = person2.getOverallTotalScore();
                mainScore2 += endScorep2;
                
                final KeyFrame scoreDelay = new KeyFrame(Duration.seconds(2), x -> {
                	playerTwoScore.setText("" + mainScore2);
                	centerText.setText(p1+"'s Turn");
                    if (mainScore1>mainScore2){
                        playerOneScore.setTextFill(Color.GREEN);
                        playerTwoScore.setTextFill(Color.BLACK);
                        playerOneScore.setStyle("-fx-font-size: 60;");
                        playerTwoScore.setStyle("-fx-font-size: 48;");
                        
                    } else if(mainScore2 > mainScore1){
                        playerTwoScore.setTextFill(Color.GREEN);
                        playerOneScore.setTextFill(Color.BLACK);
                        playerTwoScore.setStyle("-fx-font-size: 60;");
                        playerOneScore.setStyle("-fx-font-size: 48;");
                        
                    } else {
                    	playerTwoScore.setTextFill(Color.BLACK);
                        playerOneScore.setTextFill(Color.BLACK);
                        playerTwoScore.setStyle("-fx-font-size: 48;");
                        playerOneScore.setStyle("-fx-font-size: 48;");        	
                    }
                    	
                }); 
                final Timeline tl = new Timeline(scoreDelay);
                
                Platform.runLater(tl::play);
                playerTurnCounter = 0;
            }

            
       
         int convertedSlider = (int)valueOfSlider;
        //what information needs to be sent to the well done page
        if (mainScore1 > convertedSlider){
            
        	final KeyFrame finalDelay = new KeyFrame(Duration.seconds(2), e ->  {
        		
        		final Stage finalScreen;
        		Parent root = null;
        		
        		  wellDoneController sc = new wellDoneController();
                  sc.setVictorName(p1,mainScore1);

                  finalScreen=(Stage) ((Button)(event.getSource())).getScene().getWindow();
                  try {
					root = FXMLLoader.load(getClass().getResource("fxml/wellDonePage.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}

                  Scene scene = new Scene(root);
                  finalScreen.setScene(scene);
                  finalScreen.show();
        		
        	});
        	
            final Timeline delaytime = new Timeline(finalDelay);
            Platform.runLater(delaytime::play);
          

        } else if (mainScore2 > convertedSlider){
        	          
        	final KeyFrame finalDelay = new KeyFrame(Duration.seconds(2), e ->  {
        		
        		final Stage finalScreen;
                Parent root = null;
                
	            wellDoneController sc = new wellDoneController();
	            sc.setVictorName(p2,mainScore2);
	
	            finalScreen=(Stage) ((Button)(event.getSource())).getScene().getWindow();
	            try {
					root = FXMLLoader.load(getClass().getResource("fxml/wellDonePage.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	
	            Scene scene = new Scene(root);
	            finalScreen.setScene(scene);
	            finalScreen.show();    
    	});
	        final Timeline delaytime = new Timeline(finalDelay);
	        Platform.runLater(delaytime::play);

        } else if (mainScore1 > convertedSlider && mainScore2 > convertedSlider){
        		
        	final KeyFrame finalDelay = new KeyFrame(Duration.seconds(2), e ->  {
        	
	        	final Stage finalScreen;
	            Parent root= null;
	            
	            wellDoneController sc = new wellDoneController();
	            sc.setVictorName(p1,mainScore1,p2,mainScore2);
	
	            finalScreen=(Stage) ((Button)(event.getSource())).getScene().getWindow();
	            try {
					root = FXMLLoader.load(getClass().getResource("fxml/wellDonePage.fxml"));
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
	
	            Scene scene = new Scene(root);
	            finalScreen.setScene(scene);
	            finalScreen.show();
        	});
        	
	        final Timeline delaytime = new Timeline(finalDelay);
	        Platform.runLater(delaytime::play);
        } 
        
    }  
    
}
