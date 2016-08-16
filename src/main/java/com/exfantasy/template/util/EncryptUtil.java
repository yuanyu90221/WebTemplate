package com.exfantasy.template.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

/**
 * https://examples.javacodegeeks.com/core-java/crypto/encrypt-decrypt-string-with-des/
 * 
 * http://doraprojects.net/blog/?p=1276
 * 
 * @author tommy.feng
 */
public class EncryptUtil {
	
	private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";
    private static SecretKey key;
	
	private static Cipher ecipher;
	private static Cipher dcipher;
	
	static {
		try {
			key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
			
			ecipher = Cipher.getInstance(ALGORITHM);
			dcipher = Cipher.getInstance(ALGORITHM);
	
			// initialize the ciphers with the given key
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No Such Algorithm:" + e.getMessage());
		} catch (NoSuchPaddingException e) {
			System.out.println("No Such Padding:" + e.getMessage());
		} catch (InvalidKeyException e) {
			System.out.println("Invalid Key:" + e.getMessage());
		}
	}

	public static String encrypt(String str) {
		try {
			// encode the string into a sequence of bytes using the name charset
			// storing the result into a new byte array.
			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = ecipher.doFinal(utf8);

			// encode to base64
			enc = BASE64EncoderStream.encode(enc);

			return new String(enc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String str) {
		try {
			// decode with base64 to get bytes
			byte[] dec = BASE64DecoderStream.decode(str.getBytes());

			byte[] utf8 = dcipher.doFinal(dec);

			// create new string based on the specified charset
			return new String(utf8, "UTF8");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("You want to 0: Encrypt, 1: Decrypt");
		int option = sc.nextInt();
		
		switch (option) {
			case 0:
				System.out.println("Enter the string to encrypted:");
				String str = sc.next();
				System.out.println(encrypt(str));
				break;
			
			case 1:
				System.out.println("Enter the encrypted string to decrypted:");
				String encStr = sc.next();
				System.out.println(decrypt(encStr));
				break;
		}
        sc.close();
	}
}