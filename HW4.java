/* Programmer  : Spencer Crawford
 * Project     : Homework #4
 * Date        : 9/17/17
 * Description : Test time and two sorts, BubbleSort and the built-in Java sort
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HW4 {

    // VARIABLES //
    Scanner inputScanner;          // scanner for user input
    Scanner fileScanner;           // scanner for data in file
    File dataFile;                 // file to hold data
    String fileName;               // name of file to be opened
    ArrayList<String> listOfWords; // list of words in file to be placed in array list
    String[] wordArray;            // array of words in file
    String[] wordArray2;           // duplicate of the word array for later use
    int wordCount;                 // number of words in file
    long startTimeWallJava, endTimeWallJava, startTimeCPUjava, endTimeCPUjava, startTimeWallBubble, endTimeWallBubble,
            startTimeCPUBubble, endTimeCPUBubble; // all start and end times for wall clock and CPU time

    public static void main(String[] args) {
        new HW4();
    } // end main

    HW4() {
        promptUser();   // call the prompt user method
        createArray();  // call the create array method
        removeChar();   // call the remove special character method
        duplicate();    // calls the method to duplicate the array
        bubbleSort();   // call the bubble sort method
        javaSort();     // call the java sort method
        printResults(); // call the print results method
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
        listOfWords = new ArrayList<String>(); // array list to hold the words in the file
        try {
            fileScanner = new Scanner(dataFile); // scanner for the file
                while (fileScanner.hasNext()) {
                    listOfWords.add(fileScanner.next()); // add the words in the file to the array list
                } // end for
            wordArray = new String[listOfWords.size()]; // array to hold the words in the array list
            for (int i = 0; i < wordArray.length; i++) {
                wordArray[i] = listOfWords.get(i).toString(); // add the words to the array from the array list
            } //end for
            wordCount = wordArray.length; // count the number of words
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not Found!"); // just in case
        } // end catch
    } // end createArray

    // this method removes all special characters from the array
    public String[] removeChar() {
        Pattern pattern = Pattern.compile("[^a-zA-Z]"); // regex for the valid characters
        for (int i = 0; i < wordArray.length; i++) {
            Matcher matcher = pattern.matcher(wordArray[i]);
            wordArray[i] = matcher.replaceAll("");
        } // end for
        return wordArray;
    } // end removeChar

    // this method creates a duplicate of the array of words
    public void duplicate() {
        wordArray2 = new String[wordArray.length];
        for (int i = 0; i < wordArray.length; i++) {
            wordArray2[i] = wordArray[i];
        } // end for
    } // end duplicate

    // this method uses bubble sort to alphabetize the array
    public String[] bubbleSort() {
        startTimeWallBubble = System.currentTimeMillis(); // starting wall clock time
        startTimeCPUBubble = System.nanoTime();           // ending cpu time
        String temp;
        for (int i = 0; i < wordArray.length-1; i++) {
            for (int j = 0; j < wordArray.length-1-i; j++) {
                if (wordArray[j].compareTo(wordArray[j+1]) > 0) {
                    temp = wordArray[j];
                    wordArray[j] = wordArray[j+1];
                    wordArray[j+1] = temp;
                } // end if
            } // end for
        } // end for
        endTimeWallBubble = System.currentTimeMillis() - startTimeWallBubble; // ending wall clock time
        endTimeCPUBubble = System.nanoTime() - startTimeCPUBubble;            // ending cpu time
        return wordArray;
    } // end bubbleSort

    // this method uses java sort to alphabetize the array
    public void javaSort() {
        startTimeWallJava = System.currentTimeMillis(); // starting wall clock time
        startTimeCPUjava = System.nanoTime();           // stating cpu time
        Arrays.sort(wordArray2);
        endTimeWallJava = System.currentTimeMillis() - startTimeWallJava; // ending wall clock time
        endTimeCPUjava = System.nanoTime() - startTimeCPUjava;            // ending cpu time
    } // end javaSort

    // this method prints the results of the sorting times and the word count
    public void printResults() {
        System.out.println("File Name: " + fileName);
        System.out.println("Number of Words: " + wordCount);
        System.out.println("Wall Clock: \n");
        System.out.println("Java Sort: " + endTimeWallJava + "ms\n" + "Bubble Sort: " + endTimeWallBubble + "ms\n");
        System.out.println("CPU Time: \n");
        System.out.println("Java Sort: " + endTimeCPUjava + "ns\n" + "Bubble Sort: " + endTimeCPUBubble + "ns");
    } // end printResults
} // end HW4 Class