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
    boolean canmove = true; //�ɷ��ƶ����ضԻ�ʱ����Ըĳ�float
    private Image iBuffer;
    private Graphics gBuffer;
    Mypanel mypanel;//��Ϸ����
    Mypanel.MyState myState;//���Խ���
    Hero hero;//Ӣ��
    Toolkit kit = Toolkit.getDefaultToolkit();
    /**
     * ����ͼƬ����
     */
    Image mapimg[] = {kit.getImage("pictures/0.png"), kit.getImage("pictures/1.png"), kit.getImage("pictures/2.png"),
                    kit.getImage("pictures/3.png"), kit.getImage("pictures/4.png"), kit.getImage("pictures/5.png"),
                    kit.getImage("pictures/6.png"), kit.getImage("pictures/7.png"), kit.getImage("pictures/8.png"),
                    kit.getImage("pictures/9.png"), kit.getImage("pictures/10.png"), kit.getImage("pictures/11.png"),
                    kit.getImage("pictures/12.png"),kit.getImage("pictures/13.png"), kit.getImage("pictures/14.png"),
                    kit.getImage("pictures/15.png"),kit.getImage("pictures/16.png")};
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
            mypanel.setBounds(200, 0, 416, 416);
            myState = mypanel.new MyState();//�������
            myState.setBounds(30, 32, 128, 352);
            Container c = getContentPane();//װ��
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
                                //����ͼͼƬ��ӵ���Ϸ���� ͼƬ����1��ʼ�����0��ʼ����Ҫ-1
                               gBuffer.drawImage(mapimg[tempmap[level][i][j]], j * 32, i * 32, 32, 32, this);                           
                        }
                }
               
                //��Ӣ��ͼƬ��ӵ� Ӣ�۵�����ֻ��ִ��һ�����Ժ�������һ���Ƿ��һ�ν���Ŀ��ر�ʶ��ֹ��������Ӣ��
     
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
                                // �����ƶ�                                
                                if(my-1>-1) move(mx,my-1,15);
                        } else if (e.getKeyCode() == 38) {
                                // �����ƶ�                        
                                if(mx-1>-1) move(mx-1,my,14);
                        } else if (e.getKeyCode() == 39) {
                                // �����ƶ�                
                                if(my+1<13) move(mx,my+1,16);
                        } else if (e.getKeyCode() == 40) {
                                // �����ƶ�                        
                                if(mx+1<13) move(mx+1,my,0);
                        }
                } 
                mypanel.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
        
        //���Խ���
        
        class MyState extends JPanel {         
          @Override
            public void paint(Graphics g) {
        	  
                    Font font = new Font("TimesRoman", Font.BOLD + Font.ITALIC, 18);
               
                    g.setFont(font);                  
                    g.drawString("����  " + hero.getHp(), 0, 2 * 32);
                    g.drawString("����  " + hero.getAtk(), 0, 3 * 32);
                    g.drawString("����  " + hero.getDef(), 0, 4 * 32);
                    g.drawString("Կ��  " + hero.getKey(), 0, 5 * 32);
                   g.drawString("��" + mypanel.level + "��", 0, 350);
            }
    }
   
             
    
//�����ƶ����������ж��ƶ�Ŀ�ĵ�����������ַ���Ĳ�ͬʵ�ַ��������Ȳ�д��ֻ�ӻ������ƶ�
        public void move(int gox, int goy,int face) {
        	
                if (tempmap[level][gox][goy] == 13) {
                	tempmap[level][gox][goy]=face;
                        tempmap[level][mx][my] = 13;
                        mx = gox;
                        my = goy;
                } 
              
                mypanel.repaint();
                repaint();//ˢ��
        }
        @Override
        public void keyTyped(KeyEvent e) { 
                 
        }

}}

