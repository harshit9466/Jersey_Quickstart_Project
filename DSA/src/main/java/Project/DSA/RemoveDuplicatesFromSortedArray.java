package Project.DSA;

import java.util.ArrayList;

public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
    	
    	//1st------------------------------------------------------------------------
//        // Convert primitive array to ArrayList
//        ArrayList<Integer> arrayList = new ArrayList<>();
//    	
//    	for(int i = 0; i<nums.length; i++)
//    	{
//    		if(!arrayList.contains(nums[i]))
//    		{
//    			arrayList.add(nums[i]);
//    		}
//    	}
//    	System.out.println("result: "+arrayList.toString());
//    	
//    	
//		return arrayList.size();

    	//2nd------------------------------------------------------------------------
//        // Convert primitive array to ArrayList
//        ArrayList<Integer> arrayList = new ArrayList<>();
//    	
//    	for (int i = 0; i < nums.length; i++) {
//    		if (!arrayList.contains(nums[i])) {
//    			arrayList.add(nums[i]);
//    		}
//    	}
//    	
//    	// Copy the elements from ArrayList back to nums array
//    	for (int i = 0; i < arrayList.size(); i++) {
//    		nums[i] = arrayList.get(i); // Copy unique elements back to nums
//    	}
//    	
//    	// Print updated nums array
//    	System.out.print("Updated nums: ");
//    	for (int i = 0; i < arrayList.size(); i++) {
//    	    System.out.print(nums[i] + " ");
//    	}
//    	System.out.println();
//    	
//    	// Return the new size of the nums array (number of unique elements)
//    	return arrayList.size();
        
    	//3rd---------------------------------------------------------------------
        // Edge case: if the array is empty, return 0
        if (nums.length == 0) return 0;

        int j = 0; // This will track the position of the last unique element

        // Loop through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // If the current element is different from the last unique element
            if (nums[i] != nums[j]) {
                j++;  // Move the unique position pointer forward
                nums[j] = nums[i]; // Update the unique position with the new unique element
            }
        }

        // Return the number of unique elements, which is j + 1
        return j + 1;
    }

}
