import java.util.List;

//MultiCipher class extends the Substitution class.
//MultiCipher allows the encryption or decryption of a 
//message by sequentially applying a list of internal ciphers.
public class MultiCipher extends Substitution{
    //Fields
    private List<Cipher> ciphers;

    // Behavior:
    //   - Constructs a new MultiCipher with the provided List of Ciphers.
    // Parameters:
    //   - ciphers: the list of internal ciphers to be applied sequentially.
    // Exceptions:
    //   - IllegalArgumentException: thrown if the given list is null.
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException("Cipher list cannot be null");
        }
        this.ciphers = ciphers;
    }

    // Behavior:
    //   - Applies the MultiCipher Cipher on the input, returning the result.
    // Parameters:
    //   - input: the string to encrypt
    // Returns:
    //   - String: the final encrypted result.
    public String encrypt(String input) {
        String result = input;
        for (Cipher cipher : ciphers) {
            result = cipher.encrypt(result);
        }
        return result;
    }

    // Behavior:
    //   - Inverses the MultiCipher Cipher on the input, returning the result.
    // Parameters:
    //   - input: the string to decrypt
    // Returns:
    //   - String: the final decrypted result.
    public String decrypt(String input) {
        String result = input;
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            result = ciphers.get(i).decrypt(result);
        }
        return result;
    }
}