package com.nll.oop1;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;

public class ststus extends Hero{
    public static JFrame frame = new JFrame();

    public static void main(String[] args) {

        String heroHP = String.valueOf(hero.HP) ;
        String heroATK = String.valueOf(hero.ATK);
        String heroDEF = String.valueOf(hero.DEF);
        String heroKey = String.valueOf(hero.Key);


        Font fnt = new Font("Serief",  + Font.BOLD, 20);
        frame.setVisible(true);
        frame.setTitle("模拟游戏界面");
        frame.setSize(630,482);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel huanhang1 = new JLabel("                                                                                                                                                                          ");
        JLabel huanhang2 = new JLabel("                                                                                                                                                                          ");
        JLabel huanhang3 = new JLabel("                                                                                                                                                                                                  ");
        JLabel huanhang4 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang5 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang6 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang7 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang8 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang9 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang10 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang11 = new JLabel("                                                                                                                                                                                                     ");
        JLabel huanhang12 = new JLabel("                                                                                                                                                                                                     ");

        JLabel statusHP = new JLabel("HP:");
        statusHP.setFont(fnt);
        JLabel statusATK = new JLabel("ATK:");
        statusATK.setFont(fnt);
        JLabel statusDEF = new JLabel("DEF:");
        statusDEF.setFont(fnt);
        JLabel HP = new JLabel(heroHP);
        HP.setFont(fnt);
        JLabel ATK = new JLabel(heroATK);
        ATK.setFont(fnt);
        JLabel DEF = new JLabel(heroDEF);
        DEF.setFont(fnt);
        JLabel statusKey = new JLabel("Key:");
        statusKey.setFont(fnt);
        JLabel Key = new JLabel(heroKey);
        Key.setFont(fnt);


        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.getContentPane().add(huanhang5);
        frame.getContentPane().add(huanhang6);
        frame.getContentPane().add(huanhang7);
        frame.getContentPane().add(huanhang12);
        frame.getContentPane().add(statusATK);
        frame.getContentPane().add(ATK);
        frame.getContentPane().add(huanhang1);
        frame.getContentPane().add(huanhang3);
        frame.getContentPane().add(huanhang9);
        frame.getContentPane().add(statusDEF);
        frame.getContentPane().add(DEF);
        frame.getContentPane().add(huanhang2);
        frame.getContentPane().add(huanhang4);
        frame.getContentPane().add(huanhang10);
        frame.getContentPane().add(statusHP);
        frame.getContentPane().add(HP);
        frame.getContentPane().add(huanhang11);
        frame.getContentPane().add(huanhang8);

        frame.getContentPane().add(statusKey);
        frame.getContentPane().add(Key);
    }
}
