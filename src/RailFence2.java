/*
This is the code for Rail Fence cipher technique (2.3.1 in the book)
see more about it here: https://en.wikipedia.org/wiki/Rail_fence_cipher
the article says that there are three rails in the cipher method, but
we are taught to use  custom keys in school book, so this program uses custom keys, too
this is the original technique described in the wiki article
plain text: abcdef
rewriting in Rail Fence (with 3 rows):
a . . . e .
. b . d . f
. . c . . .
-> cipher: arbdfc
if you have any questions/suggestions, feel free to contact us about it
Thanks for reading this, enjoy the rest of your day :)
*/

import java.util.Scanner;

public class RailFence2 {

    protected static String encrypt(String plain, int key){
        String encrypted = "";
        int n = plain.length();
        char [][] tempArr = new char[key][n];
        int cur = 0;
        int increment = 1;
        for (int i = 0; i < n; i++) {
            tempArr[cur][i] = plain.charAt(i);
            cur += increment;
            if (((cur == 0) && (increment == -1)) || ((cur == key - 1) && (increment == 1))) {
                increment *= -1;
            }
        }
        for (int j = 0; j < key; j++){
            for (int i = 0; i < n; i++) {
                if (tempArr[j][i] <= 'z' && tempArr[j][i] >= 'a'){
                    encrypted += tempArr[j][i];
                }
            }
        }
        return encrypted;
    }

    protected static String decrypt(String encrypted, int key){
        String decrypted = "";
        int n = encrypted.length();
        char [][] tempArr = new char[key][n];
        int cur = 0;
        int increment;

        //different process on the 1st row
        for (int i = 0; i < n; i += 2*(key - 1)) {
            tempArr[0][i] = encrypted.charAt(cur);
            cur++;
        }
        for (int i = 1; i < key - 1; i++){
            increment = (key - 1) - i;
            for (int j = i; j < n; j += 2*increment){
                tempArr[i][j] = encrypted.charAt(cur);
                increment = key - increment - 1;
                cur++;
            }
        }

        //process different on the last row
        for (int i = key - 1; (i < n) && (cur < n); i += 2*(key - 1)) {
            tempArr[key - 1][i] = encrypted.charAt(cur);
            cur++;
        }

        increment = 1;
        cur = 0;
        for (int i = 0; i < n; i++){
            decrypted += tempArr[cur][i];
            if (((cur == 0) && (increment == -1)) || ((cur == key - 1) && (increment == 1))) {
                increment *= -1;
            }
            cur += increment;
        }
        return decrypted;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input Plain text: ");
        String PlainText = sc.nextLine();
        System.out.print("Input number key: ");
        int NumKey = sc.nextInt();

        String Encrypted = encrypt(PlainText, NumKey);
        System.out.println("Encrypted text: " + Encrypted);
        String decrypted = decrypt(Encrypted, NumKey);
        System.out.println("Decrypted text: " + decrypted);
    }
}
