package Symmetric_Cipher.BlockCipher;

import java.io.*;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCipher {
	private static final String Algo = "AES";
	private static final String ENCRYPTION_KEY = "encryptionKey";
	
	public static SecretKey generateKey (String key) {
		byte[] keyBytes = key.getBytes();
        return new SecretKeySpec(keyBytes, Algo);
	}
	
	public static String encrypt(String text, String Key) throws Exception {
        SecretKey key = generateKey(Key);
        Cipher cipher = Cipher.getInstance(Algo);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrytedBytes = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encrytedBytes);
    }
	
	public static String decrypt(String text, String Key) throws Exception {
		SecretKey key = generateKey(Key);
        Cipher cipher = Cipher.getInstance(Algo);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeByte = Base64.getDecoder().decode(text);
        byte[] decrytedBytes = cipher.doFinal(decodeByte);
        return new String (decrytedBytes);
    }
	
	public static String generateRegistrationKey(String username, String pass) {
		return username + ":" + pass + ":" + ENCRYPTION_KEY;
	}
	
	public static void SaveRegistrationKeytoFIle(String registrationKey, String filename) throws IOException{
		try (FileOutputStream fos = new FileOutputStream(filename);
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(registrationKey);
		}
	}
	
	public static String readRegistrationKeyFromFile(String filename) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		String res = (String) ois.readObject();
		ois.close();
		return res;
		
	}
}
