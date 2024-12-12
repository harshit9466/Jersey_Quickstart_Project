package Project.DSA;

class SumOfAllPrimeNumber
{
    public static long prime_Sum(int n)
    {
    	long result = 0; 
    	for (int i = 2; i <= n; i++) 
    	{
			if(IsPrimeSolution.isPrime(i))
			{
//				System.out.println("i:"+i);
				result += i;
			}
		}
    	return result;
    }
    
    //Optimal
    public static long prime_Sum_Optimal(int n)
    {
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true; // Assume all numbers are prime initially
        }

        // Sieve of Eratosthenes
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false; // Mark multiples as non-prime
                }
            }
        }

        long result = 0; // Use 'long' to avoid overflow
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                result += i;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(prime_Sum(978784)); 
	}
}