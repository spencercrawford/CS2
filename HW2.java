/* Project     : Homework #2
 * Programmer  : Spencer Crawford
 * Date        : 9/8/17
 * Description : Organizes and displays information pertaining to countries that border Germany
 */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HW2 {

    public int numberOfCountries = getNumberOfCountries();                  // the number of countries
    public Scanner inputScanner = new Scanner(System.in);                   // scanner for user input
    public File countriesFile = new File("countries_data.txt");   // file containing country data
    public linkedList<Countries> countriesLL = new linkedList<Countries>();         // linked list of countries
    public Countries[] countriesArray = new Countries[numberOfCountries];   // array of countries
    public int choose = 0;                                                  // index deciding more input or not
    public boolean moreInput = false;                                       // boolean deciding more input or not
    public String moreInputString;                                          // string deciding more input or not
    public final int maxPopulation = 35000000;                              // holds the max population size

    public static void main(String args[]) {
        new HW2();
    } // end main

    HW2() { // this constructor is responsible for calling all methods and determining if the user wants to continue
        try {
            create();
            assignBorders();
            do {
                greetUser();
                choose = inputScanner.nextInt();
                if (choose == 1) {
                    importData();
                } else if (choose == 2) {              //          HELLO
                    showBordersForGermany();           //
                } else if (choose == 3) {              //         /\___/\
                    showAllBorders();                  //        ( =^.^= )
                } else if (choose == 4) {              //         (") (")__/
                    showPopulations();                 //
                } else if (choose == 5) {              //        DR. COHEN!
                    showBordersAndPopulation();
                } else if (choose == 6) {
                    System.exit(0);
                } else { // if the user enter a number other than 1 through 6
                    System.out.println("Please enter a number 1 through 6!");
                    System.exit(0);
                } // end if
                // prompt the user to continue or quit
                System.out.println("\nContinue? Type 'Y' to continue, or enter any other key to exit.");
                moreInputString = inputScanner.next();
                if (moreInputString.toUpperCase().charAt(0) == 'Y') { // if the user wants to continue
                    moreInput = true;
                } else { // if the user does not want to continue
                    moreInput = false;
                    System.exit(0);
                } // end if
            } while (moreInput);
        } catch (InputMismatchException imme) { // if the user can't follow directions...
            System.out.println("Please enter a numerical digit!");
        } // end catch
    } // end constructor

    public int getNumberOfCountries() { // this method determines how many countries are in the file

        int lineNumber = 0; // holds the number of lines, which will determine the number of countries
        try {
            BufferedReader reader = new BufferedReader(new FileReader("copy.txt")); // read the file
            while ((reader.readLine()) != null) { // read each line
                lineNumber++; } // add one to the line number for each line
                reader.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found!");
        } catch (IOException ioe) {
            System.out.println("IO Exception!"); }
        return lineNumber; // return the number of countries
    } // end getNumberOfCountries

    public void create() { // this method creates a linked list and an array of countries

        try {
            Scanner countriesScan = new Scanner(countriesFile);
            countriesScan.useDelimiter(" ");

            for (int i = 0; countriesScan.hasNext(); i++) {

                Countries[] country = new Countries[numberOfCountries];
                country[i] = new Countries();
                country[i].countryName = countriesScan.next();
                country[i].latitude = countriesScan.next();
                country[i].longitude = countriesScan.next();
                country[i].countryArea = countriesScan.nextInt();
                country[i].countryPopulation = countriesScan.nextInt();
                country[i].countryGDP = countriesScan.nextDouble();
                country[i].countryYear = countriesScan.nextInt();
                country[i].borders = new Borders();
                country[i].borders.borderNum = countriesScan.nextInt(); // assigns the border index
                country[i].borders.countryIndex = i;
                countriesArray[i] = country[i]; // fills an array with countries

            } // end for

            for (int i = 0; i < numberOfCountries; i++) { // fills the linked list with countries
                countriesLL.addLast(countriesArray[i]);
            } // end for

        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found!");
        } // end catch
    } // end create

    public void assignBorders() { // this method assigns borders to each country

        for (int i = 0; i < numberOfCountries; i++) { // loop through all the countries
            StringBuilder builder = new StringBuilder(); // create a new string builder
            // convert the country's borderNum to string
            String borderNumString = Integer.toString(countriesLL.get(i).borders.borderNum);
            // create an array of strings the size of the borderNum's length
            String[] borderArray = new String[borderNumString.length()];
            for (int k = 0; k < borderNumString.length(); k++) { // loop through the borderNum
                for (int j = 0; j < numberOfCountries; j++) { // loop through all the countries
                    // convert the country's index to string
                    String indexString = Integer.toString(countriesLL.get(j).borders.countryIndex);
                    // if the border number contains a country's index
                    if (borderNumString.charAt(k) == indexString.charAt(0)) {
                        // assign a country's name to each index in the array
                        borderArray[k] = countriesLL.get(j).countryName;
                    } // end if
                } // end for
            } // end for
            for (String s : borderArray) { // loop through the border array
                builder.append(s); // append the contents to the string builder
                builder.append("\n");
            } // end for
            countriesLL.get(i).borders.borderString = builder.toString(); // set the current country's border list to the string
        } // end for
    } // end assignBorders

    public void greetUser() { // this method prompts the user to begin / continue

        System.out.println("Press 1 to import data\n" +
                "Press 2 to display list of all countries that border Germany\n" +
                "Press 3 to display list of countries that border a country of your choice\n" +
                "Press 4 to display list of all countries that have a population of over 35 million\n" +
                "Press 5 to display list of all countries that border Germany and have a population of over 35 " +
                "million\n" +
                "Press 6 to exit");
    } // end greetUser

    public void importData() { // this method displays the data for all countries

        for (int i = 0; i < numberOfCountries; i++) {
            System.out.println("Country Name: " + countriesLL.get(i).countryName);
            System.out.println("Latitude: " + countriesLL.get(i).latitude);
            System.out.println("Longitude: " + countriesLL.get(i).longitude);
            System.out.println("Country Area: " + countriesLL.get(i).countryArea);
            System.out.println("Country Population: " + countriesLL.get(i).countryPopulation);
            System.out.println("Country GDP: " + countriesLL.get(i).countryGDP + "*10^12");
            System.out.println("Country Year: " + countriesLL.get(i).countryYear + "\n");
        } // end for
    } // end importData

    public void showBordersForGermany() { // this method displays the borders for germany

        for (int i = 0; i < numberOfCountries; i++) { // loop through the countries
            if (countriesLL.get(i).countryName.equals("Germany")) { // if the country is germany
                System.out.println(countriesLL.get(i).countryName + ":");
                System.out.print(countriesLL.get(i).borders.borderString); // print the borders
            } // end if
        } // end for
    } // end showBordersForGermany

    public void showAllBorders() { // this method displays borders for a country of the user's choice

        try {
            System.out.println("0 = Germany\n1 = Netherlands\n2 = Belgium\n3 = Luxembourg\n4 = France\n" +
                    "5 = Switzerland\n6 = Austria\n7 = Czechia\n8 = Poland");
            int chooseBorder = inputScanner.nextInt();
            if (chooseBorder < 0 || chooseBorder > 8) throw new InputMismatchException(); // just in case
            for (int i = 0; i < numberOfCountries; i++) { // loop through the countries
                if (countriesLL.get(i).borders.countryIndex == chooseBorder) { // if the input matches a country's index
                    System.out.println(countriesLL.get(i).countryName + ":");
                    System.out.print(countriesLL.get(i).borders.borderString); // print the borders
                } // end if
            } // end for
        } catch (InputMismatchException imme) {
            System.out.println("Please enter a number between 0 abd 8 inclusive!");
        }
    } // end showAllBorders

    public void showPopulations() { // this method displays countrys with a population over 35 million

        System.out.println("Here is a list of countries with a population of over 35 million:\n");
        for (int i = 0; i < numberOfCountries; i++) {
            if (countriesLL.get(i).countryPopulation > maxPopulation) {
                System.out.print(countriesArray[i].countryName);
            } // end if
        } // end for
    } // end showPopulations

    public void showBordersAndPopulation() {
        // this method shows which counties border Germany AND have a population of over 35 million

        System.out.println("Here is a list of all countries that border Germany and have a population of over 35 " +
                "million: ");
        for (int i = 0; i < numberOfCountries; i++) {
            if (countriesLL.get(i).borders.countryIndex != 0 && countriesLL.get(i).countryPopulation > maxPopulation) {
                System.out.print(countriesLL.get(i).countryName);
            } // end if
        } // end for
    } // end showBordersAndPopulation
} // end HW2