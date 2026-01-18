//package coding1;
//
//import java.util.Scanner;
//
//public class SwfitCodeValidation {
//
//	static Scanner sc = new Scanner(System.in);
//	public static void main(String args[]) {
//		System.out.print("enter the SwiftCode: ");
//		String swiftCode = sc.nextLine();
//		boolean isValid = validateLength(swiftCode);
//		
//		if(isValid) {
//			String institutionCode = swiftCode.substring(0, 4);
//			boolean status = isAlpha(institutionCode);
//			if(status) {
//				String countryCode = swiftCode.substring(4, 6);
//				boolean validStatus = isAlpha(countryCode);
//				if(validStatus) {
//					String locationCode = swiftCode.substring(6, 8);
//					validStatus = isAlphanumeric(locationCode);
//					if(validStatus) {
//						if(swiftCode.length()==11) {
//							String code = swiftCode.substring(8, 11);
//							if(!code.equalsIgnoreCase("xxx")) {
//								validStatus = isAlphanumeric(code);
//								if(validStatus) {
//									System.out.println("Input: "+swiftCode+" is VALID");
//								}else {
//									System.err.println("Input: "+swiftCode+" is INVALID: Branch Code contains invalid characters");
//								}
//							}
//						}
//					}else {
//						System.err.println("Input: "+swiftCode+" is INVALID: Location Code contains invalid characters");
//					}
//				}else {
//					System.err.println("Input: "+swiftCode+ "is INVALID: Country Code must be alphabetic" );
//				}
//			}
//			else {
//				System.err.println("Input: "+swiftCode+" is INVALID: Institution Code must be alphabetic.");
//			}
//		}
//		else {
//			System.err.println("Input: "+swiftCode +" is  INVALID: Invalid Length");
//		}
//	}
//	
//	
//	public static boolean validateLength(String code) {
//		if(code.length()==8 || code.length()==11){
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	public static  boolean isAlphanumeric(String code) {
//		String pattern = "^[a-zA-Z0-9]+$";
//		return code.matches(pattern);
//	}
//	
//	public static boolean isAlpha(String code) {
//		String pattern = "^[a-zA-Z]+$";
//		return code.matches(pattern);
//	}
//	
//	
//	
//}
package coding1;

import java.util.Scanner;

public class SwfitCodeValidation {

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        System.out.print("Enter the SwiftCode: ");
        String swiftCode = sc.nextLine();

        if (validateLength(swiftCode)) {
            String institutionCode = swiftCode.substring(0, 4);
            if (isAlpha(institutionCode)) {
                String countryCode = swiftCode.substring(4, 6);
                if (isAlpha(countryCode)) {
                    String locationCode = swiftCode.substring(6, 8);
                    if (isAlphanumeric(locationCode)) {
                        if (swiftCode.length() == 11) {
                            String branchCode = swiftCode.substring(8, 11);
                            if (!branchCode.equalsIgnoreCase("xxx")) {
                                if (isAlphanumeric(branchCode)) {
                                    System.out.println("Input: " + swiftCode + " is VALID");
                                } else {
                                    System.err.println("Input: " + swiftCode + " is INVALID: Branch Code contains invalid characters");
                                }
                            } else {
                                System.out.println("Input: " + swiftCode + " is VALID");
                            }
                        } else {
                            System.out.println("Input: " + swiftCode + " is VALID");
                        }
                    } else {
                        System.err.println("Input: " + swiftCode + " is INVALID: Location Code contains invalid characters");
                    }
                } else {
                    System.err.println("Input: " + swiftCode + " is INVALID: Country Code must be alphabetic");
                }
            } else {
                System.err.println("Input: " + swiftCode + " is INVALID: Institution Code must be alphabetic");
            }
        } else {
            System.err.println("Input: " + swiftCode + " is INVALID: Invalid Length");
        }
    }

    // Validate the length of the Swift Code (8 or 11 characters)
    public static boolean validateLength(String code) {
        return code.length() == 8 || code.length() == 11;
    }

    // Check if the code contains only alphabetic characters
    public static boolean isAlpha(String code) {
        return code.matches("^[a-zA-Z]+$");
    }

    // Check if the code contains only alphanumeric characters
    public static boolean isAlphanumeric(String code) {
        return code.matches("^[a-zA-Z0-9]+$");
    }
    
    public static boolean checkAlphaNumeric(String code) {
    	for(int i=0;i<code.length();i++) {
    		if(!(Character.isLetterOrDigit(code.charAt(i)))){
    			return false;
    		}
    	}
    	return true;
    }
    public static boolean checkAlpha(String code) {
    	for(int i=0;i<code.length();i++) {
    		if(!(Character.isLetter(code.charAt(i)))){
    			return false;
    		}
    	}
    	return true;
    }
}


