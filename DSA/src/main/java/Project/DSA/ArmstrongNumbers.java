package Project.DSA;
import java.util.Scanner;

public class ArmstrongNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the number of test cases
        System.out.print("Enter the number of test cases: ");
        int t;
        try {
            t = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter an integer.");
            return;
        }

        // Loop through the test cases
        while (t-- > 0) {
            System.out.print("Enter a number to check if it's an Armstrong number: ");
            int n;
            try {
                n = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            // Create an object of the Solution class
            ArmstrongNumbersSolution ob = new ArmstrongNumbersSolution();

            // Check if the number is an Armstrong number
            boolean flag = ob.armstrongNumber(n);

            // Output the result
            if (flag) {
                System.out.println(n + " is an Armstrong number.");
            } else {
                System.out.println(n + " is not an Armstrong number.");
            }

            // Optional separator
            System.out.println("~");
        }

        // Close the scanner
        scanner.close();
    }
}

// User function template for Java
class ArmstrongNumbersSolution {
    // Method to check if a number is an Armstrong number
    static boolean armstrongNumber(int n) {
        int result = 0;
        int tempNum = n;

        // Calculate the sum of cubes of digits
        while (tempNum > 0) {
            int digit = tempNum % 10;
            result += digit * digit * digit;
            tempNum = tempNum / 10;
        }

        // Check if the result matches the original number
        return result == n;
    }
}
