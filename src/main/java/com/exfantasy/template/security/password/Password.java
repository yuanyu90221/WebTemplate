package com.exfantasy.template.security.password;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {
	public static PasswordEncoder encoder = new BCryptPasswordEncoder();
	private static final int workload = 8;

	public static String encrypt(String plaintext_password) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(plaintext_password, salt);
		return hashed_password;
	}

	public static boolean isValidatePassword(String password, String hashPassword) {
		return BCrypt.checkpw(password, hashPassword);
	}

	public static void main(String[] args) {
		String pwd = "b9134034";
		String encrypt = encrypt(pwd);
		
		System.out.println("Original password: [" + pwd + "] -> encrypt: [" + encrypt + "]");
		
		System.out.println(isValidatePassword(pwd, encrypt));
	}
}