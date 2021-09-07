package mota1;

import java.awt.Image;

public class Hero extends Stuff{
	
     private int hp;
     private int atk;
     private int def; 
     private int key; 
     private int maxstair;//目前高层用于以后使用飞机跳层
     public Image hImage;

/***set get 以及构造方法省略*/
//遇到怪物触发战斗的方法
     public int attack(Monster m) {
             // 有效输出=攻击-怪物护甲
             int h_harm = atk - m.def;
             // 承受伤害值=怪物攻击-护甲
             int m_harm = m.atk - def;
             //击败怪物需要的回合数
             int Rounds = 0;
             if (h_harm>0) {
                     Rounds = (int)Math.ceil((double)m.hp/h_harm);
             }else {
                     //无法击穿怪物护甲
                     return 0;
             }
             if (h_harm > 0 && m_harm <= 0) {
                     //碾压怪物方法
                    
                     return 1;
             }  else if(Rounds*m_harm>=hp){
                     //生命值不足以支付消耗
                     return 0;
             } else {
                     //两败俱伤
                     m.hp = 0;
                     hp=hp-(Rounds*m_harm);
                   
             }
             return -1;
     }



public int getHp() {
	return hp;
}

public void setHp(int hp) {
	this.hp = hp;
}

public int getAtk() {
	return atk;
}

public void setAtk(int atk) {
	this.atk = atk;
}

public int getDef() {
	return def;
}

public void setDef(int def) {
	this.def = def;
}



public int getMaxstair() {
	return maxstair;
}

public void setMaxstair(int maxstair) {
	this.maxstair = maxstair;
}

public Image gethImage() {
	return hImage;
}

public void sethImage(Image hImage) {
	this.hImage = hImage;
}



public int getKey() {
	return key;
}



public void setKey(int key) {
	this.key = key;
}
}
