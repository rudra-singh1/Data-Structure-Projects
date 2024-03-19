//CaesarKey class extends the Substitution class.
//CaesarKey performs an encryption where it placing a key at the front of 
// the substitution, with the rest of the alphabet following normally 
// (minus the characters included in the key)
public class CaesarKey extends Substitution{

    //Fields
    private String key;
    
    // Behavior:
    //   - Constructs a new CaesarKey with the provided key value.
    // Parameters:
    //   - key: the string key 
    // Exceptions:
    //   - IllegalArgumentException: thrown if the key is empty, it contains characters 
    //     outside the valid range of characters, it contains any duplicate characters or
    //     the length of the shifter doesn't match the number of characters within cipher's 
    //     encodable range
    public CaesarKey(String key) {
        if(key.length() == 0){
            throw new IllegalArgumentException();
        } 
        this.key = key;
        super.setShifter(shiftedLetters());
    }
    
    // Behavior:
    //   - Creates a shifter string by placing the key at the front 
    //     and appending the rest of the encodable range.
    //   - When appending rest of encodable range, characters within key will 
    //     NOT be appended.
    // Returns:
    //   - String: the shifter string.
    private String shiftedLetters(){
        String shiftedAlphabet = key; 

            for(int i = super.MIN_CHAR; i <= super.MAX_CHAR; i++){
                if(key.indexOf(((char) i)) == -1){
                    shiftedAlphabet += ((char) i);
                }
            } 

        return shiftedAlphabet;
    }
}