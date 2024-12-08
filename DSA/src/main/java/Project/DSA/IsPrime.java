package Project.DSA;
import java.io.*; // For BufferedReader and IOException

public class IsPrime {
    public static void main(String args[]) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter number of test cases:");
            int t = Integer.parseInt(read.readLine()); // Read number of test cases
            
            while (t-- > 0) {
                try {
                    System.out.println("Enter a number to check if it's prime:");
                    int n = Integer.parseInt(read.readLine()); // Read the number to check primality
                    Solution ob = new Solution();
                    
                    if (ob.isPrime(n)) {
                        System.out.println("true"); // If the number is prime, print "true"
                    } else {
                        System.out.println("false"); // If the number is not prime, print "false"
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                }
                System.out.println("~"); // Separator for clarity between test cases
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for test cases. Please enter a valid integer.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
        }
    }
}

class Solution {
    static boolean isPrime(int n) {
        if (n <= 1) {
            return false; // 0 and 1 are not prime numbers
        }
        if (n == 2) {
            return true; // 2 is the only even prime number
        }
        if (n % 2 == 0) {
            return false; // Any even number other than 2 is not prime
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) { // Check odd numbers up to √n
            if (n % i == 0) {
                return false; // n is divisible by i, so it's not prime
            }
        }
        return true; // If no divisors are found, n is prime
    }
}
