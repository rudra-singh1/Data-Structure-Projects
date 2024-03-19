import java.util.List;

public class MultiCipher extends Cipher{
    private List<Cipher> ciphers;

    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException("Cipher list cannot be null");
        }
        this.ciphers = ciphers;
    }

    public String encrypt(String input) {
        String result = input;
        for (Cipher cipher : ciphers) {
            result = cipher.encrypt(result);
        }
        return result;
    }

    public String decrypt(String input) {
        String result = input;
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            result = ciphers.get(i).decrypt(result);
        }
        return result;
    }
}
