package mota1;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class startGame extends JFrame  {
    boolean canmove = true; //可否移动开关对话时候可以改成float
    private Image iBuffer;
    private Graphics gBuffer;
    Mypanel mypanel;//游戏界面
    Mypanel.MyState myState;//属性界面
    Hero hero;//英雄
    Toolkit kit = Toolkit.getDefaultToolkit();
    /**
     * 载入图片数据
     */
    Image mapimg[] = {kit.getImage("pictures/0.png"), kit.getImage("pictures/1.png"), kit.getImage("pictures/2.png"),
                    kit.getImage("pictures/3.png"), kit.getImage("pictures/4.png"), kit.getImage("pictures/5.png"),
                    kit.getImage("pictures/6.png"), kit.getImage("pictures/7.png"), kit.getImage("pictures/8.png"),
                    kit.getImage("pictures/9.png"), kit.getImage("pictures/10.png"), kit.getImage("pictures/11.png"),
                    kit.getImage("pictures/12.png"),kit.getImage("pictures/13.png"), kit.getImage("pictures/14.png"),
                    kit.getImage("pictures/15.png"),kit.getImage("pictures/16.png")};
    /**
* 怪物
*/
Monster monster[] = new Monster[1]; 
    /**
     * 初始化英雄与怪物属性
     */
    public void init() {
            hero = new Hero();
            monster[0] = new Monster("史莱姆", 50, 20, 1, 1, 1, mapimg[2]);
    }
    public startGame() {
            super("修仙魔塔 作者：");
            init();
           
            mypanel = new Mypanel();//游戏界面
            mypanel.setBounds(200, 0, 416, 416);
            myState = mypanel.new MyState();//属性面板
            myState.setBounds(30, 32, 128, 352);
            Container c = getContentPane();//装箱
            c.setLayout(null);
            c.setBackground(Color.GRAY);
            c.add(mypanel);
            c.add(myState);
           setResizable(false);
            setSize(630, 450);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mypanel.requestFocus();
    }

//因为要监听键盘输入所以这里继承KeyListener接口
    class Mypanel extends JPanel implements KeyListener {
            private static final long serialVersionUID = 1L;
             
            private int level = 1; //所在楼层数
            private int mx, my; //xy坐标
            private int tempmap[][][];//地图数据
            public Mypanel() {
                    setSize(416, 416);
                    this.addKeyListener(this);
                    this.tempmap = new ReadMap().getMaps();//读取map文件
                   mx = 0;
                    my = 6;
                    requestFocus();
            }
            
            @Override
            public void paint(Graphics g) {  
                if (iBuffer == null) {
                        iBuffer = createImage(this.getSize().width, this.getSize().height);
                        gBuffer = iBuffer.getGraphics();
                }
                for (int i = 0; i < 13; i++) {
                        for (int j = 0; j < 13; j++) {
                                //将地图图片添加到游戏界面 图片名从1开始数组从0开始所以要-1
                               gBuffer.drawImage(mapimg[tempmap[level][i][j]], j * 32, i * 32, 32, 32, this);                           
                        }
                }
               
                //将英雄图片添加到 英雄的坐标只能执行一次所以后面会添加一个是否第一次进入的开关标识防止出现两个英雄
     
             boolean isStart=true;
                     
              if(isStart) {
                    tempmap[level][mx][my]=0;
                    gBuffer.drawImage(mapimg[0], my * 32, mx * 32, 32, 32, this);
                    isStart=false;
                    g.drawImage(iBuffer, 0, 0, this);
                    requestFocus();
                    
            }
             
        }
           public void keyPressed(KeyEvent e) {
                if (canmove) {
                        if (e.getKeyCode() == 37) {
                                // 向左移动                                
                                if(my-1>-1) move(mx,my-1,15);
                        } else if (e.getKeyCode() == 38) {
                                // 向上移动                        
                                if(mx-1>-1) move(mx-1,my,14);
                        } else if (e.getKeyCode() == 39) {
                                // 向右移动                
                                if(my+1<13) move(mx,my+1,16);
                        } else if (e.getKeyCode() == 40) {
                                // 向下移动                        
                                if(mx+1<13) move(mx+1,my,0);
                        }
                } 
                mypanel.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
        
        //属性界面
        
        class MyState extends JPanel {         
          @Override
            public void paint(Graphics g) {
        	  
                    Font font = new Font("TimesRoman", Font.BOLD + Font.ITALIC, 18);
               
                    g.setFont(font);                  
                    g.drawString("生命  " + hero.getHp(), 0, 2 * 32);
                    g.drawString("攻击  " + hero.getAtk(), 0, 3 * 32);
                    g.drawString("防御  " + hero.getDef(), 0, 4 * 32);
                    g.drawString("钥匙  " + hero.getKey(), 0, 5 * 32);
                   g.drawString("第" + mypanel.level + "层", 0, 350);
            }
    }
   
             
    
//这里移动方法用来判断移动目的地如果碰到各种方块的不同实现方法这里先不写了只接基本的移动
        public void move(int gox, int goy,int face) {
        	
                if (tempmap[level][gox][goy] == 13) {
                	tempmap[level][gox][goy]=face;
                        tempmap[level][mx][my] = 13;
                        mx = gox;
                        my = goy;
                } 
              
                mypanel.repaint();
                repaint();//刷新
        }
        @Override
        public void keyTyped(KeyEvent e) { 
                 
        }

}}

