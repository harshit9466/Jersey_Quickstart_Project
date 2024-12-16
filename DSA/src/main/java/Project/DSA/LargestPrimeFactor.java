package Project.DSA;

// User function Template for Java

class LargestPrimeFactor {
    static int largestPrimeFactor(int n) 
    {
    	int largestPrimeFactor = n;
    	if(n % 2 == 0)
    	{
    		largestPrimeFactor = 2;
    		n /= 2;
    	}
    	
        for (int i = 3; i <= Math.sqrt(n); i += 2) 
        { // Check odd numbers up to √n
            System.out.println("iteration: "+i);
            while (n % i == 0) {
                largestPrimeFactor = i;
                n /= i;
            }
        }
        
        if (n > 2) {
            largestPrimeFactor = n;
        }
        
        return largestPrimeFactor;
    }
    
    public static void main(String[] args) {
        int n = 543; // Example input
        int result = LargestPrimeFactor.largestPrimeFactor(n);
        System.out.println("The largest prime factor of " + n + " is: " + result);
    }
}