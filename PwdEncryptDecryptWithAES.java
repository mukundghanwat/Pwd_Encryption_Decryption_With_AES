package com.sample;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PwdEncryptDecryptWithAES {
	private static final String SECRET_KEY = "abc12345xyz8524w";

	private static final String INIT_VECTOR = "abc12345xyz8524w";

	static String inputText = "Mukund@#123";

	public static void main(String[] args) throws Exception {
		System.out.println("Original String :: " + inputText);
		String encodedString = encrypt(inputText);
		System.out.println("Encrypted String :: " + encodedString);
		String decodedString = decrypt(encodedString);
		System.out.println("Decrypted String :: " + decodedString);
	}

	/**
	 * This method is used to encrypt data
	 * 
	 * @param msg
	 * @return String
	 */
	public static String encrypt(String msg) throws Exception {
		IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

		byte[] encrypted = cipher.doFinal(msg.getBytes());

		String encodedString = Base64.getEncoder().encodeToString(encrypted);
		return encodedString;
	}

	/**
	 * This method is used to decrypt data
	 * 
	 * @param msg
	 * @return String
	 */
	public static String decrypt(String msg) throws Exception {
		IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] decodeMsg = Base64.getDecoder().decode(msg);
		byte[] decrypted = cipher.doFinal(decodeMsg);
		String decodedString = new String(decrypted);
		return decodedString;
	}
}
