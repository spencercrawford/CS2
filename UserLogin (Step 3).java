/*
 * Programmer  : Spencer Crawford
 * Project     : Homework #1 CS2050-001
 * Date        : 9/2/17
 * Description : This program evaluates user login for valid requirements
 */

import java.util.Scanner;             // imports the Scanner utility for user input.
import java.io.File;                  // import the file utility
import java.io.FileNotFoundException; // import the exception for the file not being found
import java.io.PrintWriter;           // import the print writer utility

public class UserLogin {

    // VARIABLES //
    private Scanner inputScanner = new Scanner(System.in); // scanner for user input
    private String inputString; // holds user input
    private boolean upperCase = false; // determines inclusion of uppercase letters
    private boolean lowerCase = false; // determines inclusion of lowercase letters
    private boolean hasLength = false; // determines length of five characters
    private boolean specChar  = false; // determines inclusion of the following: ! @ # $
    private boolean hasDigit  = false; // determines inclusion of numerical digits
    private boolean otherChar = true;  // determines inclusion of invalid characters such as: % ^ & *
    private boolean hasSpace  = true;  // determines inclusion of space or tab
    private String specCharString = "!@#$"; // valid special characters
    private String numString      = "1234567890"; // valid numerical characters
    private String allCharString  = "!@#$1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // all valid
    private String userReport;  // holds the current UserLogin report
    private String finalReport; // holds the final UserLogin report
    private File reportFile = new File("Report File"); // file that holds the final report

    public static void main(String[] args) throws FileNotFoundException {
        /* this is the main method, and is responsible for calling all other methods.
         */
        UserLogin userLogin = new UserLogin();
        // this is the user Login variable that calls the methods

        userLogin.greetUser();
        // calls the greetUser method
        userLogin.readUserLoginFromUser();
        // calls the readUserLoginFromUser method
        userLogin.checkCase();
        // calls the checkCase method
        userLogin.checkLength();
        // calls the checkLength method
        userLogin.checkSpecialCharacter();
        // calls the checkSpecialCharacter method
        userLogin.checkDigit();
        // calls the checkDigit method
        userLogin.checkOtherInvalidCharacter();
        // calls the checkOtherInvalidCharacter method
        userLogin.checkSpace();
        // calls the checkSpace method
        userLogin.checkValidity();
        // calls the check Validity method
        userLogin.printUserLoginValidity();
        // calls the printUserLoginValidity method
        userLogin.addToReport();
        // calls the addToReport method
        userLogin.printReport();
        // calls the printReport method
    } // end main

    private void greetUser() {
        /* this method greets and prompts the user to enter a login.
         */
    } // end greetUser

    private void readUserLoginFromUser() {
        /* this method checks for input from the user, and stores user input in a string.
         */
        inputString = inputScanner.nextLine(); // scans the user input
        System.out.println("Login: " + inputString);
    } // end readUserLoginFromUser

    private void checkCase() {
        /* this method searches the user input for both uppercase and lowercase letters.
         * if the input contains at least one uppercase letter, then boolean upperCase is TRUE.
         * if the input contains at least one lowercase letter, then boolean lowerCase is TRUE.
         */
        System.out.println("upperCase: " + upperCase);
        System.out.println("lowerCase :" +lowerCase);
    } // end checkCase

    private void checkLength() {
        /* this method searches the user input for a length of five characters or more
         * if the length of the user input is at least five characters, then the boolean hasLength is TRUE.
         */
        System.out.println("hasLength: " + hasLength);
    } // end checkLength
    private void checkSpecialCharacter() {
        /* this method searches the user input for the inclusion of at least one of the following: ! @ # $
         *      if the input is ok, then the boolean specChar is TRUE.
         */
        System.out.println("specChar: " + specChar);
    } // end CheckSpecialCharacter

    private void checkDigit() {
        /* this method searches the user input for the inclusion of at least one numerical digit
         *      if the input is ok, then the boolean hasDigit is TRUE.
         */
        System.out.println("hasDigit: " + hasDigit);
    } // end checkDigit

    private void checkOtherInvalidCharacter() {
        /* this method searches the user input for the inclusion of invalid special characters such as: % ^ & *
         *      if the input is ok, then the boolean otherChar is FALSE.
         */
        System.out.println("OtherChar: " + otherChar);
    } // end checkOtherInvalidCharacter

    private void checkSpace() {
        /* this method searches the user input for the inclusion of a space or tab
         *      if the input is ok, then the boolean hasSpace is false
         */
        System.out.println("HasSpace: " + hasSpace);
    } // end hasSpace

    private void checkValidity() {
        /* this method checks all the booleans for input validity, and prints a report of the login status
         * if the input is ok, then print "Login: + user input + (Valid)"
         * if the input is not ok, then print "Login: + user input + (Invalid)"
         * it then prints the reasons why the input is not valid, based on the status of the related booleans.
         */
    } // end checkValidity

    private void printUserLoginValidity() {
        /* this method print the user report based on the login validity
         */
    } // end printUserLoginValidity

    private void addToReport() {
        /* this method adds the current user report to the final report
         */
    } // end addToReport

    private  void printReport() throws FileNotFoundException {
        /* this method creates a file called reportFile, and then saves the userReport to it.
         */
    } // end printReport
} // end class
