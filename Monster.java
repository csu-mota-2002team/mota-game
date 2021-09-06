package com.nll.oop1;

public class Monster {
    public int HP;
    public String Name;
    public int Num;
    public int ATK;
    public int DEF;

    public Monster(String Name,int HP,int ATK,int DEF,int Num)
    {
        super();
        this.ATK = ATK;
        this.DEF = DEF;
        this.HP = HP;
        this.Num = Num;
        this.Name = Name;
    }
    @Override
    public String toString(){
        return this.Name+"的生命值为："+this.HP+"攻击力为："+this.ATK+"防御力为："+this.DEF;
    }
    public String getName(){
        return this.Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public int getHP(){
        return this.HP;
    }
    public void setHP(int HP){
        this.HP = HP;
    }
    public int getATK(){
        return this.ATK;
    }
    public void setATK(int ATK){
        this.ATK = ATK;
    }
    public int getDEF(){
        return this.DEF;
    }
    public void setDEF(int DEF){
        this.DEF = DEF;
    }
    public int getNum(){
        return this.Num;
    }
}
