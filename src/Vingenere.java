import java.util.Scanner;

public class Vingenere {

    private static String encrypt(String Plain, String Key){
        String encrypted = "";
        int n = Plain.length();
        int m = Key.length();

        for (int i = 0; i < n; i++) {
            //i: i-th character on Plain message
            //i%m: corresponding position on Key message
            char PlainChar = Plain.charAt(i);
            char KeyChar = Key.charAt(i%m);

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

    private static String decrypt(String encrypt, String Key){
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

    private static String GenerateNewKey(String Plain, String key){
        String NewKey = key ;
        int n = Plain.length();
        int m = key.length();
        NewKey += Plain.substring(0, n - m);
        return NewKey;
    }

    public static void main(String[] args) {
        //input
        Scanner sc = new Scanner(System.in);
        System.out.print("input plain message: ");
        String Plain = sc.nextLine();
        System.out.print("input key: ");
        String Key = sc.nextLine();

        //encrypt and decrypt
        String encrypted = encrypt(Plain, Key);
        System.out.println("Encrypted message: " + encrypted);
        String decrypted = decrypt(encrypted, Key);
        System.out.println("Decrypted message: " + decrypted);
    }
}