package Program Linting;

// This class demonstrates scenarios to be used as test input in the 
// implemented checks of the Linter program.
public class TestFile {
    public static void main(String[] args) {
        System.out.println("This is a really really really long line that should fail the long line check");
        while (true) {
            System.out.println("");
            break;
        }
    }
}
