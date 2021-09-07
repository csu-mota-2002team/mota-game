package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
    private int[][][] maps = new int[3][13][13];
    /**
     * 初始化地图数据简单讲就是读取本地map文件将里面的数字存到三维数组中
     */
    public ReadMap() {
    	//新增自动判断文件数
    	File f = new File("maps");
        File fs[] = f.listFiles();                
            //这里i<=1只是测试用正常有几层写几
            for (int i = 1; i <= fs.length; i++) {
                    try {
                            BufferedReader in = new BufferedReader(new FileReader("maps/" + i + ".map"));                             
                            String line; 
                            int row = 0;
                            // 逐行读取，并将每个数组放入到数组中
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