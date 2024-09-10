/*
This is the code for Vingenere's cipher technique, but with autokey (2.2.4 in the book)
see more here https://en.wikipedia.org/wiki/Autokey_cipher
if you have any questions/suggestions, feel free to contact us about it
Thanks for reading this, enjoy the rest of your day :)
*/

import java.util.Scanner;

public class AutoKey extends Vingenere {
    protected static String GenerateNewKey(String Plain, String key){
        String NewKey = key ;
        int n = Plain.length();
        int m = key.length();
        //we connect plain with key to make a new key keyplain
        //since key already has m characters, we only need to add (m-n) characters (if m < n, of course)
        if (m < n)
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

        //output
        Key = GenerateNewKey(Plain, Key);
        System.out.println("Auto generated key: " + Key);
        String encrypted = encrypt(Plain, Key);
        System.out.println("Encrypted message: " + encrypted);
        String decrypted = decrypt(encrypted, Key);
        System.out.println("Decrypted message: " + decrypted);
    }
}
