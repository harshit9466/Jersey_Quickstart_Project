package Project.DSA;

import java.util.Arrays;

class sortedSquaresTetst {
    public int[] sortedSquares(int[] nums) {
        int tempNum [] = new int [nums.length];
        
        for (int i = 0; i<nums.length; i++) 
        {
        	int num = nums[i];
            tempNum[i] = num * num;
        }
        Arrays.sort(tempNum);
        return tempNum;
    }
}