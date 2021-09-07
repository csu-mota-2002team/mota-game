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
    
    boolean canmove = true; //�ɷ��ƶ����ضԻ�ʱ����Ըĳ�float
    private Image iBuffer;
    private Graphics gBuffer;
    Mypanel mypanel;//��Ϸ����
    Hero hero;//Ӣ��
    Toolkit kit = Toolkit.getDefaultToolkit();
    /**
     * ����ͼƬ����
     */
    Image mapimg[] = {kit.getImage("pictures/0.png"), kit.getImage("pictures/1.png"), kit.getImage("pictures/2.png"),
                    kit.getImage("pictures/3.png"), kit.getImage("pictures/4.png"), kit.getImage("pictures/5.png"),
                    kit.getImage("pictures/6.png"), kit.getImage("pictures/7.png"), kit.getImage("pictures/8.png"),
                    kit.getImage("pictures/9.png"), kit.getImage("pictures/10.png"), kit.getImage("pictures/11.png"),
                    kit.getImage("pictures/12.png"),kit.getImage("pictures/13.png")};
    /**
* ����
*/
Monster monster[] = new Monster[1]; 
    /**
     * ��ʼ��Ӣ�����������
     */
    public void init() {
            hero = new Hero();
            monster[0] = new Monster("ʷ��ķ", 50, 20, 1, 1, 1, mapimg[2]);
    }
    public startGame() {
            super("����ħ�� ���ߣ�");
            init();
            mypanel = new Mypanel();//��Ϸ����
            mypanel.setBounds(200, 32, 416, 416);
            Container c = getContentPane();//װ��
            c.setLayout(null);
            c.setBackground(Color.GRAY);
            c.add(mypanel);
            setSize(630, 482);
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mypanel.requestFocus();
    }

//��ΪҪ��������������������̳�KeyListener�ӿ�
    class Mypanel extends JPanel implements KeyListener {
            private static final long serialVersionUID = 1L;
             
            private int level = 1; //����¥����
            private int mx, my; //xy����
            private int tempmap[][][];//��ͼ����
            public Mypanel() {
                    setSize(416, 416);
                    this.addKeyListener(this);
                    this.tempmap = new ReadMap().getMaps();//��ȡmap�ļ�
                    this.mx = 0;
                    this.my = 6;
                    requestFocus();
            }
          //�����������غ�һ�������������ڿ�������¥
            /**
            * �ϲ㿪��
            */
           boolean up_stair = false;
           /**
            * �²㿪��
            */
           boolean down_stair = false;
            /**
            * ÿ����ƶ����� ǰΪ��¥���������꣬��Ϊ��¥���������꣺ �����Զ���
            * [0][0][0] [0][0][1]��¥�����һ��ʱ����λ��x,y
            * [0][1][0] [0][1][1]��¥�����һ��ʱ����λ��x,y
            */
           private int location[][][] = { { { 11, 11 }, { 0, 0 } }, { { 0, 0 }, { 12, 12 } }};
           public void readmaps(int level) {
        	   // ��ȡ���²�� ��ʼ����
        	   int face;
			if (up_stair) {
        		   mx = location[level - 1][0][0];
        		   my = location[level - 1][0][1];
        	   } else if (down_stair) {
        		   mx = location[level - 1][1][0];
        		   my = location[level - 1][1][1];
        	   }
                         

           //����ƶ���������
           else if (tempmap[level][mx][my] == 10) {
        	   tempmap[level][mx][my] = 11;
        	   level++;
        	   up_stair = true;//��¥��ʶ��
        	   readmaps(level);//��ȡ�²�����
        
        	   up_stair = false;
           }else if (tempmap[level][mx][my] == 0) {
        	   tempmap[level][mx][my] = 12;
        	   level--;
        	   down_stair = true;//��¥��ʶ��                                
        	   readmaps(level);//��ȡ�²�����
        
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
                                    //����ͼͼƬ��ӵ���Ϸ���� ͼƬ����1��ʼ�����0��ʼ����Ҫ-1
                                   gBuffer.drawImage(mapimg[tempmap[level][i][j]], j * 32, i * 32, 32, 32, this);                           
                            }
                    }
                   //��ʱ��2��2�����һֱʷ��ķ
//                   tempmap[1][1][1]=2;
//                   gBuffer.drawImage(mapimg[2], 1* 32, 1 * 32, 32, 32, this);
                   //��Ӣ��ͼƬ��ӵ� Ӣ�۵�����ֻ��ִ��һ�����Ժ�������һ���Ƿ��һ�ν���Ŀ��ر�ʶ��ֹ��������Ӣ��
                    gBuffer.drawImage(mapimg[0], my * 32, mx * 32, 32, 32, this);
                    boolean isStart = true;
					if(isStart ) {//��ʼ��Ӣ��ͼƬ������ִֻ��һ��
                        tempmap[level][mx][my]=0;
                        gBuffer.drawImage(mapimg[0], my * 32, mx * 32, 32, 32, this);
                        isStart=false;
                }
                    g.drawImage(iBuffer, 0, 0, this);
                    requestFocus();
            }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO �Զ����ɵķ������
				
			}               
    }
}