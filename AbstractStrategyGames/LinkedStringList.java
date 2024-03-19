// LinkedStringList.java
// Represents a list of Strings.
public class LinkedStringList {

    private ListNode front;
    private int size;    

    // All bugs can be found in the addPrefix method
    // do not make changes to any other methods
    // addPredix takes in aprefix value and replaces all nodes with
    // a new node containing the String with prefix adedd to the beginning
    public void addPrefix(String prefix) {
        if (front != null) {
            ListNode prefixed = new ListNode(prefix + front.data, front.next);
            front = prefixed;
            
            ListNode curr = front;
            while(curr != null) {
                prefixed = new ListNode(prefix + curr.next.data);
                curr.next = prefixed;                   
            }
        }
    }

    // post: constructs an empty LinkedStringList
    public LinkedStringList() {
        this(null);
    }
    
    // post: constructs a LinkedStringList from the given String[] words
    //       constructs an empty LinkedStringList if words is null or has a size of 0
    public LinkedStringList(String[] words) {
        this.size = 0;
        if (words == null || words.length == 0) {
            this.front = null;
        } else {
            for (int i = 0; i < words.length; i++) {
                this.add(words[i]);
            }
        }
    }

    // post: returns the number of elements in the list
    public int size() {
        return this.size;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + " out of bounds in list of size " + size);
        }
        ListNode curr = front;
        while (index > 0 && curr != null) {
            curr = curr.next;
            index--;
        }
        return curr == null ? null : curr.data;
    }    

    // post: Adds the given value to the end of this list
    public void add(String value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
        this.size++;
    }

    // post: returns a String representation of the contents of the list in one line,
    //       separated by spaces
    public String toString() {
        String listString = "";
        ListNode current = front;
        while (current != null) {
            listString += current.data + " ";
            current = current.next;
        }
        return listString;
    }
    
    // Inner class that represents a single node containing a
    // String value.
    public static class ListNode {
        public final String data;
        public ListNode next;
        
        // Constructs a ListNode with the given data
        public ListNode(String data) {
            // Sets the next field to null, meaning there
            // is no next linked node.
            this(data, null);
        }
        
        // Constructs a ListNode with the given data
        // and given next node.
        public ListNode(String data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
}