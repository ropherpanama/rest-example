package com.rest.security;

import com.google.common.base.Charsets;

public class Hashing {
	public static String sha256Encode(String arg) {
		return com.google.common.hash.Hashing.sha256().hashString(arg, Charsets.UTF_8).toString();
	}
}
