package com.jredu.servlet;

import com.jredu.util.AccessToken;

public class Test {
	public static void main(String[] args) {
		String respString = AccessToken.fetchAccessToken();
		System.out.println(respString);
	}
}
