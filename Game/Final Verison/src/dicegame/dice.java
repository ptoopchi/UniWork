package dicegame;

import java.util.ArrayList;
import java.util.Random;

public class dice {

    //the variables
    private int randomNum;
    private Random rand;

    private int minValue;
    private int maxValue;

    private ArrayList<Integer> List = new ArrayList<Integer>();
    
    //empty construtor for calling into the main
    public dice() {
        this.minValue = 1;
        this.maxValue = 6;
    }

    //gets the min and max value
    public int getMinValue() {
        return minValue;
    }
    public int getMaxValue() {
        return maxValue;
    }


    //will roll the dice and give an random value
    public int makeRandomNumber(){
        rand = new Random();
        randomNum = rand.nextInt(this.maxValue) + 1;
        return randomNum;
    }
    
    //the 3 dices for the game for each player
    public int rollOne() {
		int r = makeRandomNumber();
    	List.add(r);
		return r;
    }

    public int rollTwo() {
    	int r = makeRandomNumber();
    	List.add(r);
		return r;
    }
    
    public int rollThree() {
    	int r = makeRandomNumber();
    	List.add(r);
		return r;
    }

}
