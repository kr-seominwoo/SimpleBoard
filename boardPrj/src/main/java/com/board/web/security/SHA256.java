package com.board.web.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	public String encrypt(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		
		StringBuilder builder = new StringBuilder();
		for (byte b : md.digest()) {
			builder.append(String.format("%02x", b));
		}
		
		return builder.toString();
	}
}
