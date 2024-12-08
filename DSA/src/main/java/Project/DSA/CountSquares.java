package Project.DSA;
import java.util.Scanner; // To take input from the user

class CountSquares {
    // Method to count the number of perfect squares strictly less than n
    static int countSquares(int n) {
        int squareCount = 0;
        // Iterate from 1 to the square root of n (but not including n itself)
        for (int i = 1; i * i < n; i++) {
            squareCount++; // Each value of i will give i^2 which is a perfect square
        }
        return squareCount;
    }

    // Main method to take input and display output
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a scanner object
        
        // Input: Ask the user to enter a number
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();  // Read the input number
        
        // Call countSquares and print the result
        System.out.println("Number of perfect squares less than " + n + ": " + countSquares(n));
        
        scanner.close(); // Close the scanner object to prevent memory leaks
    }
}
