package dicegame;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class leaderBoardHandling {

    //empty constructor
    public leaderBoardHandling() {
    }

    //method for adding a new person to the file
    public void create_person(String username, String score, FileWriter theFile) throws IOException {

        //setting up the file and the stream
        BufferedWriter outputStream = new BufferedWriter(theFile);
        PrintWriter outputToFile = new PrintWriter(outputStream);

        //loads the file and puts data into a hashmap
        Scanner peopleFile = new Scanner(new File("people.txt"));
        HashMap<String, Integer>  theMap_create_person = new HashMap<String, Integer>();

        while (peopleFile.hasNextLine()) {
            String[] each_line = peopleFile.nextLine().split(":");
            theMap_create_person.put(each_line[0], Integer.parseInt(each_line[1]));
        }
        
        //adds to the txt file
        if(!theMap_create_person.containsKey(username)){
            String split = ":";
            outputToFile.write(username+split);
            outputToFile.write(score+"\n");
            outputToFile.close();
        }

    }
}
