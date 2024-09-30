import java.util.Arrays;

/*
 * Mã hóa theo mẫu hình học
 */

public class TranspositionCipher {
	
	//subclass to keep track of a character's original index after being sorted
    static class CharIndex{
        char c;
        int index;
        
        CharIndex(){}
        
        CharIndex(char c, int index){
        	this.c = c;
        	this.index = index;
        }
    }

    protected static String encrypt(String Plain, String Key){
        String encrypted ="";

        //get the length of both strings
        int n = Plain.length();
        int m = Key.length();

        //sort the key's characters alphabetically
        CharIndex[] tempArr = new CharIndex[m];
        for (int i = 0; i < m; i++) {
            tempArr[i] = new CharIndex(Key.charAt(i), i);
        }
        Arrays.sort(tempArr, (a, b) -> a.c - b.c);

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

        /*sort the key's characters alphabetically */
        CharIndex[] tempArr = new CharIndex[m];
        for (int i = 0; i < m; i++) {
            tempArr[i] = new CharIndex(Key.charAt(i), i);
        }
        Arrays.sort(tempArr, (a, b) -> a.c - b.c);

        /* the maximum and minimum number of rows in the table above
        if n is divisible by m then max = min = n/m
        else the table will have 1 extra rows at columns that has index < n % m
        */
        int maxRow, minRow;
        maxRow = minRow = n / m;
        if (n%m != 0)
            maxRow++;

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
}
