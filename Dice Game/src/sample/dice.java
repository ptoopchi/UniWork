package sample;

import java.util.ArrayList;
import java.util.Random;

public class dice {

    private int randomNum;
    private Random rand;

    private int minValue;
    private int maxValue;



    //empty construtor for calling into the main
    public dice() {
        this.minValue = 1;
        this.maxValue = 6;
    }

    public int getMinValue() {
        return minValue;
    }
    public int getMaxValue() {
        return maxValue;
    }


    //will roll the dice 1 time
    public int makeRandomNumber(){

        rand = new Random();

        randomNum = rand.nextInt(this.maxValue) + 1;

        return randomNum;
    }
    public ArrayList<Integer> rollDice() {


        ArrayList<Integer> playerDiceValues = new ArrayList<Integer>();

        for (int i = 0; i < 3; i++) {
            int temp = makeRandomNumber();
            playerDiceValues.add(temp);
        }

        return playerDiceValues;
    }



}
