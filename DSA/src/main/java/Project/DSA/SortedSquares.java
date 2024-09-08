package Project.DSA;

import java.util.Arrays;

public class SortedSquares {
	
    public static int[] sortedSquares(int[] nums) {
    	
//    	for (int i = 0; i< nums.length; i++)
//    	{
//    		nums[i] = nums[i]*nums[i];
//    	}
//   	
//    	Arrays.sort(nums);
//
//		return nums;
//    }
    	
    	int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int index = n - 1; // Start filling result from the end

        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--; // Move to the next position in result array
        }

        return result;
    }

}
