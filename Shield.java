package com.nll.oop1;

public class Shield {
    public int Num = 7;

    public Shield(){};
    public void addDEF(Hero hero){
        hero.DEF += 15;
    }
    public int getNum(){
        return this.Num;
    }
}
