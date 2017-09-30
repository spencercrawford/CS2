/* Project     : Homework #2
 * Programmer  : Spencer Crawford
 * Date        : 9/8/17
 * Description : Organizes and displays information pertaining to countries that border Germany
 */
public class Node<Countries> {

    public Countries current;    // current item
    public Node<Countries> next; // next item

    public Node(Countries current, Node next) { // constructor for an item in a potential list
        this.current = current;
        this.next = next;
    } // end constructor
} // end Node Class