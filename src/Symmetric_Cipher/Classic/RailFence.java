package Symmetric_Cipher.Classic;

public class RailFence {
	protected static String encrypt(String plain, int key){
        int n = plain.length();
        if (key == 1 || key >= n)
        	return plain;
        String encrypted = "";
        char [][] tempArr = new char[key][n];
        int cur = 0;
        int increment = 1;
        for (int i = 0; i < key; i++)
        	for (int j = 0; j < n; j++) {
        		tempArr[i][j] = '-';
        	}
        for (int i = 0; i < n; i++) {
            tempArr[cur][i] = plain.charAt(i);
            cur += increment;
            if (((cur == 0) && (increment == -1)) || ((cur == key - 1) && (increment == 1))) {
                increment *= -1;
            }
        }
        for (int j = 0; j < key; j++){
            for (int i = 0; i < n; i++) {
                if (tempArr[j][i] != '-'){
                    encrypted += tempArr[j][i];
                }
            }
        }
        return encrypted;
    }

    protected static String decrypt(String encrypted, int key){
        int n = encrypted.length();
        if (key == 1 || key >  n)
    		return encrypted;
        String decrypted = "";
        char [][] tempArr = new char[key][n];
        int cur = 0;
        int increment;

        for (int i = 0; i < key; i++)
        	for (int j = 0; j < n; j++) {
        		tempArr[i][j] = '-';
        	}

        //different process on the 1st row
        for (int i = 0; i < n; i += 2*(key - 1)) {
            tempArr[0][i] = encrypted.charAt(cur);
            cur++;
        }
        for (int i = 1; i < key - 1; i++){
            increment = i;
            for (int j = i; j < n; j += 2*increment){
                tempArr[i][j] = encrypted.charAt(cur);
                increment = key - increment - 1;
                cur++;
            }
        }
        //process differently on the last row
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
}
