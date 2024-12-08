package Project.DSA;
import java.util.Scanner;

public class CountEvenlyDivisibleDigits {

    // Method to count how many digits in the number divide it evenly
    static int evenlyDivides(int n) {
        int result = 0;
        int tempNum = n;
        
        // Loop through the digits of the number
        while (tempNum > 0) {
            // Get the last digit using modulus
            int digit = tempNum % 10;
            
            // Skip 0 digits to avoid division by zero
            if (digit != 0 && n % digit == 0) {
                result++;
            }
            
            // Remove the last digit by dividing the number by 10
            tempNum = tempNum / 10;
        }
        
        return result;
    }

    // Main method to take user input and test the solution
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int number = 0;
        
        // Taking user input with exception handling
        while (true) {
            try {
                System.out.print("Enter a number: ");
                number = Integer.parseInt(scanner.nextLine()); // Reading user input
                break; // Exit the loop if input is valid
            } catch (NumberFormatException e) {
                // Handle invalid input (non-integer values)
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
        
        // Call the method to calculate how many digits divide the number evenly
        int result = evenlyDivides(number);
        
        // Output the result
        System.out.println("Digits in " + number + " that divide it evenly: " + result);
        
        // Close the scanner
        scanner.close();
    }
}
