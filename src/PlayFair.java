/*
This is the code for Playfair cipher technique
see more here https://en.wikipedia.org/wiki/Playfair_cipher
if you have any questions/suggestions, feel free to contact us about it
Thanks for reading this, enjoy the rest of your day :)
*/
import java.util.Scanner;

public class PlayFair {

    static protected void createTable(char[][] Arr, String key){
        boolean[] check = new boolean[26];
        for (int i = 0; i < 25; i++)
            check[i] = false;
        int n = key.length();
        int cur = 0;
        for (int i = 0; i < n; i++) {
            char c = key.charAt(i);
            if (!check[c - 'a']){
                check[c - 'a'] = true;
                if (c == 'i' || c == 'j')
                    check['i' - 'a'] = check['j' - 'a'] = true;
                Arr[cur/5][cur%5] = c;
                cur++;
            }
        }
        for (char i = 'a'; i <= 'z'; i++){
            if (i == 'j' || check[i - 'a'])
                continue;
            Arr[cur/5][cur%5] = i;
            cur++;
        }
    }

    static protected String find(char[][] Arr, char a){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                if (Arr[i][j] == a)
                    return i + "" + j;
            }
        }
        return "";
    }

    static protected String encrypt(String Plain, String key){
        String encrypted = "";
        int n = Plain.length();

        char [][] Arr = new char[26][26];
        createTable(Arr, key);

        int cur = 0;
        do {
            char a, b;
            if (cur == n - 1){
                a = Plain.charAt(cur);
                b = 'x';
            }
            else{
                a = Plain.charAt(cur);
                b = Plain.charAt(cur + 1);
                if (a == b){
                    b = 'x';
                    cur--;
                }
            }
            cur += 2;

            String posA = find(Arr, a);
            String posB = find(Arr, b);
            //chu nhat
            char tmpA = Arr[posA.charAt(0) - '0'][posB.charAt(1) - '0'];
            char tmpB = Arr[posB.charAt(0) - '0'][posA.charAt(1) - '0'];
            //cung dong
            if (posA.charAt(0) == posB.charAt(0)) {
                tmpA = Arr[posA.charAt(0) - '0'][(posA.charAt(1) - '0' + 1) % 5];
                tmpB = Arr[posB.charAt(0) - '0'][(posB.charAt(1) - '0' + 1) % 5];
            }
            //cung cot
            if (posA.charAt(1) == posB.charAt(1)){
                tmpA = Arr[(posA.charAt(0) - '0' + 1) % 5][posA.charAt(1) - '0'];
                tmpB = Arr[(posB.charAt(0) - '0' + 1) % 5][posB.charAt(1) - '0'];
            }
            encrypted += tmpA + "" + tmpB;
        } while (cur < n);
        return encrypted;
    }

    static String fix(String decrypted){
        int n = decrypted.length();
        String res = "";
        for (int i = 0; i < n - 2; i += 2){
            char x = decrypted.charAt(i);
            char y = decrypted.charAt(i + 1);
            res += x;
            if (y != 'x' || x != decrypted.charAt(i + 2))
                res += y;
        }
        res += decrypted.charAt(n - 2);
        if (decrypted.charAt(n - 1) != 'x'){
            res += decrypted.charAt(n - 1);
        }
        return res;
    }

    static protected String decrypt(String encrypted, String key){
       String decrypted = "";
       int n = encrypted.length();

       char [][] Arr = new char[26][26];
       createTable(Arr, key);
       int cur = 0;

       for (int i = 0; i < n; i += 2){
           String posA = find(Arr, encrypted.charAt(i));
           String posB = find(Arr, encrypted.charAt(i + 1));
           char tmpA = Arr[posA.charAt(0) - '0'][posB.charAt(1) - '0'];
           char tmpB = Arr[posB.charAt(0) - '0'][posA.charAt(1) - '0'];
           //cung dong
           if (posA.charAt(0) == posB.charAt(0)) {
               tmpA = Arr[posA.charAt(0) - '0'][(posA.charAt(1) - '0' - 1 + 5) % 5];
               tmpB = Arr[posB.charAt(0) - '0'][(posB.charAt(1) - '0' - 1 + 5) % 5];
           }
           //cung cot
           if (posA.charAt(1) == posB.charAt(1)){
               tmpA = Arr[(posA.charAt(0) - '0' - 1 + 5) % 5][posA.charAt(1) - '0'];
               tmpB = Arr[(posB.charAt(0) - '0' - 1 + 5) % 5][posB.charAt(1) - '0'];
           }
           decrypted += tmpA + "" + tmpB;
       }

       decrypted = fix(decrypted);
       return decrypted;
    }

    public static void main(String[] args) {
        //input
        Scanner sc = new Scanner(System.in);
        System.out.print("input plain message: ");
        String Plain = sc.nextLine();
        System.out.print("input key: ");
        String Key = sc.nextLine();

        //output
        String encrypted = encrypt(Plain, Key);
        System.out.println(encrypted);
        System.out.println(decrypt(encrypted, Key));
    }
}
