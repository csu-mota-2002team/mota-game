package com.nll.oop1;
import javax.swing.JOptionPane;

public class Mystery {
    public int Num = 12;

    public Mystery(){};
    public void mystery(Hero hero){
        java.util.Random r=new java.util.Random();
        int n = r.nextInt()*24;

        switch (n){
            case 0:
                hero.HP += 30;
                JOptionPane.showMessageDialog(null, "路边捡到一个苹果，吃下去，香甜可口，HP回复30。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 10:
                hero.HP += 30;
                JOptionPane.showMessageDialog(null, "看见居然有一张床，睡了一觉，HP回复30。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 1:
                hero.HP -= 20;
                JOptionPane.showMessageDialog(null, "路边捡到一个苹果，吃下去，居然有毒，HP扣除20。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 11:
                hero.HP -= 20;
                JOptionPane.showMessageDialog(null, "看见居然有一张床，睡了一觉，做了噩梦，HP扣除20。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 2:
                hero.ATK += 15;
                JOptionPane.showMessageDialog(null, "石头缝隙中闪烁着光芒，拨开石块，是一把铁剑，ATK增加15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 12:
                hero.ATK += 15;
                JOptionPane.showMessageDialog(null, "石头缝隙中闪烁着光芒，拨开石块，是一根树枝，怪顺手的，ATK增加15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 3:
                hero.ATK -= 10;
                JOptionPane.showMessageDialog(null, "昨夜睡觉时，压着右手睡了10个小时，一醒来手全麻了，ATK扣除15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 13:
                hero.ATK -= 10;
                JOptionPane.showMessageDialog(null, "整天冒险，手酸痛不已，ATK扣除15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 4:
                hero.DEF += 15;
                JOptionPane.showMessageDialog(null, "杂草堆中闪烁着光芒，斩断杂草，是一顶铁盔，DEF增加15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 14:
                hero.DEF += 15;
                JOptionPane.showMessageDialog(null, "杂草堆中闪烁着光芒，斩断杂草，是一半瓜皮，戴着挺舒适，DEF增加15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 5:
                hero.DEF -= 10;
                JOptionPane.showMessageDialog(null, "昨天吃的隔夜饭菜，今天肠胃翻腾，上吐下泻，DEF扣除15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 15:
                hero.DEF -= 10;
                JOptionPane.showMessageDialog(null, "过于奔波，出现了甲沟炎的症状，疼痛难忍，DEF扣除15。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 6:
                hero.HP += 50;
                hero.DEF -= 10;
                JOptionPane.showMessageDialog(null, "捡到一本秘籍，上面写着“欲练神功必先自宫”，果断修炼，HP增加50，DEF扣除20。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 16:
                hero.HP += 50;
                hero.DEF -= 10;
                JOptionPane.showMessageDialog(null, "昨天伙食太好了，壮硕起来，但是好像变得很笨拙，HP增加50，DEF扣除20。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 7:
                hero.HP -= 50;
                hero.ATK += 20;
                JOptionPane.showMessageDialog(null, "饿了几天，消瘦不少，但是出手速度快多了，HP扣除50，ATK增加20。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
            case 17:
                hero.HP -= 50;
                hero.ATK += 20;
                JOptionPane.showMessageDialog(null, "不知名的树叶飘落到饮用水里，豪饮一口，苦涩但养生，HP扣除50，ATK增加20。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 8:
                hero.HP -= 30;
                hero.ATK += 10;
                hero.DEF += 10;
                JOptionPane.showMessageDialog(null, "看到别的勇者披荆斩棘，发愤图强训练，训练过度劳累，但卓有成效，HP扣除30，ATK增加10，DEF增加10。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 18:
                hero.HP -= 30;
                hero.ATK += 10;
                hero.DEF += 10;
                JOptionPane.showMessageDialog(null, "太久没读书了，仿佛退化了一样，越来越像猩猩，HP扣除30，ATK增加10，DEF增加10。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 9:
                hero.ATK += 5;
                hero.DEF += 5;
                JOptionPane.showMessageDialog(null, "探险过程中欣赏着风景，心情大好，ATK增加5，DEF增加5。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 19:
                hero.ATK += 5;
                hero.DEF += 5;
                JOptionPane.showMessageDialog(null, "路边有一口温泉，脱光衣服立马下水，一身的疲惫都消除了，ATK增加5，DEF增加5。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 20:
                hero.ATK -= 5;
                hero.DEF -= 5;
                JOptionPane.showMessageDialog(null, "走着走着不小心踩到小史莱姆，心情大败，ATK扣除5，DEF扣除5。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 21:
                hero.ATK -= 5;
                hero.DEF -= 5;
                JOptionPane.showMessageDialog(null, "路边有一口温泉，脱光衣服立马下水，却烫的连滚带爬离开，回想起来都害怕，ATK扣除5，DEF扣除5。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 22:
                hero.HP = 1;
                JOptionPane.showMessageDialog(null, "想起曾今愧对的那个女孩，突然天打雷劈，天雷乱轰！被雷劈到垂死！HP扣为1！", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            case 23:
                hero.DEF = 9999;
                JOptionPane.showMessageDialog(null, "梦境中遇到一位仙人指点，醒来发现已进入“至臻化境”！刀枪不入！DEF增加至9999。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
            default:
                hero.ATK = 9999;
                JOptionPane.showMessageDialog(null, "曾今扶过马路的一位老奶奶出现了，她是考验善心的菩萨！留下一把“一刀9999”离去了！ATK增加至9999！。", "奇遇事件",JOptionPane.PLAIN_MESSAGE);
                break;
        }
    }
    public int getNum(){
        return this.Num;
    }
}
