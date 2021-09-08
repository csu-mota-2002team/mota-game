package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import item.Hero;
import item.example;

/**
 *jdbc�����ࣺ
 *
 */
public class DBUtils {
	private String url = "jdbc:mysql://localhost:3306/mota?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai";
	private String user = "root";
	private String password = "082856";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//private Hero hero = new Hero();
	
	//��������
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	/*
	 * �޲ι��캯��������getCont() ������ʼ�������ݿ�
	 */
	DBUtils(){
		System.out.println("��ʼ�������ݿ�");
		getCont();
	}
	
	/**
	 * �������ݿ⣬�ж����ݿ��Ƿ����ӳɹ�</br>
	 * ����жϽ��
	 */
	private void getCont() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("�������ݿ�ɹ�");
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("�������ݿ�ʧ��");
			e.printStackTrace();
		}
	}

	/**
	 * ���溯���ĺϼ�������storeHero()��storeMap()</br>
	 * �洢hero���ԡ���ͼ����λ���������</br>
	 * @throws SQLException
	 */
	public void store() throws SQLException {
		int times = this.storeHero();
		this.storeMap(times);
	}
	
	/**
	 * �˺������Դ���Ӣ����ֵ��HP��Key��ATK��DEF����mysql���ݿ���</br></br>
	 * 
	 * �����ȼ�����ݿ�����Ӧ���Ƿ����пմ浵����������д浵</br>
	 * ��������ҳ�����һ�δ浵�����д浵</br>
	 * 
	 * @return ����һ��int���͵ĵ�ǰ�Ĵ�������� ����ʧ���򷵻�0</br>
	 * @throws SQLException 
	 */
	public int storeHero() throws SQLException {
		//��ʼ������
		int times = 0;
		int count = 0;
		int row = 1;
		//Ӣ��״̬����
		int HP = hero.HP;
		int Key = hero.Key;
		int ATK = hero.ATK;
		int DEF = hero.DEF;
		
		//����Ҫִ�е�SQL���
		String sql = "select * from hero";
		//��ȡִ�ж���
		pstmt = conn.prepareStatement(sql);		
		//ִ��SQL���
		rs = pstmt.executeQuery(sql);
		
		//�ӱ�ĵ�һ�ж�ȡÿ��HP��ֵ���ж��Ƿ�Ϊ0��Ϊ0����д浵
		while(rs.next())
		{
			//��ȡÿ���浵��HP���ж��Ƿ�Ϊ0��Ϊ0����д浵������row++�ٽ���ѭ��
			int n = rs.getInt("HP");
			System.out.println("n:" + n);
			
			if(n == 0)
			{
				//����doUpdate()�����޸ı���ֵ
				String s1 = "update hero set HP = ?, `Key` = ?, ATK = ?, DEF = ? where count = ?";
				Object params[] = {HP,Key,ATK,DEF,row};
				doUpdate(s1,params);

				System.out.println("�������޸�");
				count = row;
				System.out.println(count);
				//��¼�������
				times = count;
				//����ѭ��
				break;
			}
			
			else
			{
				row++;
			}
		}
		
		count = row;
		System.out.println("count:" + count);
	
		//���浵��ȫ��ʹ��,��count�е���Сֵ���ٶ�3ȡ������ݣ��ٶ���Сֵ��3��Ϊ�������
		if(count > 3)
		{
			int minCount = 0;
			String s = "select min(count) from hero";
			pstmt = conn.prepareStatement(s);
			rs = pstmt.executeQuery(s);
			//��ȡhero����count�ֶ��е���Сֵ
			while (rs.next()) {
				minCount = rs.getInt("min(count)");
				System.out.println("minCount:" + rs.getString("min(count)") );
				//System.out.println("minCount:" + rs.getString(1));
			}
				
			//����Ӣ������
			//�ж���Ҫ�޸ĵı������������updateId����
			int updateId = minCount % 3;
			//������Ϊ0ʱ��ǿ�иı�updateId
			if(updateId == 0)
			{
				updateId = 3;
			}
			
			//���±����ض��е�����
			String s2 = "update hero set HP = ?, `Key` = ?, ATK = ?, DEF = ? , count = ? where id = ?"; //minCount + 3
			Object params[] = {HP,Key,ATK,DEF,times,updateId};
			doUpdate(s2,params);
			
			times = minCount + 3;
		}
		
		System.out.println("times:" + times);
		return times;
	}
	
	/**
	 * @author ���ƺ� </br>
	 * ���������ڴ����ͼ������������</br></br>
	 * 
	 * �������ȶ������times����ȡ�࣬</br>
	 * �ٶ�ȡ�����ʹ��switch��� ѡ��Ҫ����ı�</br>
	 * ������ѭ���洢���������ݿ�</br>
	 * 
	 * @times  ����浵���� </br>
	 * @param num��h��x��y(�����Ӧ�롢��������Ӧ�����ꡢ��Ӧ������)
	 */
	public void storeMap(int times) {
		
		int updateId = times % 3;
		
		switch (updateId) {
			case 1:
				for(int h = 0; h < 2; h++)
				{
					for(int y = 0; y < 13; y++)
					{
						for (int x = 0; x < 13; x++)
						{
							int num = data.mapArray[h][y][x];
							String sql = "update map01 set num = ? where h = ? and x = ? and y = ?";
							Object params[] = {num, h, x, y};
							doUpdate(sql, params);
						}
					}
				}
				
				break;
			case 2:
				for(int h = 0; h < 2; h++)
				{
					for(int y = 0; y < 13; y++)
					{
						for (int x = 0; x < 13; x++)
						{
							int num = data.mapArray[h][y][x];
							String sql = "update map02 set num = ? where h = ? and x = ? and y = ?";
							Object params[] = {num, h, x, y};
							doUpdate(sql, params);
						}
					}
				}
				
				break;
				
			case 0:
				for(int h = 0; h < 2; h++)
				{
					for(int y = 0; y < 13; y++)
					{
						for (int x = 0; x < 13; x++)
						{
							int num = data.mapArray[h][y][x];
							String sql = "update map03 set num = ? where h = ? and x = ? and y = ?";
							Object params[] = {num, h, x, y};
							doUpdate(sql, params);
						}
					}
				}
				
				break;
		}
	}
	
	/**
	 * ������Ϸ���ȵ��ܺ���������loadHero(), loadMap()
	 * 
	 * @param id����Ҫ��ȡ�Ĵ浵�����ִ���
	 * 
	 * @throws SQLException
	 */
	public void load(int id) throws SQLException {
		this.loadHero(id);
		this.loadMap(id);
	}
	
	/**
	 * ����Ӣ�����Դ浵</br></br>
	 * 
	 * ���������ô����id��Ϊ�����������ݿ��еõ���Ӧid��һ�м�¼</br>
	 * �жϲ��ҵļ�¼��HP�Ƿ�Ϊ0(Ĭ��ֵ)�����ж��Ƿ�Ϊ�մ浵</br>
	 * ��Ϊ�մ浵������ʾ</br>
	 * ������д浵</br>
	 * 
	 * @param id����Ҫ��ȡ�Ĵ浵�����ִ���
	 * 
	 * @throws SQLException 
	 */
	public void loadHero(int id) throws SQLException {
		//ʹ��doSelect()��ȡӢ�۵���ֵ
		String s1 = "select * from hero where id = ?";
		Object params[] = {id};
		rs =  doSelect(s1, params);
		
		rs.next();
//		System.out.println(rs.getInt("HP"));
//		System.out.println(rs.getInt("Key"));
//		System.out.println(rs.getInt("ATK"));
//		System.out.println(rs.getInt("DEF"));		
		
		//�жϴ浵Ϊ�յ����
		if(rs.getInt("HP") == 0)
		{
			JOptionPane.showMessageDialog(null, "�ô浵Ϊ��", "��ʾ",JOptionPane.PLAIN_MESSAGE);
		}
		
		//�浵��Ϊ��
		else
		{
			//��дhero������
			hero.HP = rs.getInt("HP");
			hero.Key = rs.getInt("Key");
			hero.ATK = rs.getInt("ATK");
			hero.DEF = rs.getInt("DEF");
		}
		
		System.out.println("HP:" + hero.HP);
		System.out.println("Key:" + hero.Key);
		System.out.println("ATK:" + hero.ATK);
		System.out.println("DEF:" + hero.DEF);
	}
	
	/**
	 * ������Ϸ��ͼ�浵</br></br>
	 * 
	 * ���ݲ�ͬ��id���룬����switch��䣬�����ݿ��в�ͬ�ı���е���</br>
	 * ��������forѭ�����ӱ����õı������λ�ö�ȡ��ͼ������������ά������</br>
	 * 
	 * @param id����Ҫ��ȡ�Ĵ浵�����ִ���
	 * @throws SQLException
	 */
	public void loadMap(int id) throws SQLException {
		//��ȡ��ͼ��Ʒ��־
		//int mapArray1[][][] = new int[2][13][13];
		
		switch (id) {
			case 1:
				//����forѭ�������� h:0-1   y:0-13  x:0-13
				for(int h = 0; h < 2; h++)
				{
					for(int y = 0; y < 13; y++)
					{
						for (int x = 0; x < 13; x++)
						{
							//��map01��Ѱ��h��y��x���������ļ�¼��ѡ���num
							String s2 = "select num from map01 where h = ? and y = ? and x = ?";
							Object params[] = {h,y,x};
							rs = doSelect(s2,params);

							rs.next();
							//�޸�mapArray(��ά����)�е���ֵ
							data.mapArray[h][y][x] = rs.getInt("num");
							
//							mapArray1[h][y][x] = rs.getInt("num");
//							data.mapArray[h][y][x] = mapArray1[h][y][x];
							
							System.out.println(data.mapArray[h][y][x]);
						}
					}
				}
				
				break;
				
			case 2:
				for(int h = 0; h < 2; h++)
				{
					for(int y = 0; y < 13; y++)
					{
						for (int x = 0; x < 13; x++)
						{
							String s2 = "select num from map02 where h = ? and y = ? and x = ?";
							Object params[] = {h,y,x};
							rs = doSelect(s2,params);
							
							rs.next();
							
							data.mapArray[h][y][x] = rs.getInt("num");
							System.out.println(data.mapArray[h][y][x]);
						}
					}
				}
				
				break;
			case 3:
				for(int h = 0; h < 2; h++)
				{
					for(int y = 0; y < 13; y++)
					{
						for (int x = 0; x < 13; x++)
						{
							String s2 = "select num from map03 where h = ? and y = ? and x = ?";
							Object params[] = {h,y,x};
							rs = doSelect(s2,params);
							
							rs.next();
							
							data.mapArray[h][y][x] = rs.getInt("num");
							System.out.println(data.mapArray[h][y][x]);
						}
					}
				}
				
				break;
		}
		
	}
	
	
	
	
	/**
	 * ��ȡִ�ж���ִ�иĵ�sql���
	 * 
	 * @����sql
	 * 		String sql ="update ����  set �ֶ���1 = �ֶ�ֵ1���ֶ���2 = �ֶ�ֵ2��������where ����"</br>
	 * 
	 * 		String sql ="update ����  set �ֶ���1 = �ֶ�ֵ�����ֶ���2 = ����������where ����=��"</br>
	 * @����params
	 * 		Object params[] = ��ռλ���Ĳ���ֵ��
	 */
	
	public void doUpdate(String sql,Object params[]){
		try {
			pstmt = conn.prepareStatement(sql);
			//��ָ����ռλ�����ò���ֵ
			if(params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					pstmt.setObject(i + 1, params[i]);
				}
				
				System.out.println(pstmt.toString());
				//ִ��SQL���
				pstmt.execute();
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡִ�ж���ִ�в飨��ȡ����sql���
	 * 
	 * @param sql  </br>
	 * 			String sql ="select * ����  where ����"</br>
	 * 
	 * 			String sql ="select * ����  where ����=��"</br>
	 * @param params </br>
	 * 			Object params[] = ��ռλ���Ĳ���ֵ��
	 * @return
	 * 			����ResultSet���ͣ��û�����ͨ���Ժ�������ֵ�����õ���¼��ֵ</br>
	 * 			������ rst.next() ��rst.getString()��
	 */
	public ResultSet doSelect(String sql,Object params[]){
		ResultSet rst = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//��ָ����ռλ�����ò���ֵ
			if(params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					pstmt.setObject(i + 1, params[i]);
				}
				
				System.out.println(pstmt.toString());
				//ִ��sql���
				rst = pstmt.executeQuery();
				
				
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return rst;
	}
	
	/**
	 * �ͷ���Դ</br>
	 * �ͷ�˳��Ϊ���ȿ����Ķ�����ͷţ������Ķ������ͷ�
	 */
	public void getColse() {
		try {
			//�жϸ�������Ƿ�Ϊ��
			if (rs != null) {
				rs.close();
			}
			
			if (pstmt!=null) {
				pstmt.close();
			}
			
			if (conn!=null) {
				conn.close();	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
