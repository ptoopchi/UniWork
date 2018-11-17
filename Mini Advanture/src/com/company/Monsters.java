package com.company;

public class Monsters extends MainCharacters {

    private int monster_ID;

    public Monsters(String character_name, int attacking_damage, int health, int sneaking_skill,int monster_ID) {
        super(character_name, attacking_damage, health, sneaking_skill);

        this.monster_ID = monster_ID;
    }

    public void setMonster_ID(int monster_ID) {
        this.monster_ID = monster_ID;
    }
    public int getMonster_ID() {
        return monster_ID;
    }






}
