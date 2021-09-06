package com.nll.oop1;

public class Keys {
    public int Num = 8;

    public Keys(){};
    public void addKey(Hero hero){
        hero.Key += 1;
    }
    public int getNum(){
        return this.Num;
    }
}
