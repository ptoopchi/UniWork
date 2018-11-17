package com.company;

public class Attacking_Enemies {

    private String Enemy_name;
    private int Enemy_health;
    private int Enemy_damage;
    private int Enemy_sneak_skill;

    public Attacking_Enemies(String enemy_name, int enemy_health, int enemy_damage, int enemy_sneak_skill) {
        Enemy_name = enemy_name;
        Enemy_health = enemy_health;
        Enemy_damage = enemy_damage;
        Enemy_sneak_skill = enemy_sneak_skill;
    }

    public String getEnemy_name() {
        return Enemy_name;
    }

    public void setEnemy_name(String enemy_name) {
        Enemy_name = enemy_name;
    }


    public int getEnemy_health() {
        return Enemy_health;
    }

    public void setEnemy_health(int enemy_health) {
        Enemy_health = enemy_health;
    }


    public int getEnemy_damage() {
        return Enemy_damage;
    }

    public void setEnemy_damage(int enemy_damage) {
        Enemy_damage = enemy_damage;
    }


    public int getEnemy_sneak_skill() {
        return Enemy_sneak_skill;
    }

    public void setEnemy_sneak_skill(int enemy_sneak_skill) {
        Enemy_sneak_skill = enemy_sneak_skill;
    }




}
