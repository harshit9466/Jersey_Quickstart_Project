package Project.DSA;

class FindPeakElement
{
	public int findPeakElement(int[] nums)
	{
		int low = 0, high = nums.length - 1;

		while(low < high)
		{
			int mid = (low + high) / 2;

			// Check if mid is a peak
			boolean leftCheck = (mid == 0 || nums[mid] > nums[mid - 1]);
			boolean rightCheck = (mid == nums.length - 1 || nums[mid] > nums[mid + 1]);

			if(leftCheck && rightCheck)
			{
				return mid; // Mid is a peak
			}

			// Decide which half to explore
			if(nums[mid] < nums[mid + 1])
			{
				low = mid + 1; // Move right
			}
			else
			{
				high = mid - 1; // Move left
			}
		}

		return low; // or high
	}
}