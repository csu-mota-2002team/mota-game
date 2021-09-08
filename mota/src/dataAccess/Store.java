package dataAccess;

import java.sql.SQLException;

/**
 * 用于测试DBUtils类中的store函数
 * @author 曾科浩
 *
 */
public class Store {
	public static void main(String[] args) throws SQLException {
		DBUtils db = new DBUtils();
		
		db.store();
		
		db.getColse();
	}
	
	
	
}
