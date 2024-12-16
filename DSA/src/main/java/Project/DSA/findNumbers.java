package Project.DSA;

class findNumbers {
    public int findNumbers(int[] nums) {
    	int result = 0;
    	
        for (int i = 0; i < nums.length; i++) {
			int tempNum = nums[i];
			int digitNum = 0;
			while (tempNum > 0)
			{
				int digit = tempNum % 10;
				tempNum = tempNum / 10;
				digitNum++;
			}
			
			if(digitNum % 2 == 0)
			{
				result++;
			}
		}
    	
    	return result;
    }
}