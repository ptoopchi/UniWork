package dicegame;

import java.util.ArrayList;

public class player {

    //keeps track of the overall score of the user
    private int overallTotalScore;

    //method for checking the score that the user should receive
    public void setOverallTotalScore(ArrayList<Integer> scores) {

        if (scores.get(0) == scores.get(1) && scores.get(0) == scores.get(2)) {
            overallTotalScore = 18;
        } else if (scores.get(0) == scores.get(1)) {
            overallTotalScore = scores.get(0) + scores.get(1);
        } else if (scores.get(1) == scores.get(2)) {
            overallTotalScore = scores.get(1) + scores.get(2);
        } else if (scores.get(0) == scores.get(2)) {
            overallTotalScore = scores.get(0) + scores.get(2);
        } else {
            overallTotalScore = 1;
        }
        
    }

    //returns the score the user is at
    public int getOverallTotalScore() {
    	return overallTotalScore;
    }

}
