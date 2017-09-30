/* Project     : Homework #2
 * Programmer  : Spencer Crawford
 * Date        : 9/8/17
 * Description : Organizes and displays information pertaining to countries that border Germany
 */
class Countries  { // this class is responsible for constructing the information a country holds

    String countryName;    // holds the name of a country
    String latitude;       // holds the latitude of a country
    String longitude;      // holds the longitude of a country
    int countryArea;       // holds the area of a country
    int countryPopulation; // holds the population of a country
    double countryGDP;     // holds the overall GDP of a country
    int countryYear;       // holds the year a country was declared
    Borders borders;

    public Countries() {} // end constructor

} // end class