package Symmetric_Cipher.Classic;

public class VingenereCipher {
	protected static String encrypt(String Plain, String Key){
        String encrypted = "";
        int n = Plain.length();
        int m = Key.length();

        for (int i = 0; i < n; i++) {
            //i: i-th character on Plain message
            //i%m: corresponding position on Key message
            char PlainChar = Plain.charAt(i);
            char KeyChar = Key.charAt(i % m);

            //KeyChar's position on the alphabet
            int KeyPos = KeyChar - 'a';
            //Push PlainChar up *KeyPos* characters on the alphabet
            PlainChar += KeyPos;
            if (PlainChar > 'z')
                PlainChar -= 26;
            encrypted += PlainChar;
        }
        return encrypted;
    }

    protected static String decrypt(String encrypt, String Key){
        String decrypted = "";
        int n = encrypt.length();
        int m = Key.length();

        for (int i = 0; i < n; i++) {
            char encryptChar = encrypt.charAt(i);
            char KeyChar = Key.charAt(i%m);
            int KeyPos = KeyChar - 'a';
            encryptChar -= KeyPos;
            if (encryptChar < 'a')
                encryptChar += 26;
            decrypted += encryptChar;
        }
        return decrypted;
    }
}
