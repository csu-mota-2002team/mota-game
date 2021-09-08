package item;

public class Hero {
    public int HP;
    public int Key;
    public int Num = 0 ;
    public int ATK;
    public int DEF;

    public Hero() {
        this.HP = 200;
        this.Key = 0;
        this.ATK = 20;
        this.DEF = 20;
    }
    
    public  Hero(int HP,int Key,int ATK,int DEF){
        this.ATK = ATK;
        this.HP = HP;
        this.Key = Key;
        this.DEF = DEF;
    }
    @Override
    public String toString(){
        return "当前勇者的生命值为："+this.HP+"攻击力为："+this.ATK+"防御力为："+this.DEF+"钥匙个数为："+this.Key;
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

    public int getKey(){
        return this.Key;
    }

    public void setKey(int Key){
        this.Key = Key;
    }

    public int getNum(){
        return this.Num;
    }

}
