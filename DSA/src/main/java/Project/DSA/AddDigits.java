package Project.DSA;

import java.util.Scanner;

class AddDigits {
	
	//optimal
	public static int addDigitsOptimal(int num) {
	    if (num == 0) return 0;       // Special case for 0
	    return 1 + (num - 1) % 9;     // Digital root formula
	}

	
	//bruteForce
    public static int addDigits(int num) {
        int tempNum = num;
        int result = 0;
        do {
            int digit = tempNum % 10;
            result += digit;
            tempNum = tempNum / 10;

            if (tempNum == 0 && !(result < 10 && result >= 0)) {
                tempNum = result;
                result = 0;
            }
        } while (tempNum != 0);

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a number: ");
            int userInput = scanner.nextInt(); // Takes input from the user

            if (userInput < 0) {
                throw new IllegalArgumentException("Input must be a non-negative integer.");
            }

            // Call the addDigits method
            int result = addDigitsOptimal(userInput);
//            int result = addDigitsOptimal(userInput);

            // Print the result
            System.out.println("The result of adding digits until a single digit is: " + result);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid input. Please enter a valid non-negative integer.");
        } finally {
            scanner.close(); // Close the scanner resource
        }
    }
}
