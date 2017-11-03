/* Project : Homework #7
 * Programmer : Spencer Crawford
 * Date : 11/3/17
 * Description : Uses a HashTable to index and search for words in a file
 */
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/** The HW7 Class filters unique words from a file, and determines the number of unique words. A Hash Table those words
 *  is then created, and the user is given the option to search for words in the Hash Table. **/
public class HW7 {
    // VARIABLES //
    File wordFile; // file containing the data
    String[] wordArray; // the final array of words
    String[] tempWordArray; // a temporary array of words
    ArrayList<String> wordArrayList = new ArrayList<String>(); // the final array list of words
    ArrayList<String> tempWordArrayList = new ArrayList<String>(); // a temporary array list of words
    int numberOfWords = 0; // the final number of words
    int tempNumberOfWords = 0; // a temporary number of words
    HashTable ht; // the hash table of word-index pairs
    Scanner in = new Scanner(System.in); // scanner for user input
    Scanner fileScanner; // scanner for the word file

    /** Main Method **/
    public static void main(String args[]){ new HW7();}

    /** Constructor responsible for calling each Function **/
    HW7() {
        System.out.println("Please enter the name of the file"); // prompt the user to begin
        createFile(in.next()); // create the file
        createArray(); // create the initial array
        removeSpecialChar(); // remove special characters from words in array
        removeDuplicates(); // remove all duplicate words from array
        createHashTable(); // create hash table of words in array
        direct(); // direct what the user wants to do next
    } // end constructor

    /** Method for creating the data File **/
    public void createFile(String fileName) {
        try {
            wordFile = new File(fileName);
            fileScanner = new Scanner(wordFile); // create a scanner for the file
        } catch (FileNotFoundException fnfe) {
            System.out.println("Please enter a valid file name!");
            createFile(in.next());
        } // end catch
    } // end promptUser

    /** Method for creating the initial Array **/
    public void createArray() {
            while (fileScanner.hasNext()) { // while there are still words in the file
                tempWordArrayList.add(fileScanner.next().toLowerCase()); // add those words to a temp ArrayList
                tempNumberOfWords++;
            } // end for

            tempWordArray = new String[tempNumberOfWords]; // create a temp array

            for(int i = 0; i < tempNumberOfWords; i++) {
                tempWordArray[i] = tempWordArrayList.get(i); // add the words from the temp ArrayList
            } // end for
    } // end createArray

    /** Method for removing special characters from words **/
    public void removeSpecialChar() {
        Pattern pattern = Pattern.compile("[^a-zA-Z']"); // regex for the valid characters
        for (int i = 0; i < tempWordArray.length; i++) {
            Matcher matcher = pattern.matcher(tempWordArray[i]);
            tempWordArray[i] = matcher.replaceAll(""); // remove special characters from the temp array
        } // end for
    } // end removeSpecialChar

    /** Methos for removing duplicate words from the Array **/
    public void removeDuplicates() {
        Arrays.sort(tempWordArray);

        for (int i = 1; i < tempWordArray.length; i++) {
            if (!(tempWordArray[i].equals(tempWordArray[i-1]))) { // if the last word is not a duplicate
                wordArrayList.add(tempWordArray[i]); // add the word to the final ArrayList
                numberOfWords++; // increase the final number of words
            } // end if
        } // end for

        wordArray = new String[numberOfWords]; // create the final array of words
        for (int i = 0; i < wordArrayList.size(); i++) {
            wordArray[i] = wordArrayList.get(i);
        } // end for
    } // end removeDuplicates

    /** Method for creating the Hash Table **/
    public void createHashTable() {
        System.out.println("The number of unique words in the file is: " + numberOfWords);
        System.out.println("Please enter a prime number for the hash table size");
        int potentialSize = in.nextInt();
        ht = new HashTable(potentialSize);
        boolean wasPrime = ht.isPrime(potentialSize);
        if (!wasPrime)
            System.out.println("the number you chose was not prime\n" + ht.tableSize + " was used instead");
        for (int i = 0; i < numberOfWords; i++) {
            ht.put(wordArray[i], i);
        } // end for
    } // end createHashTable

    /** Method for directing user involvement with the Hash Table **/
    public void direct() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1 - Find the index and bucket of a particular word");
        System.out.println("2 - Find the contents of a particular bucket");
        System.out.println("3 - Print the entire contents of the Hash Table");
        System.out.println("4 - Exit the program");
        boolean error;
        int choice;
        do {
            error = true; // basically a input mismatch exception filter
            choice = 0;
            while (error) {
                if (in.hasNextInt()) // if the input was good
                    choice = in.nextInt(); // assign the number to choice
                else {
                    System.out.println("Please enter a numerical integer");
                    in.next(); // try again
                    continue;
                } // end else
                error = false;
            } // end while
            switch (choice) { // direct the user to what they want to do
                case 1:
                    findWord();
                case 2:
                    System.out.println("Which bucket would you like to see the contents of?");
                    boolean bucketError = true;
                    int bucketChoice;
                    while (bucketError) { // make sure the user entered a numerical integer for the bucket
                        if (in.hasNextInt()) {
                            bucketChoice = in.nextInt(); // user enters the bucket
                            findBucketContents(bucketChoice);
                        } else {
                            System.out.println("Please enter a numerical integer");
                            in.next(); // try again
                            continue;
                        } // end else
                        bucketError = false;
                    } // end while
                case 3:
                    printResults();
                case 4:
                    System.exit(-1); // close the program
            } // end switch
            System.out.println("Please enter 1, 2, or 3");
        } while (choice < 1 || choice > 3);
    } // end direct

    /** Method for testing results and searching through the Hash Table **/
    public void findWord() {
        String choice;
        System.out.println("Which word would you like the index of?");
        String wordToSearch = in.next();
        if (ht.get(wordToSearch) == -1) // if the word is not in the hash table
            System.out.println("Word not Found! Is there a typo in your entry?");
        else
            System.out.println("Your word was found in index: " + ht.get(wordToSearch) + " in Bucket: "
                    + (ht.computeHash(wordToSearch) + 1));
        do {
            System.out.println("Would you like to continue? Y / N");
            choice = in.next();
            switch (choice.toUpperCase()) {
                case "Y":
                    findWord();
                case "N":
                    direct();
            } // end switch
        } while (!choice.equals("Y")); // if the user did not enter Y or N
    } // end testResults

    /** Method to find the contents of a particular Bucket **/
    public void findBucketContents(int bucket) {
        String choice;
            System.out.print("Bucket " + bucket + " : "); // print the bucket #
            LinkedHashEntry entry = ht.table[bucket];
            while (entry != null) {
                System.out.print(entry.key + " "); // print the contents of the bucket
                entry = entry.next;
            } // end while
        do {
            System.out.println("\nWould you like to continue? Y / N");
            choice = in.next();
            switch (choice.toUpperCase()) {
                case "Y":
                    System.out.println("Which bucket would you like to see the contents of?");
                    int newBucket = in.nextInt();
                    findBucketContents(newBucket);
                case "N":
                    direct();
            } // end switch
        } while (!choice.equals("Y")); // if the user did not enter Y or N
    } // end findBucketContents

    /** Method to print the results of the hash table **/
    public void printResults() {
        ht.printHashTable();
        direct();
    } // end printResults

} // end HW7