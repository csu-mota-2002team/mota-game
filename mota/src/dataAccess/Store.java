package dataAccess;

import java.sql.SQLException;

/**
 * ���ڲ���DBUtils���е�store����
 * @author ���ƺ�
 *
 */
public class Store {
	public static void main(String[] args) throws SQLException {
		DBUtils db = new DBUtils();
		
		db.store();
		
		db.getColse();
	}
	
	
	
}
