/* Project : Homework #7
 * Programmer : Spencer Crawford
 * Date : 11/3/17
 * Description : Uses a HashTable to index and search for words in a file
 */
/** The Linked Hash Entry class defines the Node for a Hash Table. Each Bucket in the Hash Table contains a list of
 *  Linked Hash Entries, each with the following members: **/
public class LinkedHashEntry {

    String key;           // key is the word
    int value;            // value is the index
    LinkedHashEntry next; // next is the next key-value pair

    /** Default constructor for a Linked Hash Entry **/
    LinkedHashEntry(String key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    } // end constructor

    /** Function to receive value of a Linked Hash Entry **/
    public int getValue() {
        return value;
    } // end getValue

    /** Function to set value for a Linked Hash Entry **/
    public void setValue(int value) {
        this.value = value;
    } // end setValue

    /** Function to receive key for a Linked Hash Entry **/
    public String getKey() {
        return key;
    } // end getKey

    /** Function to receive the next Linked Hash Entry **/
    public LinkedHashEntry getNext() {
        return next;
    } // end getNext

    /** Function to set the next Linked Hash Entry **/
    public void setNext(LinkedHashEntry next) {
        this.next = next;
    } // end setNext

} // end linkedHashEntry
