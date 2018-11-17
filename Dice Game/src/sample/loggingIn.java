package sample;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class loggingIn{

    public loggingIn() {
    }


    // need to make it so if file doesnt exist make one
    //do try and catch for any exceptions that might be thrown.
    //make a method to check if a user already exists
    //so if it does then they cant add it again



    //log in fix when user types something that doesnt match at all



    public void create_account(String username, String password, FileWriter theFile) throws IOException {

        //setting up the file and the stream
        BufferedWriter outputStream = new BufferedWriter(theFile);
        PrintWriter outputToFile = new PrintWriter(outputStream);

        //before saving the details check to see if the user name is already taken or not
        Scanner peopleFile = new Scanner(new File("people.txt"));

        HashMap<String, String> theMap_create_account = new HashMap<String, String>();

        while (peopleFile.hasNextLine()) {
            String[] each_line = peopleFile.nextLine().split(":");
            theMap_create_account.put(each_line[0], each_line[1]);
        }
        if(!theMap_create_account.containsKey(username)){
            String split = ":";
            outputToFile.write(username+split);
            outputToFile.write(password+"\n");

            System.out.println("Your details have been saved!");

            outputToFile.close();

        }else{
            System.out.println("That username is already taken!");
        }

        outputToFile.close();

    }

    //able to login into an account
    public void logIn_account(String username, String password) throws IOException {

        Scanner theFile = new Scanner(new File("people.txt"));

        HashMap<String, String> theMap = new HashMap<String, String>();

        while (theFile.hasNextLine()) {
            String[] each_line = theFile.nextLine().split(":");
            theMap.put(each_line[0], each_line[1]);
        }
        if (theMap.containsKey(username)){

            if (password.equals(theMap.get(username))){
                System.out.println("User Found!");
            }
            else {
                System.out.println("Not Found!");
            }
        }
    }
}
