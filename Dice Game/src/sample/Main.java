package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    //class put in here as methods
    Stage window;
    static boolean answer;
    Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //setting up the boxes
        window = primaryStage;
        root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
        window.setTitle("Dice Drop");


        window.setOnCloseRequest(e -> {
            Controller run = new Controller();
            e.consume();
            run.handleTheQuit();
        });


        window.setScene(new Scene(root, 450, 400));
        window.show();
    }

    public static void main(String[] args){
        launch(args);


        loggingIn usingLog = new loggingIn();
        Main theMain = new Main();
        dice theDice = new dice();

        player person1 = new player(1);//change it so they can pick their name
        player person2 = new player(2);
//*****************************************************************************************************************
//creating an account
//*****************************************************************************************************************
//        Scanner forLogin = new Scanner(System.in);
//        System.out.println("---------[Creating an Account]----------");
//
//        System.out.print("Enter username: ");
//        String username = forLogin.next();
//
//        System.out.print("Enter password: ");
//        String password = forLogin.next();
//
//        System.out.println("Please Confirm Your Password: ");
//        String password2 = forLogin.next();
//
//        String sendPassword;
//        if(password.equals(password2)){
//           sendPassword = password;
//            try {
//                FileWriter newFile = new FileWriter("people.txt",true); //for creating an account
//                usingLog.create_account(username, sendPassword, newFile);
//            }catch (IOException e){
//                e.getStackTrace();
//            }
//        }
//        else{
//            System.out.println("Try again the passwords didnt match!");
//        }
//********************************************************************************************************
//loging into an account
//********************************************************************************************************
//        Scanner forLogin = new Scanner(System.in);
//        System.out.println("---------[Signing In]----------");
//
//        System.out.print("Enter username: ");
//        String username = forLogin.nextLine();
//
//        System.out.print("Enter password: ");
//        String password = forLogin.nextLine();
//
//            try {
//                usingLog.logIn_account(username, password);
//            }catch (IOException e){
//                e.getStackTrace();
//            }
//********************************************************************************************************
//        Scanner theScanner = new Scanner(System.in);
//
//        System.out.println("Please enter if you want 3 | 5 | 9 full rounds");
//        int userRounds = theScanner.nextInt();
//        //counts the numbers of games within a round and also stops when the limit is reached
//        int counter = 0;
//        boolean run = true;
//
//        while (run) {
//            //for each round there is 2 sets of games causing the points for both to be added together
//            for (int i = 0; i < 2; i++) {
//                System.out.println("the result for person 1 is " + person1.currentRoundScore());
//                System.out.println("-----------------------------------------------------");
//                System.out.println("the result for person 2 is " + person2.currentRoundScore());
//                System.out.println("-----------------------------------------------------");
//            }
//            theMain.theTotalScore(person1, person2);
//            counter++;
//            if (counter == userRounds){
//                run = false;
//            }
//        }
    }

    //gets the total amount of rounds that each of the players have won
    public void theTotalScore(player player1, player player2){
        if(player1.currentRound > player2.currentRound){
            player1.overallTotalScore++;
        }
        else if(player2.currentRound > player1.currentRound){
            player2.overallTotalScore++;
        }
        else{
            player1.overallTotalScore++;
            player2.overallTotalScore++;
        }
        System.out.println("Player 1 current score: "+player1.overallTotalScore);
        System.out.println("Player 2 current score: "+player2.overallTotalScore);
    }
}
