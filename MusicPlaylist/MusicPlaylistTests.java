package MusicPlaylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MusicPlaylistTests {

    private MusicPlaylist playlist;

    @BeforeEach
    public void setUp() {
        playlist = new MusicPlaylist();
    }

    @Test
    public void testAddSong() {
        playlist.addSong("Song 1");
        assertTrue(playlist.getPlaylistSize() == 1);
    }

    @Test
    public void testPlaySong() {
        playlist.addSong("Song 1");
        playlist.playSong();
        assertTrue(playlist.getPlaylistSize() == 0);
        assertTrue(playlist.getHistorySize() == 1);
    }

    @Test
    public void testPrintHistory() {
        playlist.addSong("Song 1");
        playlist.addSong("Song 2");
        playlist.playSong();
        playlist.playSong();
        playlist.printHistory();
        assertTrue(playlist.getHistorySize() == 2);
    }

    @Test
    public void testClearHistory() {
        playlist.addSong("Song 1");
        playlist.playSong();
        playlist.clearHistory();
        assertTrue(playlist.getHistorySize() == 0);
    }

    @Test
    public void testDeleteFromHistoryNegative() {
        playlist.addSong("Song 1");
        playlist.addSong("Song 2");
        playlist.addSong("Song 3");
        
        playlist.playSong();
        playlist.playSong();
        playlist.playSong();
        playlist.deleteFromHistory(-2);
        assertTrue(playlist.getHistorySize() == 1);
    }

    @Test
    public void testDeleteFromHistoryPositive() {
        playlist.addSong("Song 1");
        playlist.addSong("Song 2");
        playlist.addSong("Song 3");
        
        playlist.playSong();
        playlist.playSong();
        playlist.playSong();
        playlist.deleteFromHistory(2);
        assertTrue(playlist.getHistorySize() == 1);
    }
}
