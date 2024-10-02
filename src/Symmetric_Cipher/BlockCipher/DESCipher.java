package Symmetric_Cipher.BlockCipher;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.management.openmbean.InvalidKeyException;

public class DESCipher {
	
	private static SecretKey generateKey(String Key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, java.security.InvalidKeyException {
			DESKeySpec keySpec = new DESKeySpec(Key.getBytes());
			SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			return kf.generateSecret(keySpec);
	}
	
	public static String encrypt(String text, String Key) throws InvalidKeyException, java.security.InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        SecretKey key = generateKey(Key);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrytedBytes = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encrytedBytes);
    }
	
	public static String decrypt(String text, String Key) throws InvalidKeyException, java.security.InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        SecretKey key = generateKey(Key);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeByte = Base64.getDecoder().decode(text);
        byte[] decrytedBytes = cipher.doFinal(decodeByte);
        return new String (decrytedBytes);
    }
}
