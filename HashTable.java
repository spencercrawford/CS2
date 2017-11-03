/* Project : Homework #7
 * Programmer : Spencer Crawford
 * Date : 11/3/17
 * Description : Uses a HashTable to index and search for words in a file
 */
/** The Hash Table Class is a data structure composed of an Array of Linked Lists. Each Linked List is composed of
 *  Linked Hash Entries. The purpose of a Hash Table is dramatically increasing speed in accessing members of table. **/
public class HashTable {

    LinkedHashEntry[] table; // array of linked lists for the table
    int tableSize;           // number of total buckets in the table
    int size;                // number of key-value pairs

    /** Default constructor for a Hash Table **/
    HashTable(int number) {
        size = 0; // all buckets start empty
        tableSize = nextPrime(number); // compute the next highest prime number for the number of buckets
        table = new LinkedHashEntry[tableSize]; // create the hash table
        for (int i = 0; i < tableSize; i++) {
            table[i] = null; // all linked lists start empty
        } // end for
    } // end constructor

    /** Function for computing the hash value of a String **/
    public int computeHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash += s.charAt(i); // add up the ascii values
        } // end for
        return hash % tableSize; // mod the sum of the ascii values with the table size
    } // end computeHash

    /** Function to receive the value of a Linked Hash Entry's key **/
    public int get(String key) {
        int hash = computeHash(key); // convert the string to a hash value
        if (table[hash] == null) // if the key is not in any of the buckets
            return -1;
        else {
            LinkedHashEntry entry = table[hash];
            while (entry != null && !entry.key.equals(key)) // if the key is not the first item in the bucket
                entry = entry.next; // go to the next key in the bucket
            if (entry == null) // if the bucket is empty
                return -1;
            else
                return entry.value; // return the value
        } // end else
    } // end get

    /** Function to add a Linked Hash Entry to the Hash Table **/
    public void put(String key, int value) {
        int hash = computeHash(key); // convert the string to a hash value
        if (table[hash] == null) { // if the bucket is empty
            table[hash] = new LinkedHashEntry(key, value); // add the entry
        } else {
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) // if there is something else already in the bucket
                entry = entry.next; // go to the next available space in the bucket
            if (entry.key.equals(key)) // if the bucket contains the current key
                entry.value = value; // assign the entry's value
            else
                entry.next = new LinkedHashEntry(key, value); // place the entry in the bucket
        } // end else
        size++; // add one to the number of key-value pairs
    } // end put

    /** Function to remove a Linked Hash Entry from the Hash Table **/
    public void remove(String key) {
        int hash = computeHash(key); // convert the string to a hash value
        if (table[hash] != null) { // if the key is in the hash table
            LinkedHashEntry prevEntry = null;
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !entry.key.equals(key)) { // if there is something else already in the bucket
                prevEntry = entry;
                entry = entry.next; // go to the next entry
            } // end while
            if (entry.key.equals(key)) { // if the correct key is found
                if (prevEntry == null)
                    table[hash] = entry.next;
                else
                    prevEntry.next = entry.next;
                size--; // decrease the size by one
            } // end if
        } // end if
    } // end remove

    /** Function to receive the current number of Linked Hash Entries **/
    public int getSize() {
        return size;
    } // end getSize

    /** Function to print the contents of the Hash Table **/
    public void printHashTable() {
        for (int i = 0; i < tableSize; i++) {
            System.out.print("\nBucket "+ (i + 1) +" : "); // print the bucket #
            LinkedHashEntry entry = table[i];
            while (entry != null) {
                System.out.print(entry.key +" "); // print the contents of the bucket
                entry = entry.next;
            } // end while
        } // end for
    } // end printHashTable

    /** Function to determine if a given number is Prime **/
    public boolean isPrime(int number) {
        if (number % 2 == 0) // if its even, its not prime
            return false;
        for (int i = 3; i * i <= number; i += 2) { // if its odd and not prime
            if (number % i == 0)
                return false;
        } // end for
        return true; // the number is prime
    } // end isPrime

    /** Function to determine the next highest Prime Number **/
    public int nextPrime(int minNumber) {
        for (int i = minNumber; true; i++) {
            if (isPrime(i))
                return i;
        } // end for
    } // end nextPrime

} // end HashTable