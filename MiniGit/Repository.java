import java.util.*;
import java.text.SimpleDateFormat;

//This class represents a simplified version of a Git repository, 
//supporting a subset of operations found in real Git repositories.
public class Repository {

    //Fields
    private final String name;
    private Commit head;

    //Behavior:
    //  -creates a new, empty repository with the specified 
    //   name
    //Parameters:
    //  -name: string representing repository name
    //Exceptions:
    //  -IllegalArgumentException: thrown if name is 
    //   null or empty
    public Repository(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.head = null;
    }

    //Behavior:
    //  -Provides ID of the current head of this repository.
    //  -if head is null, return null
    //Return:
    //  -String: ID of the current head of this repository.
    public String getRepoHead(){
        if (head != null){
            return head.id;
        }
        return null;
    }

    //Behavior:
    //  -Provides the number of commits in the repository
    //Return:
    //  -int: the number of commits in the repository
    public int getRepoSize() {
        int count = 0;
        Commit current = head;
        while (current != null) {
            count++;
            current = current.past;
        }
        return count;
    }

    //Behavior:
    //  -Provides a string representation of this repository
    //Return:
    //  -String: formatted representation of this repository
    public String toString(){
        if (getRepoSize() == 0){
            return name + " - No commits";
        }
        return name + " - Current head: " + head.toString();
    }

    //Behavior:
    //  -Checks if commit with specified ID is in repository
    //   or not
    //Parameters:
    //  -targetID: string representing the commit's ID
    //Return:
    //  -boolean: true if the commit with ID is 
    //   in the repository, false if not
    public boolean contains(String targetId){
        Commit current = head;
        while(current != null){
            if (current.id.equals(targetId)){
                return true;
            }
            current = current.past;
        }
        return false;
    }

    //Behavior:
    //  -Provides the desired most recent commits in this repository,
    //   with the most recent first
    //  -if there are fewer than n commits in this repository, return them all.
    //  -if there are no commits in this repository, return the empty string.
    //Parameters:
    //  -n: int representing number of recent commits
    //Return:
    //  -boolean: string representations of the most 
    //   recent commits in this repository
    //Exception:
    //  -IllegalArgumentException: thrown if n is 
    //   non-positive
    public String getHistory(int n){
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        String output = "";
        Commit current = head;
        for (int i = 0; i < n && current != null; i++) {
            output += current.toString() + "\n";
            current = current.past;
        }

        return output;
    }

    //Behavior:
    //  - Creates a new commit with the given message, 
    //    and adds it to this repository.
    //Parameter:
    //  - message: string representing new commit message
    //Return:
    //  - String: ID of the new commit.
    public String commit(String message){
        head = new Commit(message, head);
        return head.id;
    }
    
    //Behavior:
    //  - Remove the commit with specified ID from this repository, 
    //    maintaining the rest of the history.
    //Parameters:
    //  -targetID: string representing the commit's ID
    //Return:
    //  - boolean: true if the commit was successfully dropped, and 
    //    false if there is no commit that matches the given ID in the repository
    public boolean drop(String targetId){
        if (!contains(targetId)) {
            return false;
        }

        if (head.id.equals(targetId)) {
            head = head.past;
            return true;
        }

        Commit current = head;
        while (current != null && current.past != null) {
            if (current.past.id.equals(targetId)) {
                current.past = current.past.past;
                return true;
            }
            current = current.past;
        }
        return false;
    }

    //Behavior:
    //  -Merges commits from another repository into this one, 
    //   preserving chronological order.
    //  -If the other repository is empty, this repository should 
    //   remain unchanged.
    //  -If this repository is empty, all commits in the other 
    //   repository should be moved into this repository.
    //  -At the end of this method's execution, other should be 
    //   an empty repository in all cases.
    //Parameter:
    //  -other: The repository to synchronize with.
    public void synchronize(Repository other) {
        Commit otherHead = other.head;
    
        while (otherHead != null) {
            Commit next = otherHead.past;
            otherHead.past = null; // detach from the other repository
    
            if (this.head == null || this.head.timeStamp <= otherHead.timeStamp) {
                otherHead.past = this.head;
                this.head = otherHead;
            } else {
                Commit current = this.head;
                
                while (current.past != null && current.past.timeStamp > otherHead.timeStamp) {
                    current = current.past;
                }

                otherHead.past = current.past;
                current.past = otherHead;
            }
            otherHead = next;
        }
        other.head = null;
    }

    /**
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}