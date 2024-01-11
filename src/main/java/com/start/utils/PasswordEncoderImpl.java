package com.start.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderImpl {
		public static void main(String[] args) {
			BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
			System.out.println(pass.encode("amit"));
			System.out.println(pass.encode("admin"));

		}
		
}
