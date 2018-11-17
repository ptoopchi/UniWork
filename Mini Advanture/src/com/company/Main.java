package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to The Quest 2!\n");

        System.out.print("To Start First Please Pick A Character.\n" +
                //the mad jack
                "\nA) Mad Jack\nAttcking Damage: 6 \nHealth: 100 \nSneaking Skill: ★★☆☆☆\n" +
                //the Brainage
                "\nB) Brainage \nAttacking Damage: 4\nHealth: 100\nSneaking Skill: ★★★★☆\n" +
                //the tank
                "\nC) Tank \nAttacking Damage: 5\nHealth: 150\nSneaking Skill: ★☆☆☆☆\n" +
                "\nPlease Choose Your Character Here: ");


        String chosen_player = scanner.nextLine().toLowerCase();


        //start the main player process
        MainCharacters mainChar = new MainCharacters(null,0,0,0);

        if (chosen_player.equals("a")) {
            mainChar.setCharacter_name("Mad Jack");
            mainChar.setAttacking_damage(6);
            mainChar.setHealth(100);
            mainChar.setSneaking_skill(2);
            mainChar.messagePrint();

        } else if (chosen_player.equals("b")) {
            mainChar.setCharacter_name("Brainage");
            mainChar.setAttacking_damage(4);
            mainChar.setHealth(100);
            mainChar.setSneaking_skill(4);
            mainChar.messagePrint();

        } else if (chosen_player.equals("c")) {
            mainChar.setCharacter_name("Tank");
            mainChar.setAttacking_damage(5);
            mainChar.setHealth(150);
            mainChar.setSneaking_skill(1);
            mainChar.messagePrint();

        } else {
            System.out.print("This Is Not A Valid Character Please Try Again!\n" +
                    "\n Please Choose Your Character Here: ");
        }

        System.out.println("Would you like to read background story before the game starts...\n" +
                "Please choose: \n" +
                "a) Yes\n" +
                "b) No");
        String readmore = scanner.next().toLowerCase();

        if(readmore.equals("a")){
            System.out.println("\nYour name is Sam Neilson. The year is 2136. You have time travelled from the year 2014.\n" +
                    "Time traveling is no joke and the reasons behind your choice will impact you\n" +
                    "But at least you know this time you will find out more about your fathers secrets...\n" +
                    "" +
                    "I hate this time period there are strange creatures out there ready to get me i must be prepared!\n" +
                    "" +
                    "Wait i think i can see something in the distance\n");
        }


        //start the monster choosing process
        Monsters firstFight = new Monsters("",0,0,0,0);


        int numberOfEnemies = randomNumberInRange(1,3);

        for (int i = 0; i < numberOfEnemies;i++){

            int enemySelector = randomNumberInRange(1,4);
            //create the monsters and chose a random one by after each for loop has passed.

            if(enemySelector == 1){
                firstFight.setCharacter_name("Skelly");
                firstFight.setAttacking_damage(1);
                firstFight.setHealth(10);
                firstFight.setSneaking_skill(1);
                firstFight.setMonster_ID(1);
                System.out.println("You have been approached by a Skelly with: \n Damage: 1 \n Health: 10\n");
            }else if (enemySelector == 2){
                firstFight.setCharacter_name("Fireball");
                firstFight.setAttacking_damage(5);
                firstFight.setHealth(4);
                firstFight.setMonster_ID(2);
                firstFight.setSneaking_skill(3);
                System.out.println("You have been approached by a Fireball with: \n Damage: 10 \n Health: 4\n");
            }else if (enemySelector == 3){
                firstFight.setCharacter_name("Carnage");
                firstFight.setAttacking_damage(3);
                firstFight.setHealth(10);
                firstFight.setMonster_ID(3);
                firstFight.setSneaking_skill(4);
                System.out.println("You have been approached by a Carnage with: \n Damage: 3 \n Health: 10\n");
            }else if (enemySelector == 4){
                firstFight.setCharacter_name("Ground Buster");
                firstFight.setAttacking_damage(3);
                firstFight.setHealth(25);
                firstFight.setMonster_ID(4);
                firstFight.setSneaking_skill(2);
                System.out.println("You have been approached by a Ground Buster with: \n Damage: 3 \n Health: 25\n");
            }

            System.out.println("Oh SHIT! There is a "+firstFight.getCharacter_name()+" you need to attack it NOW!\n" +
                    "a) Attack Monster\nb) Sneak Away\n");


            //sneak power is unknown they have to guess luck then if correct
            // get away if not then they stay and get damaged like normal




            //variables needed for battle
            int monster_health = firstFight.getHealth();                 //damage to monster
            int your_damage = mainChar.getAttacking_damage();           //damage player can do

            int your_health = mainChar.getHealth();                     //players health
            int monster_damage = firstFight.getAttacking_damage();      //monsters damage
            

            int monster_sneak = firstFight.getSneaking_skill();         //monster sneak skill
            int your_sneak = mainChar.getSneaking_skill();              //players sneak skill

            boolean battle_mode = true;

            while(battle_mode) {

                System.out.print("Please Choose Here: ");
                String attack_monsters = scanner.next().toLowerCase();


                    if (attack_monsters.equals("a")) {

                        monster_health -= your_damage;
                        System.out.println("Monster's remaining health: "+monster_health);

                        your_health -= monster_damage;
                        System.out.println("\n Your remaining health: "+your_health);

                    }
                    if(monster_health <= 0){
                        System.out.println("\n"+firstFight.getCharacter_name()+" has been killed!\n");
                        battle_mode = false;
                    }


                    if (attack_monsters.equals("b")) {

                        if (your_sneak > monster_sneak) {
                            System.out.println("You got away from monster!");
                            battle_mode = false;
                        } else {
                            System.out.println("You can't sneak away!");
                            your_health -= monster_damage;
                            System.out.println("You have been damaged your remaining health: "+your_health+"\n");
                        }



                    }
                }
            }

        System.out.println("Once all of the monsters have been killed you see a room in the distance that looks abandoned.\n" +
                "You slowly walking hoping that nothing is going to jump out at you. As you walk in the door behind you slams shut\n" +
                "and you can't get out...");

        System.out.println("\nOnce you enter you see the room is large with a sofa on the side of the room with a tv. Opposite it has a large\n" +
                "fire place. On the left of it is a window with a small ledge and barriers. The room was fitted with a white carpet that is now ruined\n" +
                "but for some reason one thing was out of place... there was a crafting table at the back of the room by the lonely door right opposite you!");


        System.out.print(
                "\na) Investigate the door in front" +
                "\nb) Go to crafting table" +
                "\nc) Check out window ledge" +
                "\nd) Check the bottom right corner of room" +
                "\ne) Check the sofa" +
                "\nf) Check your inventory");    //add an option so if empty it saysing nothing is there

        //need to create list here and to choose where each time goes you do list.add(o, "trains");
        //create a list for the inventory
        ArrayList inventory = new ArrayList();
        

        boolean whileLoop_abandonedRoom = true;

        while(whileLoop_abandonedRoom) {

            System.out.print("\nPlease Choose One: ");

            String abandoned_room = scanner.next().toLowerCase();

            if (abandoned_room.equals("a")) {

                System.out.println("The door in front of you is locked. Try looking around to see how you can get out.");
            }
            else if(abandoned_room.equals("b")){

                System.out.println("You have entered the crafting area!");
                System.out.print("\na) Fix Wooden handle" +
                            "\nb) Sharpen metal piece" +
                            "\nc) Craft ");

                //IF SECTION FOR CRAFTING ROOM
                String abandoned_area_CraftingRoom = scanner.next().toLowerCase();
                if(abandoned_area_CraftingRoom.equals("a")){




                }




                //END OF IF FOR CRAFTING ROOM!
                


            }
            else if(abandoned_room.equals("c")){
                System.out.println("On the window ledge you find a long wooden handle." +
                        "You think to your self if only i had the top i could make an axe.");


                System.out.print("\nWooden Handle is added to inventory");
                inventory.add("Wooden Handle");

            }
            else if(abandoned_room.equals("d")){

                System.out.println("In the corner you spot a piece of metal rod with a note on it " +
                        "\non the note it says to get the Ulimate Power Piece you need to get 6 of these pieces...");


                inventory.add("Metal Rod #1 of 6");
                System.out.println("\nMetal Rod (#1 of 6) has been added to your inventory.");

            }
            else if(abandoned_room.equals("e"))  {
                System.out.println("As you are looking in all the crevices of the sofa, you find a shiny metal object. " +
                        "\nHmm, I wonder if this could be used to assemble the axe");

                
                System.out.println("\nMetal Piece was added to inventory");
                inventory.add("Metal Piece");

            }

        }  //end of while loop

        
            }


    public static int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;

    }



}
