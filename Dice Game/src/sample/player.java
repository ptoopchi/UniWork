package sample;

import java.util.ArrayList;

public class player {

    private int userID;
    public int currentRound;
    public int overallTotalScore;
    dice diceForPlayerClass = new dice();

    //the c,s,g
    public player(int userID) {
        this.userID = userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setCurrentRound(int playerScore) {
        this.currentRound = playerScore;
    }
    public int getOverallTotalScore() {
        return overallTotalScore;
    }
    public void setOverallTotalScore(int overallTotalScore) {
        this.overallTotalScore = overallTotalScore;
    }
    public int getUserID() {
        return userID;
    }
    public int getCurrentRound() {
        return currentRound;
    }



    public int currentRoundScore(){

        ArrayList<Integer> theList = diceForPlayerClass.rollDice();

        System.out.println(theList);

        int num1 = theList.get(0);
        int num2 = theList.get(1);
        int num3 = theList.get(2);
        if(num1 == num2 && num2 == num3){

            this.currentRound = 18;
        }
        else if(num1 == num2){
            this.currentRound = currentRound + (num1 + num2);
        }
        else if(num1 == num3){
            this.currentRound = currentRound + (num1 + num3);
        }
        else if(num2 ==  num3){
            this.currentRound = currentRound + (num2 + num3);
        }
        else{
            this.currentRound = currentRound + 1;
        }
        return currentRound;
    }


}
