package Project.DSA;
import java.util.Scanner;

class AllDivisorsOfANumber {
    // Method to print divisors of a number
    public static void print_divisors(int n) {
        if (isPrime(n)) {
            System.out.print(1 + " " + n);
        } else {
            for (int i = 1; i <= n; i++) {
                if (n % i == 0) {
                    System.out.print(i + " ");
                }
            }
        }
    }

    // Method to check if a number is prime
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

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        try {
            // Prompt the user for input
            System.out.print("Enter a number to find its divisors: ");
            int n = scanner.nextInt(); // Read input

            // Print the divisors of the number
            System.out.print("Divisors of " + n + ": ");
            print_divisors(n);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid integer.");
        } finally {
            scanner.close(); // Close the scanner to prevent resource leaks
        }
    }
}
