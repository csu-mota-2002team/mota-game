package dataAccess;

import java.sql.SQLException;

/**
 * 用于测试DBUtils中的load类
 * @author 曾科浩
 *
 */
public class load {
	public static void main(String[] args) throws SQLException {
		DBUtils db = new DBUtils();
		
		//db.load(1);
		db.loadHero(1);
		//db.loadMap(1);
		
		for(int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 13; j++)
			{
				for (int k = 0; k < 13; k++)
				{
					System.out.print(data.mapArray[i][j][k] + " ");
				}
				System.out.println();
			}
		}
		
		db.getColse();
	}
}
