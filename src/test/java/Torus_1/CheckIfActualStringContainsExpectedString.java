package Torus_1;

public class CheckIfActualStringContainsExpectedString {
	public static void main(String[] args) {
		// Input string
		/*
		 * String input = "697GR2034 " + "\n" + "₹100.70" + "\n" +
		 * " NSE -5.30 (-5.00%)";
		 * 
		 * // Split the string based on \n String[] parts = input.split("\\n");
		 * 
		 * // Fetch the first value if (parts.length > 0) { String firstValue =
		 * parts[0].trim(); // Trim to remove any leading or trailing spaces
		 * System.out.println("First value: " + firstValue); } else {
		 * System.out.println("No values found in the input string."); }
		 */

		String a = "basavaraj has not applied for scholarship";
		String b = "Applied";
		boolean caseSensitive = true;

		boolean c;
		
		if(caseSensitive==true) {
			if (a.contains(b)) {
				c = true;
			} else {
				c = false;
			}
			System.out.println(c);
		}
		
		else {
			
			// Convert both strings to lower case and check contains
			if (a.toLowerCase().contains(b.toLowerCase())) {
				c = true;
			} else {
				c = false;
			}

			System.out.println(c);
		}
	
	}
}
