package MusicPlaylist;
import java.util.*;

// The MusicPlaylist class represents a music playlist with functionalities 
// to add songs, play songs, manage history, and retrieve playlist and 
// history information.
public class MusicPlaylist {
   private Queue<String> playlist;
   private Stack<String> history;
   

   // Behavior: 
   //   - Constructs a MusicPlaylist, initializing it with an empty song playlist and 
   //     empty song play history
   public MusicPlaylist() {
      this.playlist = new LinkedList<>();
      this.history = new Stack<>();
   }

   // Behavior: 
   //   - Constructs a MusicPlaylist, initializing it with songs and 
   //     history of played songs
   // Parameters:
   //   - playlist: a Queue<String> containing playlist songs to be managed 
   //     by MusicPlaylist
   //   - history: a Stack<String> containing song play history to be managed 
   //     by MusicPlaylist
   public MusicPlaylist(Queue<String> playlist, Stack<String> history) {
      this.playlist = new LinkedList<>(playlist);
      this.history = new Stack<>();
      this.history.addAll(history);
   }

   // Behavior: 
   //   - Adds a song to the music playlist.
   // Parameters:
   //   - songName: a String representing the name of the song.
   public void addSong(String songName) {
      playlist.add(songName);
   }
   
   // Behavior: 
   //   - This method plays a song from the playlist, adds it to the history, 
   //     and removes it from the playlist.
   // Exceptions:
   //   - IllegalStateException: thrown if the music playlist is empty.
   public void playSong() {
      if (playlist.isEmpty()) {
         throw new IllegalStateException();
      } else{
         String songName = playlist.remove();
         System.out.println("Playing song: " + songName);
         System.out.println();
         history.push(songName);
      }
   }
   
   // Behavior: 
   //   - Prints the history of songs played.
   // Exceptions:
   //   - IllegalStateException: thrown if the music history is empty.
   public void printHistory() {
      if (history.isEmpty()) {
         throw new IllegalStateException();
      }
      Stack<String> temp = new Stack<>();
      while (!history.isEmpty()) {
         String song = history.pop();
         System.out.println("    " + song);
         temp.push(song);
      }
      System.out.println();
      while (!temp.isEmpty()) {
         history.push(temp.pop());
      }
   }

   // Behavior: 
   //   - Clears the entire history of played songs.
   public void clearHistory() {
      history.clear();
   } 

   // Behavior: 
   //   - Deletes a specified number of songs from the play history.
   //   - A positive number deletes from the recent history, while a negative 
    //    number deletes from the beginning (chronological song play order)
   // Parameters:
   //   - number: an integer representing the number of songs to be deleted. 
   // Exceptions:
    //   - If the absolute value of number of songs to delete from history is greater than 
    //     the music history size, an IllegalArgumentException is thrown.
   public void deleteFromHistory(int number) {
      if (Math.abs(number) > history.size()) {
         throw new IllegalArgumentException();
      }
      
      if (number > 0) {
         for (int i = 0; i < number; i++) {
            history.pop();
         }
      } else if (number < 0) {
         Stack<String> tempS = new Stack<>();
         
         while(!history.isEmpty()){
            tempS.push(history.pop());
         }
         int num = Math.abs(number);
         
         while(num != 0){
            tempS.pop();
            num--;
         }
         
         while(!tempS.isEmpty()){
            history.push(tempS.pop());
         }
      }
   }

   // Behavior: 
   //   - Retrieves the size of the music playlist.
   // Return:
   //   - int: an integer representing the number of songs in the music playlist.
   public int getPlaylistSize() {
      return playlist.size();
   }
   
   // Behavior: 
   //   - Retrieves the size of the music history.
   // Return:
   //   - int: an integer representing the number of songs in the music history.
   public int getHistorySize() {
      return history.size();
   }
}