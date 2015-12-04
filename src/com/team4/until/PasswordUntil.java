package com.team4.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import com.team4.domain.Employee;

import sun.misc.BASE64Encoder;

public class PasswordUntil {
	public static String md5(String input) {

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte md5[] = md.digest(input.getBytes());

			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(md5);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
