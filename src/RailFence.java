/*
This is the code for Rail Fence cipher technique (2.3.1 in the book)
see more about it here: https://en.wikipedia.org/wiki/Rail_fence_cipher
the article says that there are three rails in the cipher method, but
we are taught to use  custom keys in school book, so this program uses custom keys, too
At uni, we were taught to do as following:
plain text: abcdef
rewriting in Rail Fence (with 3 rows):
a . . d . .
. b . . e .
. . c . . f
-> cipher: adbecf
if you have any questions/suggestions, feel free to contact us about it
Thanks for reading this, enjoy the rest of your day :)
*/

import java.util.Scanner;

public class RailFence {
    protected static String encrypt(String Plain, int NumKey){
        String Encrypted = "";
        int n = Plain.length();

        /* the indexes of the numbers on the same row will have the same remainder when modulo NumKey
        so we iterate each remainders and add corresponding characters to Encrypted string
         */
        for (int i = 0; i < NumKey; i++){
            for (int j = i; j < n; j += NumKey){
                Encrypted += Plain.charAt(j);
            }
        }
        return Encrypted;
    }

    protected static String decrypt(String Encrypted, int NumKey){
        String Decrypted = "";
        int n = Encrypted.length();

        //max colum in the original message table, and cur will keep track of the current character in Encrypted text
        int maxCol = (int)Math.ceil((float)n / NumKey);
        int cur = 0;
        char[][] tempArr = new char [NumKey][maxCol];
        for (int i = 0; i < NumKey; i++){
            int col = n / NumKey;
            if (i < (n % NumKey))
                col++;
            for (int j = 0; j < col; j++){
                tempArr[i][j] = Encrypted.charAt(cur);
                cur++;
            }
        }
        for (int j = 0; j < maxCol; j++){
            for (int i = 0; i < NumKey; i++){
                if (tempArr[i][j] >= 'a' && tempArr[i][j] <= 'z')
                    Decrypted += tempArr[i][j];
            }
        }
        return Decrypted;
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
