package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
    private int[][][] maps = new int[3][13][13];
    /**
     * ��ʼ����ͼ���ݼ򵥽����Ƕ�ȡ����map�ļ�����������ִ浽��ά������
     */
    public ReadMap() {
    	//�����Զ��ж��ļ���
    	File f = new File("maps");
        File fs[] = f.listFiles();                
            //����i<=1ֻ�ǲ����������м���д��
            for (int i = 1; i <= fs.length; i++) {
                    try {
                            BufferedReader in = new BufferedReader(new FileReader("maps/" + i + ".map"));                             
                            String line; 
                            int row = 0;
                            // ���ж�ȡ������ÿ��������뵽������
                            while ((line = in.readLine()) != null) {
                                    String[] temp = line.split("\\s+");
                                    for (int j = 0; j < temp.length; j++) {
                                            maps[i][row][j] = Integer.parseInt(temp[j]);
                                    }
                                    row++;
                            }
                            in.close();
                    } catch (FileNotFoundException e) {
                            e.printStackTrace();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }
            }
    }
    public int[][][] getMaps() {
            return maps;
    }
    
}