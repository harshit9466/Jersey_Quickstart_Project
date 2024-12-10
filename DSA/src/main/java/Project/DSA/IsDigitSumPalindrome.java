package Project.DSA;

class IsDigitSumPalindrome {
	static int isDigitSumPalindrome(int n) {
		int tempNum = n;
		int sum = 0;

		// Calculate the sum of digits
		while (tempNum > 0) {
			int digit = tempNum % 10;
			sum += digit;
			tempNum = tempNum / 10;
		}

		StringBuilder sumString = new StringBuilder(String.valueOf(sum));
		int length = sumString.length();

		// Handle odd-length strings
		int halfLength = length / 2;
		StringBuilder firstHalf = new StringBuilder(sumString.substring(0, halfLength));
		StringBuilder secondHalf;

		if (length % 2 == 0) {
			secondHalf = new StringBuilder(sumString.substring(halfLength, length));
		} else {
			secondHalf = new StringBuilder(sumString.substring(halfLength + 1, length));
		}

		StringBuilder revSecondHalf = secondHalf.reverse();

		if (firstHalf.toString().equals(revSecondHalf.toString())) {
			return 1; // Symmetric
		} else {
			return 0; // Not symmetric
		}
	}

	public static void main(String[] args) {
		int result = isDigitSumPalindrome(56);
		System.out.println("Reversed Equation: " + result);
	}
}