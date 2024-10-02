package Symmetric_Cipher.Classic;

public class Caesar {
	protected static String encrypt(String Plain, int NumKey){
        String encrypted = "";
        int n = Plain.length();

        for (int i = 0; i < n; i++) {
            //i-th character in plain text
            char PlainChar = Plain.charAt(i);
            if (PlainChar < 'a' || PlainChar > 'z')
                continue;

            //push it up NumKey characters
            PlainChar += NumKey;

            //if it passes 'z', then push it back down 26 characters
            //'z' + 1 -> 'a', 'z' + 2 -> 'b', ...
            if (PlainChar > 'z')
                PlainChar -= 26;

            encrypted = encrypted + PlainChar;
        }
        return encrypted;
    }

    protected static String decrypt(String Crypted, int NumKey){
        String decrypted = "";
        int n = Crypted.length();

        for (int i = 0; i < n; i++) {
            //i-th character in encrypted text
            char EncryptedChar = Crypted.charAt(i);
            if (EncryptedChar < 'a' || EncryptedChar > 'z')
                continue;

            //push it down NumKey chatacters
            EncryptedChar -= NumKey;

            //if it smaller than 'a', push it up 26 characters again
            //'a' - 1 -> 'z', 'a' - 2 -> 'b', etc.
            if (EncryptedChar < 'a')
                EncryptedChar += 26;

            decrypted = decrypted + EncryptedChar;
        }
        return decrypted;
    }
}
