/*
This file contains the code of substitution cipher technique (2.2.2 in the book)
see more about the cipher here: https://en.wikipedia.org/wiki/Substitution_cipher
if you have any questions/suggestions, feel free to contact us about it
Thanks for reading this and enjoy the rest of your day :)
 */

import java.util.Scanner;

public class DonBang {
    protected static String encrypt(String Plain, String Key){
        String Encrypted = "";
        int n = Plain.length();

        for (int i = 0; i < n; i++) {
            char c = Plain.charAt(i);
            //c's position in the alphabet, starting with a at position 0
            int pos = c - 'a';
            //adding the pos-th character into the encrypted message
            Encrypted += Key.charAt(pos);
        }
        return Encrypted;
    }

    protected static String decrypt(String encrypted, String Key){
        String Decrypted = "";
        int n = encrypted.length();

        for (int i = 0; i < n; i++) {
            char c = encrypted.charAt(i);
            //looking for the character's position in the key string
            int pos = 0;
            for (int j = 0; j < Key.length(); j++) {
                if (Key.charAt(j) == c) {
                    pos = j;
                    break;
                }
            }
            //add the corresponding character (pos-th character) on the alphabet
            Decrypted += (char)('a' + pos);
        }
        return Decrypted;
    }

    public static void main(String[] args) {
        //the key used in this method is 26 letters long, corresponding to 26 letters in the English alphabet
        //'a' will be replaced with the 1st letter in the key, 'b' is 2nd, 'c' is 3rd, etc.
        String StringKey;

        //for testing purposes, here's a sample key where I pressed 26 letters on my keyboard
        //StringKey = "qazwsxedcrfvtgbyhnujmikolp"

        //input
        Scanner scan = new Scanner(System.in);
        System.out.print("Input Plain message: ");
        String Plain = scan.nextLine();
        System.out.println("Input Key: ");
        StringKey = scan.nextLine();
      
        //encrypt and decrypt
        String encrypted = encrypt(Plain, StringKey);
        System.out.println("encrypted Plain message: " + encrypted);
        String decrypted = decrypt(encrypted, StringKey);
        System.out.println("decrypted Plain message: " + decrypted);
    }
}
