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
 *jdbc工具类：
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
	
	//加载驱动
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/*
	 * 无参构造函数，调用getCont() 函数开始链接数据库
	 */
	DBUtils(){
		System.out.println("开始链接数据库");
		getCont();
	}
	
	/**
	 * 链接数据库，判断数据库是否链接成功</br>
	 * 输出判断结果
	 */
	private void getCont() {
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("链接数据库成功");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			System.out.println("链接数据库失败");
			e.printStackTrace();
		}
	}

	/**
	 * 储存函数的合集，包括storeHero()、storeMap()</br>
	 * 存储hero属性、地图各个位置物体代号</br>
	 * @throws SQLException
	 */
	public void store() throws SQLException {
		int times = this.storeHero();
		this.storeMap(times);
	}
	
	/**
	 * 此函数用以储存英雄数值（HP、Key、ATK、DEF）至mysql数据库中</br></br>
	 * 
	 * 函数先检测数据库中相应表是否还留有空存档，若有则进行存档</br>
	 * 否则从中找出最早一次存档，进行存档</br>
	 * 
	 * @return 返回一个int类型的当前的储存次数， 操作失败则返回0</br>
	 * @throws SQLException 
	 */
	public int storeHero() throws SQLException {
		//初始化变量
		int times = 0;
		int count = 0;
		int row = 1;
		//英雄状态变量
		int HP = hero.HP;
		int Key = hero.Key;
		int ATK = hero.ATK;
		int DEF = hero.DEF;
		
		//构造要执行的SQL语句
		String sql = "select * from hero";
		//获取执行对象
		pstmt = conn.prepareStatement(sql);		
		//执行SQL语句
		rs = pstmt.executeQuery(sql);
		
		//从表的第一行读取每行HP的值，判断是否为0，为0则进行存档
		while(rs.next())
		{
			//读取每个存档的HP，判断是否为0，为0则进行存档，否则row++再进行循环
			int n = rs.getInt("HP");
			System.out.println("n:" + n);
			
			if(n == 0)
			{
				//利用doUpdate()函数修改表中值
				String s1 = "update hero set HP = ?, `Key` = ?, ATK = ?, DEF = ? where count = ?";
				Object params[] = {HP,Key,ATK,DEF,row};
				doUpdate(s1,params);

				System.out.println("数据已修改");
				count = row;
				System.out.println(count);
				//记录储存次数
				times = count;
				//跳出循环
				break;
			}
			
			else
			{
				row++;
			}
		}
		
		count = row;
		System.out.println("count:" + count);
	
		//若存档已全部使用,求count中的最小值，再对3取余改数据，再对最小值加3则为储存次数
		if(count > 3)
		{
			int minCount = 0;
			String s = "select min(count) from hero";
			pstmt = conn.prepareStatement(s);
			rs = pstmt.executeQuery(s);
			//获取hero表中count字段中的最小值
			while (rs.next()) {
				minCount = rs.getInt("min(count)");
				System.out.println("minCount:" + rs.getString("min(count)") );
				//System.out.println("minCount:" + rs.getString(1));
			}
				
			//存入英雄数据
			//判断需要修改的表的行数，并由updateId储存
			int updateId = minCount % 3;
			//当余数为0时，强行改变updateId
			if(updateId == 0)
			{
				updateId = 3;
			}
			
			//更新表中特定行的数据
			String s2 = "update hero set HP = ?, `Key` = ?, ATK = ?, DEF = ? , count = ? where id = ?"; //minCount + 3
			Object params[] = {HP,Key,ATK,DEF,times,updateId};
			doUpdate(s2,params);
			
			times = minCount + 3;
		}
		
		System.out.println("times:" + times);
		return times;
	}
	
	/**
	 * @author 曾科浩 </br>
	 * 本函数用于储存地图各点代表的物体</br></br>
	 * 
	 * 本函数先对输入的times进行取余，</br>
	 * 再对取余后结果使用switch语句 选择要储存的表</br>
	 * 再利用循环存储数据至数据库</br>
	 * 
	 * @times  代表存档次数 </br>
	 * @param num、h、x、y(物体对应码、层数、对应横坐标、对应竖坐标)
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
	 * 载入游戏进度的总函数，包括loadHero(), loadMap()
	 * 
	 * @param id代表要读取的存档的数字代号
	 * 
	 * @throws SQLException
	 */
	public void load(int id) throws SQLException {
		this.loadHero(id);
		this.loadMap(id);
	}
	
	/**
	 * 载入英雄属性存档</br></br>
	 * 
	 * 本函数利用传入的id作为条件，从数据库中得到对应id的一行记录</br>
	 * 判断查找的记录中HP是否为0(默认值)即可判断是否为空存档</br>
	 * 若为空存档进行提示</br>
	 * 否则进行存档</br>
	 * 
	 * @param id代表要读取的存档的数字代号
	 * 
	 * @throws SQLException 
	 */
	public void loadHero(int id) throws SQLException {
		//使用doSelect()读取英雄的数值
		String s1 = "select * from hero where id = ?";
		Object params[] = {id};
		rs =  doSelect(s1, params);
		
		rs.next();
//		System.out.println(rs.getInt("HP"));
//		System.out.println(rs.getInt("Key"));
//		System.out.println(rs.getInt("ATK"));
//		System.out.println(rs.getInt("DEF"));		
		
		//判断存档为空的情况
		if(rs.getInt("HP") == 0)
		{
			JOptionPane.showMessageDialog(null, "该存档为空", "提示",JOptionPane.PLAIN_MESSAGE);
		}
		
		//存档不为空
		else
		{
			//改写hero的属性
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
	 * 载入游戏地图存档</br></br>
	 * 
	 * 根据不同的id输入，利用switch语句，对数据库中不同的表进行调用</br>
	 * 利用三层for循环，从被调用的表中逐个位置读取地图物体代码存入三维数组中</br>
	 * 
	 * @param id代表要读取的存档的数字代号
	 * @throws SQLException
	 */
	public void loadMap(int id) throws SQLException {
		//读取地图物品标志
		//int mapArray1[][][] = new int[2][13][13];
		
		switch (id) {
			case 1:
				//三层for循环遍历表 h:0-1   y:0-13  x:0-13
				for(int h = 0; h < 2; h++)
				{
					for(int y = 0; y < 13; y++)
					{
						for (int x = 0; x < 13; x++)
						{
							//从map01中寻找h、y、x符合条件的记录，选择出num
							String s2 = "select num from map01 where h = ? and y = ? and x = ?";
							Object params[] = {h,y,x};
							rs = doSelect(s2,params);

							rs.next();
							//修改mapArray(三维数组)中的数值
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
	 * 获取执行对象，执行改的sql语句
	 * 
	 * @参数sql
	 * 		String sql ="update 表名  set 字段名1 = 字段值1，字段名2 = 字段值2，。。。where 条件"</br>
	 * 
	 * 		String sql ="update 表名  set 字段名1 = 字段值？，字段名2 = ？，。。。where 条件=？"</br>
	 * @参数params
	 * 		Object params[] = ｛占位符的参数值｝
	 */
	
	public void doUpdate(String sql,Object params[]){
		try {
			pstmt = conn.prepareStatement(sql);
			//给指定的占位符设置参数值
			if(params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					pstmt.setObject(i + 1, params[i]);
				}
				
				System.out.println(pstmt.toString());
				//执行SQL语句
				pstmt.execute();
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取执行对象，执行查（读取）的sql语句
	 * 
	 * @param sql  </br>
	 * 			String sql ="select * 表名  where 条件"</br>
	 * 
	 * 			String sql ="select * 表名  where 条件=？"</br>
	 * @param params </br>
	 * 			Object params[] = ｛占位符的参数值｝
	 * @return
	 * 			返回ResultSet类型，用户可以通过对后续返回值操作得到记录的值</br>
	 * 			如利用 rst.next() 、rst.getString()等
	 */
	public ResultSet doSelect(String sql,Object params[]){
		ResultSet rst = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//给指定的占位符设置参数值
			if(params != null)
			{
				for (int i = 0; i < params.length; i++)
				{
					pstmt.setObject(i + 1, params[i]);
				}
				
				System.out.println(pstmt.toString());
				//执行sql语句
				rst = pstmt.executeQuery();
				
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return rst;
	}
	
	/**
	 * 释放资源</br>
	 * 释放顺序为：先开启的对象后释放，后开启的对象先释放
	 */
	public void getColse() {
		try {
			//判断各类对象是否为空
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
