/* just using many techniques at once */

import java.util.Scanner;
public class multiTech {
    public static void main(String[] args) {
        //input
        Scanner sc = new Scanner(System.in);
        System.out.print("Input plain message: ");
        String message = sc.nextLine();
        System.out.print("Input key: ");
        String key = sc.nextLine();

        //encrypt
        System.out.println("---------------------------");
        String encrypted = Transposition.encrypt(message, key);
        System.out.println("Ciphered message using Transposition: " + encrypted);
        String newKey = AutoKey.GenerateNewKey(encrypted, key);
        System.out.println("New key generated using autokey " + newKey);
        encrypted = Vingenere.encrypt(encrypted, newKey);
        System.out.println("Ciphered message using Vingenere: " + encrypted);

        //decrypt
        System.out.println("---------------------------");
        String decrypted = Vingenere.decrypt(encrypted, newKey);
        System.out.println("Deciphered using Vingenere: " + decrypted);
        decrypted = Transposition.decrypt(decrypted, key);
        System.out.println("Deciphered using Transposition: " + decrypted);
    }
}
