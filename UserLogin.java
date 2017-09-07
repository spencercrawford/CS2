import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class UserLogin {
    private Scanner inputScanner = new Scanner(System.in);                  // scanner for user input
    private File reportFile = new File("Report File");            // file for saving all logins
    private PrintWriter printToFile = new PrintWriter(reportFile);          // print writer for file
    private String inputString;                                             // string for user input
    private String report;                                                  // string for user input
    private boolean upperCase = false; // determines inclusion of uppercase letters
    private boolean lowerCase = false; // determines inclusion of lowercase letters
    private boolean hasLength = false; // determines length of five characters
    private boolean specChar  = false; // determines inclusion of the following: ! @ # $
    private boolean hasDigit  = false; // determines inclusion of numerical digits
    private boolean otherChar = true;  // determines inclusion of invalid characters such as: % ^ & *
    private boolean hasSpace  = true;  // determines inclusion of space or tab
    private boolean isValid   = false; // determines whether login was valid or not
    private boolean moreInput = false; // determines if there is more input to be evaluated

    UserLogin() throws FileNotFoundException {
        printToFile.write("Spencer Crawford\nHomeWork #1 CS2015-001\n\nAll User Logins:\n"); // personal header
        do {
            greetUser();
            readUserLoginFromUser();
            checkCase();
            checkLength();
            checkSpecialCharacter();
            checkDigit();
            checkOtherInvalidCharacter();
            checkSpace();
            checkValidity();
            printUserLoginValidity();
            addToReport();
            printReport();
            System.out.println("More Users? Enter 'Yes' to continue, enter 'No' to exit.");
            if (inputScanner.nextLine().toUpperCase().equals("YES")) {
                moreInput = true;
            } else {
                printToFile.close();
                System.exit(0);
            } // end if
        } while (moreInput);
    } // end constructor

    public static void main(String[] args) throws FileNotFoundException {
        // this is the main method, and determines if there is more input to be evaluated
        new UserLogin();
    } // end main

    private void greetUser() {
        // this method greets and prompts the user
        System.out.println("Welcome! Please enter your password.");
    } // end greetUser

    private void readUserLoginFromUser() {
        // this method reads input from the user and stores it in a string
            inputString = inputScanner.nextLine();
    } // end readUserLoginFromUser

    private void checkCase() {
        // this method determines the inclusion of uppercase and lowercase letters
        // this nested loop determines the inclusion of uppercase letters
        upperCase = false;
        lowerCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) >= 'A' && inputString.charAt(i) <= 'Z') {
                upperCase = true;
            } // end if
        } // end for

        // this nested loop determines the inclusion of lowercase letters
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) >= 'a' && inputString.charAt(i) <= 'z') {
                lowerCase = true;
            } // end if
        } // end for
    } // end checkCase

    private void checkLength() {
        // this method determines if the input is at least 5 characters
        hasSpace = false;
        if (inputString.length() >= 5) {
            hasLength = true;
        } else {
            hasLength = false;
        }
    } // end checkLength

    private void checkSpecialCharacter() {
        // this method determines the inclusion of at least one of the valid special characters
        specChar = false;
        if (inputString.contains("!")||inputString.contains("@")||inputString.contains("#")||inputString.contains("$"))
        { specChar = true;
        } // end if
    } // end checkSpecialCharacter

    private void checkDigit() {
        // this method determines the inclusion of at least one numerical digit
        hasDigit = false;
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9') {
                hasDigit = true;
            } // end if
        } // end for
    } // end checkDigit

    private void checkOtherInvalidCharacter() {
        otherChar = true;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9!@#$]+$");
        Matcher matcher = pattern.matcher(inputString); // Your String should come here
        if(!matcher.find()) {
            otherChar = true;
        } else {
            otherChar = false;
        }
    } // end checkOtherInvalidCharacter

    private void checkSpace() {
        hasSpace = true;
        // this statement determines the inclusion of a space or tab
        if (!inputString.contains(" ")) {
            hasSpace = false;
        } // end if
    } // end checkSpace

    private void checkValidity() {
        // this method determines whether or not the login was valid
        isValid = false;
        if (upperCase && lowerCase && hasLength && specChar && hasDigit && !otherChar && !hasSpace) {
            isValid = true;
        } else {
            isValid = false;
        } // end end if
    } // end checkValidity

    private void printUserLoginValidity() {
        report = "";
        if (isValid) {
            report = "\n\nLogin: " + inputString + " (Valid)";
        } else {
            report = "\n\nLogin: " + inputString + " (Invalid)";
        } // end end if
    } // end printUserLoginValidity

    private void addToReport() {
        // if login was invalid, this method prints the reasons why it was invalid
        if (!upperCase) { // if no uppercase letters
            report += ("\nPlease include least one uppercase letter");
        } // end if
        if (!lowerCase) { // if no lowercase letters
            report += ("\nPlease include least one lowercase letter");
        } // end if
        if (!hasLength) { // if less than five characters
            report += ("\nPlease include at least five characters");
        } // end if
        if (!specChar) { // if no valid special characters
            report += ("\nPlease include at least one of the following: ! @ # $");
        } // end if
        if (!hasDigit) { // if no numerical digit
            report += ("\nPlease include at least one numerical digit");
        } // end if
        if (otherChar) { // if invalid special characters
            report += ("\nYou have included an invalid character");
        } // end if
        if (hasSpace) { // if space or tab
            report += ("\nyou have included a space or tab");
        } // end if
        System.out.println(report);
    } // end addToReport

    private void printReport() {
        // this method prints the list of logins to a file
        printToFile.write(report);
    } // end printReport
} // end class