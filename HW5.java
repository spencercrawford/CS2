/* Project     : Homework #5
 * Programmer  : Spencer Crawford
 * Date        : 10/2/17
 * Description : Creates a sorted list of words and the displays the number of times each word occurs.
 */
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HW5 {
    // VARIABLES //
    Scanner inputScanner;                                      // scanner for user input
    Scanner fileScanner;                                       // scanner for data in file
    File dataFile;                                             // file to hold data
    String fileName;                                           // name of file to be opened
    String[] wordArray;                                        // array of words in file
    int wordCount;                                             // number of words in the array
    long startTimeWall, endTimeWall, startTimeCPU, endTimeCPU; // start and times for wall clock and cput
    ArrayList listOfWords = new ArrayList<>();                 // array list to hold the words in the file
    LinkedList<String> LLOfWords = new LinkedList<>();         // list  without duplicates
    ArrayList<Integer> duplicates = new ArrayList<>();         // list of the the numbers of duplicates
    ArrayList<String> newList = new ArrayList<>();             // copy of original list for later use

    public static void main(String args[]) { new HW5(); } // end main

    HW5() { // constructor responsible for calling all methods
        promptUser();
        createArray();
        removeChar();
        removeDuplicates();
        printResults();
    } // end constructor

    // this method prompts the user to begin
    public void promptUser() {
        inputScanner = new Scanner(System.in);
        System.out.println("Please enter the name of the file.");
        fileName = inputScanner.next(); // scanner for the file
        dataFile = new File(fileName);  // data file now has the selected name
    } // end promptUser

    // this method creates the array of words in the data file
    public void createArray() {
        try {
            fileScanner = new Scanner(dataFile);     // scanner for the file
            while (fileScanner.hasNext()) {          // while there are still words in the file,
                listOfWords.add(fileScanner.next().toLowerCase()); // add the words in the file to the array list
            } // end for

            wordArray = new String[listOfWords.size()]; // array to hold the words in the array list

            for (int i = 0; i < wordArray.length; i++) {
                wordArray[i] = listOfWords.get(i).toString(); // add the words to the array from the list
            } //end for
            wordCount = wordArray.length; // count the number of words

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not Found!"); // just in case
        } // end catch
    } // end createArray

    // this method removes all special characters from the array
    public String[] removeChar() {

        Pattern pattern = Pattern.compile("[^a-zA-Z']"); // regex for the valid characters
        for (int i = 0; i < wordArray.length; i++) {
            Matcher matcher = pattern.matcher(wordArray[i]);
            wordArray[i] = matcher.replaceAll("");
        } // end for

        return wordArray;

    } // end removeChar

    public void removeDuplicates() { // this method removes all duplicates from the list

        for (int i = 0; i < wordArray.length; i++) { // copy the array into a list
            newList.add(wordArray[i]); }

        Collections.sort(newList); // sort the new list of words

        startTimeWall = System.currentTimeMillis(); // starting wall clock time
        startTimeCPU = System.nanoTime();           // ending cpu time

        for (int i = 1; i < newList.size(); i++) {
            if (!(newList.get(i).equals(newList.get(i-1)))) {
                LLOfWords.add(newList.get(i)); // creates a list without duplicates
                duplicates.add(Collections.frequency(newList, newList.get(i))); } // creates list of duplicates numbers
        }
        endTimeWall = System.currentTimeMillis() - startTimeWall; // end time for wall clock time
        endTimeCPU  = System.nanoTime() - startTimeCPU;           // end time for cpu time
    }

    public void printResults() { // this method prints the results of the sorting and clock times

        for (int i = 0; i < LLOfWords.size(); i++) {
            System.out.println(LLOfWords.get(i) + " " + duplicates.get(i));
        } // end for
        System.out.println("\nFile Name: " + fileName);
        System.out.println("Number of words: " + wordCount);
        System.out.println("\nWall Clock:\n" + endTimeWall + "ms\n");
        System.out.println("CPU Time:\n" + endTimeCPU + "ns");

    } // end printResults
} // end HW5
