package coding1;

import java.util.Scanner;

public class SwiftCodeValidation {

	static Scanner sc = new Scanner(System.in);

	public static void main(String args[]) {
		System.out.print("Enter the SwiftCode: ");
		String swiftCode = sc.nextLine();
		System.out.println(validateSwiftCode(swiftCode));

	}

	private static String validateSwiftCode(String code) {
		if (code.length() != 8 || code.length() != 11) {
			return "Input: " + code + " is INVALID: Invalid Length";
		}
		for (int i = 0; i < 4; i++) {
			if (!(Character.isLetter(code.charAt(i)))) {
				return "Input: " + code + " is INVALID: Institution Code must be alphabetic";
			}
		}
		for (int i = 4; i < 6; i++) {
			if (!(Character.isLetter(code.charAt(i)))) {
				return "Input: " + code + " is INVALID: Country Code must be alphabetic";
			}
		}
		for (int i = 6; i < 8; i++) {
			if (!(Character.isLetter(code.charAt(i)))) {
				return "Input: " + code + " is INVALID: Location Code contains invalid characters";
			}
		}
		if (code.length() == 11) {
			for (int i = 8; i < 11; i++) {
				if (!(Character.isLetterOrDigit(code.charAt(i)))) {
					return "Input: " + code + " is INVALID: Branch Code contains invalid characters";
				}
			}
		}
		return "Input: " + code + " is VALID";

	}

}
