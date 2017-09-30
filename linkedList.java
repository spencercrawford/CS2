/* Project     : Homework #2
 * Programmer  : Spencer Crawford
 * Date        : 9/8/17
 * Description : Organizes and displays information pertaining to countries that border Germany
 */
public class linkedList<Countries> {

    public Node<Countries> head; // this is the first item in the list

    public linkedList() {
        head = null;
    } // end constructor

    public void addFirst(Countries item) {
        head = new Node<Countries>(item, head);
    } // end addFirst

    public void addLast(Countries item) { // add an item to the list
        if (head == null)   // if the list is empty
            addFirst(item); // the last item is also the first item
        else {
            Node<Countries> temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = new Node<Countries>(item, null); // add an item to the list at the end
        } // end if
    } // end addLast

    public Countries get(int index) { // reference a specific item in the list
        if (head == null) throw new IndexOutOfBoundsException(); // the list can't be empty
        Node<Countries> temp = head;
        for(int i = 0; i < index; i++) temp = temp.next;
        if (temp == null) throw new IndexOutOfBoundsException();
        return temp.current; // reference the item
    } // end get
} // end linkedList Class