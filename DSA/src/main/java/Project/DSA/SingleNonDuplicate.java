package Project.DSA;

class SingleNonDuplicate
{
	public int singleNonDuplicate(int[] nums)
	{
		int low = 0, high = nums.length - 1;

		while(low < high)
		{
			int mid = low + (high - low) / 2;

			// Check the relationship between mid and its neighbor based on even/odd index
			if(mid % 2 == 0)
			{
				// Even index: Compare with the next element
				if(nums[mid] == nums[mid + 1])
				{
					low = mid + 1; // Move right
				}
				else
				{
					high = mid; // Move left
				}
			}
			else
			{
				// Odd index: Compare with the previous element
				if(nums[mid] == nums[mid - 1])
				{
					low = mid + 1; // Move right
				}
				else
				{
					high = mid; // Move left
				}
			}
		}

		// Single element is at the position where low == high
		return nums[low];
	}
}
