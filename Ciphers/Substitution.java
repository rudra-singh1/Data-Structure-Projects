//Substitution class extends the Cipher class. It embodies an encryption algorithm 
//where each input character's index is assigned and subsequently replaced 
//with the corresponding output character from the shifter alphabet supplied to the 
//Substituiton class.
public class Substitution extends Cipher{

    //Fields
    private String shifter; 

    //Behavior:
    //  -Constructs a new Substitution Cipher with an empty shifter.
    public Substitution(){
        this.shifter = "";
    }

    //Behavior: 
    //  -Constructs a new Substitution Cipher with the provided shifter.
    //Parameters:
    //  -shifter: the shifter string
    //Exceptions:
    //  -IllegalArgumentException: thrown if the length of the shifter doesn't match the  
    //   number of characters within Substitution Cipher's encodable range, contains a  
    //   duplicate character, or any character falls outside the encodable range
    public Substitution(String shifter){
        setShifter(shifter);
    }

    // Behavior:
    //   -Updates the shifter for the Substitution Cipher.
    // Parameters:
    //   -shifter: the new shifter string.
    // Exceptions:
    //   -IllegalArgumentException: thrown if the length of the shifter doesn't match the  
    //   number of characters within Substitution Cipher's encodable range, contains a  
    //   duplicate character, or any character falls outside the encodable range
    public void setShifter(String shifter){
        if((shifter.length() != Cipher.TOTAL_CHARS) || (duplicateCheck(shifter)) || 
        isStringEncodable(shifter)){
            throw new IllegalArgumentException();
        }
        this.shifter = shifter;
    }
    
    //Behaviour:
    //  - Applies this Substitution Cipher to the input, returning the result
    //Parameters:
    //  - input: the string to encrypt
    //Returns:
    //  -String: the encrypted result
    //Exceptions:
    //  -IllegalStateException: thrown if shifter is null or empty
    public String encrypt(String input){
        if(shifter == null || shifter.length()==0){
            throw new IllegalStateException();
        }

        String encrypted = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i); // char c = b

            int index = (int) c - Cipher.MIN_CHAR; 
            encrypted += shifter.charAt(index);

        }
        return encrypted;
    }

    //Behaviour:
    //  - Inverses this Substitution Cipher on the input, returning the result.
    //Parameters:
    //  - input: the string to decrypt
    //Returns:
    //  -String: the decrypted result
    //Exceptions:
    //  -IllegalStateException: thrown if shifter is null or empty
    public String decrypt(String input){
        if(shifter == null || shifter.length()==0){
            throw new IllegalStateException ();
        }

        String decrypted = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int index = shifter.indexOf(c);

            decrypted += (char) (index + MIN_CHAR);
        }
        return decrypted;
    }

    //Behaviour:
    //  - Checks for duplicate characters in a given string.
    //Parameters:
    //  - s: the input string to check for duplicates.
    //Returns:
    //  -boolean: true if duplicates exist, false otherwise.
    private boolean duplicateCheck(String s){
        for (int i = 0; i < (s.length() - 1); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Behaviour:
    //  - Checks if any character in the input string falls outside the encodable range.
    //Parameters:
    //  - input: the input string to check.
    //Returns:
    //  -boolean: true if any character is outside the encodable range, false otherwise.
    private boolean isStringEncodable(String input){
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int charCode = (int) currentChar;
            
            if (charCode < Cipher.MIN_CHAR || charCode > Cipher.MAX_CHAR) {
                return true; 
            }
        }
        return false; 
    }
}