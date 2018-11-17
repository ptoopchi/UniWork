package com.company;

public class MainCharacters {

    private String character_name;
    private int attacking_damage;
    private int health;
    private int sneaking_skill;

    //the constructor
    public MainCharacters(String character_name, int attacking_damage,int health ,int sneaking_skill){
        this.character_name = character_name;
        this.attacking_damage = attacking_damage;
        this.health = health;
        this.sneaking_skill = sneaking_skill;
    }

    //the setters
    public void setCharacter_name(String character_name){
        this.character_name = character_name;
    }
    public void setAttacking_damage(int attacking_damage){
        this.attacking_damage = attacking_damage;
    }
    public void setSneaking_skill(int sneaking_skill){
        this.sneaking_skill = sneaking_skill;
    }
    public void setHealth(int health){
        this.health = health;
    }

    //the getters
    public String getCharacter_name(){
        return this.character_name;
    }
    public int getAttacking_damage(){
        return this.attacking_damage;
    }
    public int getSneaking_skill(){
        return this.sneaking_skill;
    }
    public int getHealth(){
        return this.health;
    }


    public void messagePrint(){
        System.out.println("\nYou Have Chosen " + this.character_name +
                "\n" + "Attacking Damage: " + this.attacking_damage + "\n"
                + "Sneaking Skill: " + this.sneaking_skill+"\n");
    }



}
