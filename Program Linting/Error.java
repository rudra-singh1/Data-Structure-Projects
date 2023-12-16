package Program Linting;

// This class represents errors identified during linting, encapsulating information 
// such as error code, line number, and message details.
public class Error {

    private int code;
    private int lineNumber;
    private String message;

    // Behavior: 
    //   - Initializes an Error object with specified code, line number, and message.
    // Parameters:
    //   - code: an integer representing the error code
    //   - lineNumber: an integer representing the line number where the error was detected
    //   - message: a String of the message describing the error.
    public Error(int code, int lineNumber, String message){
        this.code = code;
        this.lineNumber = lineNumber;
        this.message = message;
    }

    // Behavior: 
    //   - Generates a message representing the error qualities.
    // Returns:
    //   - output: a String representing the error information.
    public String toString(){
        String output = "(Line: " + lineNumber + ") has error code " + code + "\n" + message;
        return output;
    }

    // Behavior: 
    //   - Retrieves the area of code where the error occurred.
    // Returns:
    //   - lineNumber: an integer representing the line number where the error occurred.
    public int getLineNumber(){
        return lineNumber;
    }

    // Behavior: 
    //   - Retrieves the error code associated with the error.
    // Returns:
    //   - code: an integer representing the error code associated with the error.
    public int getCode(){
        return code;
    }

    // Behavior: 
    //   - Retrieves the description of the error.
    // Returns:
    //   - message: a String of the message describing the error.
    public String getMessage(){
        return message;
    }
}
