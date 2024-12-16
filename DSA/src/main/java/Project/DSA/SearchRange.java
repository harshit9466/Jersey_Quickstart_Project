package Project.DSA;

class SearchRange
{
	
	public int[] searchRangeOptimal(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int first = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                first = mid;
                end = mid - 1; // Keep searching in the left half
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return first;
    }

    private int findLast(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int last = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                last = mid;
                start = mid + 1; // Keep searching in the right half
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return last;
    }
	
	public int[] searchRange(int[] nums, int target)
	{
		int result[] = new int[2];
		int firstOccurance = -1;
		int lastOccurance = -1;
		
		for (int i = 0; i < nums.length; i++)
		{
			if (nums[i] == target)
			{
				if(firstOccurance == -1)
				{
					firstOccurance = i;
				}
				lastOccurance = i;
			}
		}
		result[0] = firstOccurance;
		result[1] = lastOccurance;
		return result;

	}
}