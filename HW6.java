/* Project : Homework #6
 * Programmer : Spencer Crawford
 * Date : 10/19/17
 * Description : tests practical applications of Merge Sort.
 */

//import org.apache.commons.lang3.RandomStringUtils;
import java.util.*;

public class HW6 {

    // VARIABLES //
    public Scanner in = new Scanner(System.in); // scanner for user input
    public int SIZE;                            // determines the size of the array
    public int BIG;                             // determines the maximum random number
    public int type;                            // determines the data type for the array
    public int[] array;                         // int array to be sorted with java sort
    public int[] array2;                        // int array to be sorted with merge sort
    public double[] doubleArray;                // double array to be sorted with java sort
    public double[] doubleArray2;               // double array to be sorted with merge sort
    public String[] stringArray;                // String array to be sorted with java sort
    public String[] stringArray2;               // String array to be sorted with merge sort
    // all start and end times for wall clock and CPU time
    public long startWallJava, startWallMerge, startCPUJava, startCPUMerge;
    public long endWallJava, endWallMerge, endCPUJava, endCPUMerge;

    public static void main(String args[]) { new HW6(); } // end main

    HW6() {
        promptUser();     // prompt the user to choose the SIZE and BIG
        printResults(); } // print the results of the time testing

    public void promptUser() { // prompt the user to choose the SIZE and BIG
        try {
            System.out.println("Please enter the SIZE"); // user is asked to enter the SIZE
            SIZE = in.nextInt();
            System.out.println("Please enter the BIG");  // user is asked to enter the BIG
            int maybeBIG = in.nextInt();
            while (maybeBIG <= SIZE) {                   // BIG must be larger than SIZE
                System.out.println("BIG must be larger than SIZE!");
                maybeBIG = in.nextInt();
            } // end while
            BIG = maybeBIG;

            // user is asked to to enter the data type for the arrays to be sorted (int, double string)
            System.out.println("Please choose the Data Type:\n1 = Integer\n2 = double\n3 = String");
            int maybeType = in.nextInt();
            while (maybeType != 1 && maybeType != 2 && maybeType != 3) { // make sure the user is paying attention...
                System.out.println("Please enter 1, 2, or 3");
                maybeType = in.nextInt();
            } // end while
            type = maybeType;

            switch (type) {
                case 1 : createIntegers();  // create int arrays
                case 2 : createDoubles();   // create double arrays
                case 3 : createStrings(); } // create String arrays

        } catch (InputMismatchException ime) {           // just in case...
            System.out.println("Please enter a numerical integer");
            System.exit(0);
        } } // end promptUser

    public void createIntegers() { // this method creates two identical arrays of ints
        array  = new int[SIZE]; // to be sorted with java sort
        array2 = new int[SIZE]; // to be sorted with merge sort

        for (int i = 0; i < SIZE; i++) {
            array[i] = (int)(Math.random() * (BIG - 1)) + 1; // fill with random ints from 1 - BIG
        } // end for

        array2 = Arrays.copyOf(array, SIZE); // copy the first array into the second
        sortAndTest(1); // sort and test the times for the int arrays
    } // end createIntegers

    public void createDoubles() { // this method creates two identical arrays of doubles
        doubleArray  = new double[SIZE]; // to be sorted with java sort
        doubleArray2 = new double[SIZE]; // to be sorted with merge sort

        for (int i = 0; i < SIZE; i++) {
            doubleArray[i] = (double) (Math.random() * (BIG - 1.0)) + 1.0; // fill with random doubles from 1 - BIG
        } // end for

        doubleArray2 = Arrays.copyOf(doubleArray, SIZE); // copy the first array into the second
        sortAndTest(2); // sort and test the times for the double arrays
    } // end createDoubles

    public void createStrings() { // this method creates two identical arrays of Strings
        stringArray  = new String[SIZE]; // to be sorted with java sort
        stringArray2 = new String[SIZE]; // to be sorted with merge sort

        Random random = new Random();    // picks a random character from the String alphaNum (below)
        String alphaNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxys1234567890";
        StringBuilder builder;
        for (int i = 0; i < SIZE; i++) {
            builder = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                int index = random.nextInt(alphaNum.length());
                builder.append(alphaNum.charAt(index)); // fills with random chars from alphaNum
            } // end for
            stringArray[i] = builder.toString();
        } // end for

        stringArray2 = Arrays.copyOf(stringArray, SIZE); // copy the first array into the second
        sortAndTest(3); // sort and test the times for the String arrays

    } // end createStrings

    /** the above random string generator is for the assignment... but I like this one better. **/
    /*
    public void createStrings() { // this method creates two identical arrays of Strings
        stringArray  = new String[SIZE]; // to be sorted with java sort
        stringArray2 = new String[SIZE]; // to be sorted with merge sort
        for (int i = 0; i < SIZE; i++) {
            stringArray[i] = RandomStringUtils.random(6,32,120,true,true); }
        stringArray2 = Arrays.copyOf(stringArray, SIZE); // copy the first array into the second
        sortAndTest(3); } // end createStrings
    */

    public void sortAndTest(int type) { // sort the two arrays using java sort and merge sort; testing both times
        // test wall clock and CPU times for java sort
        startWallJava = System.currentTimeMillis();
        startCPUJava  = System.nanoTime();

        if (type == 1) {
            Arrays.sort(array); }       //sort the int array
        if (type == 2) {
            Arrays.sort(doubleArray); } // sort the double array
        if (type == 3) {
            Arrays.sort(stringArray); } // sort the String array

        endWallJava   = (System.currentTimeMillis() - startWallJava);
        endCPUJava    = (System.nanoTime() - startCPUJava);

        // test wall clock and CPU times for merge sort
        startWallMerge = System.currentTimeMillis();
        startCPUMerge  = System.nanoTime();

        MergeSort mergeSort = new MergeSort();
        if (type == 1) {
            array2 = mergeSort.mergesort(array2); }                    // merge sort the int array
        if (type == 2) {
            doubleArray2 = mergeSort.mergesortDoubles(doubleArray2); } // merge sort the double array
        if (type == 3) {
            stringArray2 = mergeSort.mergesortStrings(stringArray2); } // merge sort the String array

        endWallMerge   = (System.currentTimeMillis() - startWallMerge);
        endCPUMerge    = (System.nanoTime() - startCPUMerge);
    } // endSortAndTest

    public void printResults() { // this method prints the results of the sorting and timing
        System.out.println("SIZE: " + SIZE + " BIG: " + BIG);
        System.out.println("Wall Clock:\nJava Sort: " + endWallJava + "ms" + "\nMerge Sort: " + endWallMerge + "ms" +
                "\n\nCPU Time:\nJava Sort: " + endCPUJava + "ns" + "\nMerge Sort: " + endCPUMerge + "ns");
    } // endPrintResults
} // end HW6