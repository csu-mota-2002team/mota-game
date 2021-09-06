package com.nll.oop1;

public class Sword {
    public int Num = 6;

    public Sword(){};
    public void addATK(Hero hero){
        hero.ATK += 15;
    }
    public int getNum(){
        return this.Num;
    }
}
