package dataAccess;

/**
 * 作为DBUtils中储存map的测试三维数组
 * @author 曾科浩
 *
 */
public class data {
	//先建立两个二维数组mapArray1、mapArray2
	public static int[][] mapArray1 = { 	{0,1,2,3,4,5,6,7,8,9,10,11,12},
									{1,0,0,0,0,0,0,0,0,0,0,0,0},
									{2,0,0,0,0,0,0,0,0,0,0,0,0},
									{3,0,0,0,0,0,0,0,0,0,0,0,0},
									{4,0,0,0,0,0,0,0,0,0,0,0,0},
									{5,0,0,0,0,0,0,0,0,0,0,0,0},
									{6,0,0,0,0,0,0,0,0,0,0,0,0},
									{7,0,0,0,0,0,0,0,0,0,0,0,0},
									{8,0,0,0,0,0,0,0,0,0,0,0,0},
									{9,0,0,0,0,0,0,0,0,0,0,0,0},
									{10,0,0,0,0,0,0,0,0,0,0,0,0},
									{11,0,0,0,0,0,0,0,0,0,0,0,0},
									{12,0,0,0,0,0,0,0,0,0,0,0,0}
								};
	
	public static int[][] mapArray2 = { 	{0,1,2,3,4,5,6,7,8,9,10,11,12},
									{11,0,0,0,0,0,0,0,0,0,0,0,0},
									{22,0,0,0,0,0,0,0,0,0,0,0,0},
									{33,0,0,0,0,0,0,0,0,0,0,0,0},
									{44,0,0,0,0,0,0,0,0,0,0,0,0},
									{55,0,0,0,0,0,0,0,0,0,0,0,0},
									{66,0,0,0,0,0,0,0,0,0,0,0,0},
									{77,0,0,0,0,0,0,0,0,0,0,0,0},
									{88,0,0,0,0,0,0,0,0,0,0,0,0},
									{99,0,0,0,0,0,0,0,0,0,0,0,0},
									{100,0,0,0,0,0,0,0,0,0,0,0,0},
									{110,0,0,0,0,0,0,0,0,0,0,0,0},
									{120,0,0,0,0,0,0,0,0,0,0,0,0}
		};
	
	//再讲两个二维数组组合成一个三维数值
	public static int[][][] mapArray = {mapArray1,mapArray2};
	
	//以下为对三维数组的测试
	public static void main(String[] args) {
		System.out.println(mapArray[0][1][0]);
		System.out.println(mapArray[0][0][2]);
		
		mapArray[0][0][0] = 100;
		System.out.println(mapArray[0][0][0]);
		
		for(int i = 0; i < 13; i++)
		{
			System.out.print(mapArray[1][0][i] + " ");
		}
		
		System.out.println();
		
		for(int i = 0; i < 13; i++)
		{
			System.out.print(mapArray[1][i][0] + " ");
		}
	}
}
