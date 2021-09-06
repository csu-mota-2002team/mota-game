package com.nll.oop1;

public class Door {
    public int Num = 9;

    public Door(){};
    public int Open(Hero hero){
        if(hero.Key >= 1){
            hero.Key -= 1;
            return 1;
        }
        else {
            return 0;
        }
    }
    public int getNum(){
        return this.Num;
    }
}
