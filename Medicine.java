package com.nll.oop1;

public class Medicine {
    public int Num = 5;

    public Medicine(){};
    public void addHP(Hero hero){
        hero.HP += 30;
    }
    public int getNum(){
        return this.Num;
    }
}
