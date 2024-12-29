package Project.DSA;

import java.util.ArrayList;
import java.util.List;

class KthSmallestMatrix
{
	public int kthSmallest(int[][] matrix, int k)
	{
		List<Integer> arr = new ArrayList<>();
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				arr.add(matrix[i][j]);
			}
		}
		//		System.out.println("arr: "+arr.toString());
		arr.sort(null);
		return arr.get(k);
	}
}