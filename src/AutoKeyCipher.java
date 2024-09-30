
public class AutoKeyCipher {
	protected static String encrypt(String Plain, String Key){
        String encrypted = "";
        int n = Plain.length();

        for (int i = 0; i < n; i++) {
            //i: i-th character on Plain message
            //i%m: corresponding position on Key message
            char PlainChar = Plain.charAt(i);
            char KeyChar = Key.charAt(i);
            if (PlainChar < 'a' || PlainChar > 'z')
            	continue;
            
            //append the current character of plain to Key
            Key += PlainChar;

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

        for (int i = 0; i < n; i++) {
            char encryptChar = encrypt.charAt(i);
            if (encryptChar < 'a' || encryptChar > 'z')
            	continue;
            char KeyChar = Key.charAt(i);
            int KeyPos = KeyChar - 'a';
            encryptChar -= KeyPos;
            if (encryptChar < 'a')
                encryptChar += 26;
            decrypted += encryptChar;
            Key += encryptChar;
        }
        return decrypted;
    }
}
