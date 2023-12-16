package MusicPlaylist;
import java.util.*;
import java.io.*;

// This class represents a simple music playlist application. 
// Users can add songs to a playlist, play songs, view the play history, 
// clear the history, delete songs from the history, and quit the application.
public class MusicPlaylist {
    public static void main(String[] args) {
        System.out.println("Welcome to the CSE 122 Music Playlist!");

        Stack<String> history = new Stack<String>();
        Queue<String> playlist = new LinkedList<String>();
        
        boolean condition = true;

        while(condition) {
            System.out.println("(A) Add song");
            System.out.println("(P) Play song");
            System.out.println("(Pr) Print history");
            System.out.println("(C) Clear history");
            System.out.println("(D) Delete from history");
            System.out.println("(Q) Quit");
            System.out.println();
            System.out.print("Enter your choice: ");

            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();

            if (choice.equalsIgnoreCase("a")) {
                addSong(input, playlist);
            } else if (choice.equalsIgnoreCase("p")) {
                playSong(input, playlist, history);
            } else if (choice.equalsIgnoreCase("pr")) {
                printHistory(history);
            } else if (choice.equalsIgnoreCase("c")) {
                clearHistory(history);
            } else if (choice.equalsIgnoreCase("d")) {
                deleteFromHistory(input, history);
            } else if (choice.equalsIgnoreCase("q")) {
                condition = false;
            } 
        }
    }

    // Behavior:
    //   - This method adds a song to the music playlist.
    // Parameters:
    //   - console: a scanner object enabling console-line input from the user
    //   - q: the playlist queue to add the song to
    public static void addSong(Scanner console, Queue<String> q) {
        System.out.print("Enter song name: ");
        String songName = console.nextLine();
        q.add(songName);
        System.out.println("Successfully added " + songName);
        System.out.println();
        System.out.println();
    }

    // Behavior:
    //   - This method plays a song from the playlist, adds it to the history, 
    //     and removes it from the playlist.
    // Parameters:
    //   - console: a scanner object enabling console-line input from the user
    //   - q: the music playlist queue
    //   - s: the music history stack
    // Exceptions:
    //   - If the music playlist is empty, an IllegalStateException is thrown.
    public static void playSong(Scanner console, Queue<String> q, Stack<String> s) {
        if(q.isEmpty()) {
            throw new IllegalStateException();
        }

        String songName = q.remove();
        System.out.println("Playing song: " + songName);
        System.out.println();
        System.out.println();
        s.push(songName);
    }

    // Behavior:
    //   - This method prints the song play history in reverse chronological order.
    // Parameters:
    //   - s: the music history stack
    // Exceptions:
    //   - If the music history is empty, an IllegalStateException is thrown.
    public static void printHistory(Stack<String> s) {
        if(s.isEmpty()) {
            throw new IllegalStateException();
        }

        Queue<String> tempQ = new LinkedList<String>();
        sToQ(s, tempQ, true);
        reverseQ(tempQ, s);
        qToS(tempQ, s);

        System.out.println();
        System.out.println();
    }

    // Behavior:
    //   - This method clears the play history completely.
    // Parameters:
    //   - s: the music history stack
    public static void clearHistory(Stack<String> s) {
        int size = s.size();
        for(int i = 0; i < size; i++){
            s.pop();
        }
    }
    // Behavior:
    //   - This method deletes a specified number of songs from the play history.
    //   - A positive number deletes from the recent history, while a negative 
    //     number deletes from the beginning (chronological song play order)
    // Parameters:
    //   - console: a scanner object enabling console-line input from the user
    //   - s: the music history stack
    // Exceptions:
    //   - If the absolute value of the user-defined number of songs to delete from history is greater than 
    //     the music history size, an IllegalArgumentException is thrown.
    public static void deleteFromHistory(Scanner console, Stack<String> s) {
        System.out.println("A positive number will delete from recent history.");
        System.out.println("A negative number will delete from the beginning of history.");
        System.out.print("Enter number of songs to delete: ");
        System.out.println();

        int number = Integer.parseInt(console.nextLine());
        if(s.size() < Math.abs(number)) {
            throw new IllegalArgumentException();
        }

        while(number > 0) { 
            String value = s.pop();
            number--;
        }

        if(number < 0) {
            Queue<String> tempQ = new LinkedList<String>();
            sToQ(s, tempQ, false);
            reverseQ(tempQ, s);
            while(number < 0) {
                tempQ.remove();
                number++;
            }
            qToS(tempQ, s);
        }
        System.out.println();
    }


    // Behavior:
    //   -  This method transfers all elements from the provided Queue to the given Stack.
    // Parameters:
    //   - q: the queue from which elements are moved
    //   - s: the stack to which elements are transferred
    private static void qToS(Queue<String> q, Stack<String> s) {
        while (!q.isEmpty()) {
            s.push(q.remove());
        }
    }

    // Behavior:
    //   -  This method transfers all elements from the provided Stack to the given Queue. 
    //   -  It can optionally print each transferred element if the 'condition' parameter is true.
    // Parameters:
    //   - s: the stack to which elements are moved
    //   - q: the queue from which elements are transferred
    //   - condition: boolean indicating whether to print each transferred element
    private static void sToQ(Stack<String> s, Queue<String> q, boolean condition) {
        while (!s.isEmpty()) {
            String temp = s.pop(); 
            if (condition) {
                System.out.println("    " + temp);
            }
            q.add(temp);
        }
    }

    // Behavior:
    //   -  This method reverses the order of elements in the provided queue.
    // Parameters:
    //   - q: the queue to be reversed
    //   - s: the stack used in reversal
    private static void reverseQ(Queue<String> q, Stack<String> s) {

        int size = q.size();

        qToS(q, s);

        // while (!s.isEmpty()) {
        //     String value = s.pop();
        //     q.add(value);
        // }
        sToQ(s,q,false);
    }
}