package map;

import java.awt.Image;

public class Hero extends Stuff {
    private int level;// Ӣ�۵ȼ�
    private int hp;
    private int atk;
    private int def;
    private int money;
    private int exp;
    private int ykey;//Կ��
    private int bkey;
    private int rkey;
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
                    money = money + m.money;
                    exp = exp + m.exp;
                    return 1;
            }  else if(Rounds*m_harm>=hp){
                    //����ֵ������֧������
                    return 0;
            } else {
                    //���ܾ���
                    m.hp = 0;
                    hp=hp-(Rounds*m_harm);
                    money = money + m.money;
                    exp = exp + m.exp;
            }
            return -1;
    }
}