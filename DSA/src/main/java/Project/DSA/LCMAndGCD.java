package Project.DSA;
import java.util.Scanner; // For user input

public class LCMAndGCD {

    // Function to calculate GCD using the Euclidean Algorithm
    public static int findGCD(int a, int b) {
    	
    	System.out.println("a: "+a + ", b: " +b );
        while (b != 0) {       // Repeat until remainder (b) is 0
            int temp = b;      // Save the value of b
            b = a % b;         // Update b as the remainder of a / b
            System.out.println("a: "+a);
            System.out.println("b: "+b);
            a = temp;          // Update a to the previous value of b
            System.out.println();
        }
        return a;              // When b becomes 0, a is the GCD
    }

    // Function to calculate LCM using the formula: LCM(a, b) = (a * b) / GCD(a, b)
    public static int findLCM(int a, int b) {
        return (a * b) / findGCD(a, b); // Compute LCM using GCD
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for input
        
        // Input two numbers
        System.out.print("Enter the first number: ");
        int num1 = scanner.nextInt();
        
        System.out.print("Enter the second number: ");
        int num2 = scanner.nextInt();

        // Compute GCD and LCM
        int gcd = findGCD(num1, num2);
        int lcm = findLCM(num1, num2);

        // Display results
        System.out.println("GCD of " + num1 + " and " + num2 + " is: " + gcd);
        System.out.println("LCM of " + num1 + " and " + num2 + " is: " + lcm);
        
        scanner.close(); // Close the scanner
    }
}
