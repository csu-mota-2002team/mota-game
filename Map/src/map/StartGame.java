package map;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class startGame extends JFrame  {
    
    boolean canmove = true; //可否移动开关对话时候可以改成float
    private Image iBuffer;
    private Graphics gBuffer;
    Mypanel mypanel;//游戏界面
    Hero hero;//英雄
    Toolkit kit = Toolkit.getDefaultToolkit();
    /**
     * 载入图片数据
     */
    Image mapimg[] = {kit.getImage("pictures/0.png"), kit.getImage("pictures/1.png"), kit.getImage("pictures/2.png"),
                    kit.getImage("pictures/3.png"), kit.getImage("pictures/4.png"), kit.getImage("pictures/5.png"),
                    kit.getImage("pictures/6.png"), kit.getImage("pictures/7.png"), kit.getImage("pictures/8.png"),
                    kit.getImage("pictures/9.png"), kit.getImage("pictures/10.png"), kit.getImage("pictures/11.png"),
                    kit.getImage("pictures/12.png"),kit.getImage("pictures/13.png")};
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
            mypanel.setBounds(200, 32, 416, 416);
            Container c = getContentPane();//装箱
            c.setLayout(null);
            c.setBackground(Color.GRAY);
            c.add(mypanel);
            setSize(630, 482);
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
                    this.mx = 0;
                    this.my = 6;
                    requestFocus();
            }
          //创建两个开关和一个坐标数组用于控制上下楼
            /**
            * 上层开关
            */
           boolean up_stair = false;
           /**
            * 下层开关
            */
           boolean down_stair = false;
            /**
            * 每层的移动坐标 前为上楼后所在坐标，后为下楼后所在坐标： 坐标自定义
            * [0][0][0] [0][0][1]下楼进入第一层时所在位置x,y
            * [0][1][0] [0][1][1]上楼进入第一层时所在位置x,y
            */
           private int location[][][] = { { { 11, 11 }, { 0, 0 } }, { { 0, 0 }, { 12, 12 } }};
           public void readmaps(int level) {
        	   // 获取上下层的 初始坐标
        	   int face;
			if (up_stair) {
        		   mx = location[level - 1][0][0];
        		   my = location[level - 1][0][1];
        	   } else if (down_stair) {
        		   mx = location[level - 1][1][0];
        		   my = location[level - 1][1][1];
        	   }
                         

           //添加移动触发方法
           else if (tempmap[level][mx][my] == 10) {
        	   tempmap[level][mx][my] = 11;
        	   level++;
        	   up_stair = true;//上楼标识符
        	   readmaps(level);//获取下层坐标
        
        	   up_stair = false;
           }else if (tempmap[level][mx][my] == 0) {
        	   tempmap[level][mx][my] = 12;
        	   level--;
        	   down_stair = true;//下楼标识符                                
        	   readmaps(level);//获取下层坐标
        
        	   down_stair = false;
           }
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
                   //临时在2，2坐标加一直史莱姆
//                   tempmap[1][1][1]=2;
//                   gBuffer.drawImage(mapimg[2], 1* 32, 1 * 32, 32, 32, this);
                   //将英雄图片添加到 英雄的坐标只能执行一次所以后面会添加一个是否第一次进入的开关标识防止出现两个英雄
                    gBuffer.drawImage(mapimg[0], my * 32, mx * 32, 32, 32, this);
                    boolean isStart = true;
					if(isStart ) {//初始化英雄图片方法，只执行一次
                        tempmap[level][mx][my]=0;
                        gBuffer.drawImage(mapimg[0], my * 32, mx * 32, 32, 32, this);
                        isStart=false;
                }
                    g.drawImage(iBuffer, 0, 0, this);
                    requestFocus();
            }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}               
    }
}