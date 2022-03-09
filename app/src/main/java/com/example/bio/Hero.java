package com.example.bio;

public class Hero {
    private int hp;
    private int hitspeed;
    private int damage;
    private int movespeed;
    private int range;
    private int actroom;


    public Hero(){
        this.hp = 2;
        this.hitspeed=5;
        this.damage = 2;
        this.movespeed = 50;
        this.range = 200;
        this.actroom = 1;

    }

    public Hero(int hp, int hitspeed, int damage, int movespeed, int range) {
        this.hp = hp;
        this.hitspeed = hitspeed;
        this.damage = damage;
        this.movespeed = movespeed;
        this.range = range;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHitspeed() {
        return hitspeed;
    }

    public void setHitspeed(int hitspeed) {
        this.hitspeed = hitspeed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getActroom() {
        return actroom;
    }

    public void setActroom(int actroom) {
        this.actroom = actroom;
    }
}
