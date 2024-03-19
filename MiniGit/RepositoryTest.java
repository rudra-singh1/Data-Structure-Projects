import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class RepositoryTest {
    private Repository repo1;
    private Repository repo2;

    /**
     * NOTE: The following test suite assumes that getRepoHead(), commit(), and size()
     *       are implemented correctly.
     */

    @BeforeEach
    public void setUp() {
        repo1 = new Repository("repo1");
        repo2 = new Repository("repo2");
        Repository.Commit.resetIds();
    }

    @Test
    @DisplayName("Test getHistory()")
    public void getHistory() {
        // Initialize commit messages
        String[] commitMessages = new String[]{"Initial commit.", "Updated method documentation.",
                                                "Removed unnecessary object creation."};

        // Commit the commit messages to repo1
        for (int i = 0; i < commitMessages.length; i++) {
            String commitMessage = commitMessages[i];
            repo1.commit(commitMessage);

            // Assert that the current commit id is at the repository's head
            // We know our ids increment from 0, meaning we can just use i as our id
            assertEquals("" + i, repo1.getRepoHead());
        }

        assertEquals(repo1.getRepoSize(), commitMessages.length);

        // This is the method we are testing for. First, we'll obtain the 2 most recent commits
        // that have been made to repo1.
        String repositoryHistory = repo1.getHistory(2);
        String[] commits = repositoryHistory.split("\n");

        // Verify that getHistory() only returned 2 commits.
        assertEquals(commits.length, 2);

        // Verify that the 2 commits have the correct commit message and commit id
        for (int i = 0; i < commits.length; i++) {
            String commit = commits[i];

            // Old commit messages/ids are on the left and the more recent commit messages/ids are
            // on the right so need to traverse from right to left to ensure that 
            // getHistory() returned the 2 most recent commits.
            int backwardsIndex = (commitMessages.length - 1) - i;
            String commitMessage = commitMessages[backwardsIndex];

            assertTrue(commit.contains(commitMessage));
            assertTrue(commit.contains("" + backwardsIndex));
        }
    }

    @Test
    @DisplayName("Test drop() (empty case)")
    public void testDropEmpty() {
        assertFalse(repo1.drop("123"));
    }

    @Test
    @DisplayName("Test drop() (front case)")
    public void testDropFront() {
        assertEquals(repo1.getRepoSize(), 0);
        // Initialize commit messages
        String[] commitMessages = new String[]{"First commit.", "Added unit tests."};

        // Commit to repo1 - ID = "0"
        repo1.commit(commitMessages[0]);

        // Commit to repo2 - ID = "1"
        repo2.commit(commitMessages[1]);

        // Assert that repo1 successfully dropped "0"
        assertTrue(repo1.drop("0"));
        assertEquals(repo1.getRepoSize(), 0);
        
        // Assert that repo2 does not drop "0" but drops "1"
        // (Note that the commit ID increments regardless of the repository!)
        assertFalse(repo2.drop("0"));
        assertTrue(repo2.drop("1"));
        assertEquals(repo2.getRepoSize(), 0);
    }

    /* TODO - Add additional unit tests */
    @Test
    @DisplayName("Test Repository constructor ")
    public void testRepositoryConstructor() {
        //null name
        assertThrows(IllegalArgumentException.class, () -> {new Repository(null);});

        //empty name
        assertThrows(IllegalArgumentException.class, () -> {new Repository("");});

        //valid name
        Repository newRepo = new Repository("newRepo");
        int found = newRepo.toString().indexOf(" - ");
        assertEquals("newRepo", newRepo.toString().substring(0, found));
        assertEquals(null, newRepo.getRepoHead());
    }

    

    @Test
    @DisplayName("Test getRepoHead()")
    public void testRepoHead(){
        repo1.commit("first commit");
        repo1.commit("second commit");
        assertEquals("1", repo1.getRepoHead());

        //null case
        assertEquals(null, repo2.getRepoHead());
    }



    @Test
    @DisplayName("Test getRepoSize()")
    public void testRepoSize(){
        repo1.commit("first commit");
        repo1.commit("second commit");
        assertEquals(2, repo1.getRepoSize());
    }

    

    @Test
    @DisplayName("Test toString()")
    public void testToString(){
        repo1.commit("first commit");
        repo1.commit("second commit");
        String temp = repo1.toString();
        int index = temp.indexOf("second commit");
        assertEquals("second commit", temp.substring(index, temp.length()));

        int indexTwo = temp.indexOf("1");
        assertEquals("1", temp.substring(indexTwo, indexTwo + 1));

        //no commit case
        assertEquals("repo2 - No commits", repo2.toString());
    }



    @Test
    @DisplayName("Test contains()")
    public void testContains() {
        //with existing commit
        repo1.commit("first commit");
        repo1.commit("second commit");
        assertTrue(repo1.contains("0"));

        //with non-existing commit
        repo2.commit("first commit");
        assertFalse(repo2.contains("1"));
    }



    @Test  
    @DisplayName("Test commit()")
    public void testCommit(){
        //single commit
        String commitId1 = repo1.commit("first commit");
        assertEquals(commitId1, repo1.getRepoHead());
        assertEquals(1, repo1.getRepoSize());
        assertTrue(repo1.contains(commitId1));

        //multiple commit
        repo2.commit("first commit");
        repo2.commit("second commit");
        String commitId4 = repo2.commit("third commit");
        assertEquals(commitId4, repo2.getRepoHead());
        assertEquals(3, repo2.getRepoSize());
    }



    @Test
    @DisplayName("Test synchronize() (empty other repository case)")
    public void testSynchronizeWithEmptyOtherRepository() {
        repo1.commit("first commit");
        repo1.commit("second commit");
        int repo1SizeBefore = repo1.getRepoSize();

        repo1.synchronize(repo2);

        assertEquals(repo1SizeBefore, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());
    }

    @Test
    @DisplayName("Test synchronize() (empty current repository case)")
    public void testSynchronizeWithEmptyCurrentRepository() {
        repo2.commit("first commit");
        repo2.commit("second commit");
        int repo2SizeBefore = repo2.getRepoSize();

        repo1.synchronize(repo2);

        assertEquals(repo2SizeBefore, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());
    }

    @Test
    @DisplayName("Test synchronize() (non-empty repositories case)")
    public void testSynchronizeWithNonEmptyRepositories() {
        repo1.commit("first commit");
        repo1.commit("second commit");
        repo2.commit("third commit");
        repo2.commit("fourth commit");
        int repo1SizeBefore = repo1.getRepoSize();
        int repo2SizeBefore = repo2.getRepoSize();

        repo1.synchronize(repo2);

        assertEquals(repo1SizeBefore + repo2SizeBefore, repo1.getRepoSize());
        assertEquals(0, repo2.getRepoSize());
    }

    
}