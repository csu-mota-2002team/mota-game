package mota1;

import java.awt.Image;

public class Hero extends Stuff{
	
     private int hp;
     private int atk;
     private int def; 
     private int key; 
     private int maxstair;//Ŀǰ�߲������Ժ�ʹ�÷ɻ�����
     public Image hImage;

/***set get �Լ����췽��ʡ��*/
//�������ﴥ��ս���ķ���
     public int attack(Monster m) {
             // ��Ч���=����-���ﻤ��
             int h_harm = atk - m.def;
             // �����˺�ֵ=���﹥��-����
             int m_harm = m.atk - def;
             //���ܹ�����Ҫ�Ļغ���
             int Rounds = 0;
             if (h_harm>0) {
                     Rounds = (int)Math.ceil((double)m.hp/h_harm);
             }else {
                     //�޷��������ﻤ��
                     return 0;
             }
             if (h_harm > 0 && m_harm <= 0) {
                     //��ѹ���﷽��
                    
                     return 1;
             }  else if(Rounds*m_harm>=hp){
                     //����ֵ������֧������
                     return 0;
             } else {
                     //���ܾ���
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
