package Project.DSA;
import java.util.Stack;

class ReverseEquation {

    public static void main(String[] args) {
        String result = reverseEqn("20-3+5*2");
        System.out.println("Reversed Equation: " + result);
    }

    static String reverseEqn(String S) {
        // Use stacks to store numbers and operators
        Stack<String> elements = new Stack<>();

        StringBuilder currentNum = new StringBuilder();

        // Parse the equation
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            if (Character.isDigit(ch)) {
                // Build multi-digit numbers
                currentNum.append(ch);
            } else {
                // Push the completed number and the operator
                if (currentNum.length() > 0) {
                    elements.push(currentNum.toString());
                    currentNum.setLength(0); // Reset for next number
                }
                elements.push(String.valueOf(ch));
            }
        }

        // Push the last number if there is any
        if (currentNum.length() > 0) {
            elements.push(currentNum.toString());
        }

        // Reconstruct the reversed equation
        StringBuilder result = new StringBuilder();
        while (!elements.isEmpty()) {
            result.append(elements.pop());
        }

        return result.toString();
    }
}
