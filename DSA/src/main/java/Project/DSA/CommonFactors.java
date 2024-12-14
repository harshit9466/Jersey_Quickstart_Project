package Project.DSA;

import java.util.ArrayList;

class CommonFactors {
	
	public int commonFactorsOptimal(int a, int b) {
        int count = 0;
        int limit = Math.min(a, b); // Only iterate up to the smaller of a and b

        for (int i = 1; i <= limit; i++) {
            if (a % i == 0 && b % i == 0) { // Check if i is a factor of both a and b
                count++;
            }
        }

        return count;
    }
	
    public int commonFactors(int a, int b)
    {
    	ArrayList<Integer> comfacA = new ArrayList<>();
    	ArrayList<Integer> comfacB = new ArrayList<>();
    	
        for (int i = 1; i <= a; i++) { // Check odd numbers up to √n
            if (a % i == 0) {
                comfacA.add(i);// a is divisible by i
            }
        }
        
        for (int i = 1; i <= b; i++) { // Check odd numbers up to √n
            if (b % i == 0) {
                comfacB.add(i);// a is divisible by i
            }
        }
        
        ArrayList<Integer> commonElements = new ArrayList<>(comfacA); // Create a copy of comfacA
        commonElements.retainAll(comfacB); // Retain only the common elements

        System.out.println("Common elements: " + commonElements);
    	
        return commonElements.size();
    }
}