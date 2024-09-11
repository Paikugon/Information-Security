/*
This file contains the code of transposition cipher technique (2.3.2 in the book)
see more about the cipher here: https://en.wikipedia.org/wiki/Transposition_cipher
if you have any questions/suggestions, feel free to contact us about it
Thanks for reading this and enjoy the rest of your day :)

This is the hardest one that I've done so far. It's hard, but quite easy to code once I've figured it out
the idea is: when rewriting the message in a table, we'll notice the relationship between row number
and key length. Let me clarify that with an example:
Plain text: thisislonk
key: code (length: 4)

rewritten text:
c o d e (key)
1 4 3 2 (key letters in alphabetical order)
t h i s
i s l o
n k * * (* is a dummy character to fill in the blanks, I tried to code without them, but it's quite miserable to decrypt)
We'll take each column corresponding to the key letters' order, which give us this
encrypted text: tin so il hsk (I grouped them for visibility)
You'll notice that the 1st column's indexes give the same result when modulo key length, which is 4
(0, 4 and 8). The same goes for the other columns.
We'll use that to our advantage
 */

import java.util.Arrays;
import java.util.Scanner;

public class Transposition {

    //class to keep track of a character's original index after being sorted
    static class CharIndex{
        char c;
        int index;
    }

    protected static String encrypt(String Plain, String Key){
        String encrypted ="";

        //get the length of both strings
        int n = Plain.length();
        int m = Key.length();

        //sort the key's characters alphabetically
        CharIndex[] tempArr = new CharIndex[m];
        for (int i = 0; i < m; i++) {
            tempArr[i] = new CharIndex();
            tempArr[i].c = Key.charAt(i);
            tempArr[i].index = i;
        }
        Arrays.sort(tempArr, (a,b) -> a.c - b.c);

        for (int i = 0; i < m; i++) {
            //current remaining
            int curRem = tempArr[i].index;
            //iterate through the plain message
            for (int j = curRem; j < n; j += m) {
                encrypted += Plain.charAt(j);
            }
        }
        return encrypted;
    }

    protected static String decrypt(String encrypted, String Key){
        String decrypted ="";

        //get the length of both strings
        int n = encrypted.length();
        int m = Key.length();

        /*sort the key's characters alphabetically, again lol
        (I should probably make a separate function at this point, but I don't think it's really
        worth it if it only has 4 lines of code, being used only twice, anyway) */
        CharIndex[] tempArr = new CharIndex[m];
        for (int i = 0; i < m; i++) {
            tempArr[i] = new CharIndex();
            tempArr[i].c = Key.charAt(i);
            tempArr[i].index = i;
        }
        Arrays.sort(tempArr, (a,b) -> a.c - b.c);

        /* the maximum and minimum number of rows in the table above
        if n is divisible by m then max = min = n/m
        else the table will have 1 extra rows at columns that has index < n % m
        */
        int maxRow, minRow;
        maxRow = minRow = n / m;
        if (n%m != 0)
            maxRow++;

        /*It's literally 2:30am and I have class in 5 hours, i can't think of any other way than
        * putting all of them in a table, so let's do that instead of thinking of a more complicating stuff
        * The columns with Index < n % m will have maxRow row(s), else it will have minRow row(s) */
        char[][] temp = new char[m][maxRow];
        int cur = 0;
        for (int i = 0; i < m; i++) {
            int iterate = minRow;
            if (tempArr[i].index < (n % m)){
                iterate++;
            }
            /* tempArr[i].index is currently holding the original index of the character in the key
            * so I'm using it to directly allocate the characters to the original table */
            for (int j = 0; j < iterate; j++) {
                temp[tempArr[i].index][j] = encrypted.charAt(cur);
                cur++;
            }
        }
        for (int j = 0; j < maxRow; j++) {
            for (int i = 0; i < m; i++) {
                if (temp[i][j] >= 'a' && temp[i][j] <= 'z') {
                    decrypted += temp[i][j];
                }
            }
        }
        return decrypted;
    }

    public static void main(String[] args) {
        //input
        Scanner sc = new Scanner(System.in);
        System.out.print("Input plain message: ");
        String plainMessage = sc.nextLine();
        System.out.print("Input Key: ");
        String key = sc.nextLine();

        //output
        String encrypted = encrypt(plainMessage, key);
        System.out.println("Encrypted Message: " + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Message: " + decrypted);
    }
}
